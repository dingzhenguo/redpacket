package com.youkeda.redpacket.api;

import com.youkeda.redpacket.model.*;
import com.youkeda.redpacket.params.PreSingleRedEnvelopeQueryParam;
import com.youkeda.redpacket.params.UserRedEnvelopeRecordQueryParam;
import com.youkeda.redpacket.service.PreSingleRedEnvelopeService;
import com.youkeda.redpacket.service.UserRedEnvelopeRecordService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/user")
public class UserRedEnvelopeRecordApi {
    @Autowired
    private PreSingleRedEnvelopeService preSingleRedEnvelopeService;
    @Autowired
    private UserRedEnvelopeRecordService userRedEnvelopeRecordService;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/query")
    @ResponseBody
    public Result<List<UserRedEnvelopeRecord>> query(@RequestParam(value = "redEnvelopeId") String redEnvelopeId) {
        Result<List<UserRedEnvelopeRecord>> result = new Result<>();
        result.setSuccess(true);
        UserRedEnvelopeRecordQueryParam userRedEnvelopeRecordQueryParam = new UserRedEnvelopeRecordQueryParam();
        userRedEnvelopeRecordQueryParam.setRedEnvelopeId(redEnvelopeId);
        userRedEnvelopeRecordQueryParam.setNeedUserInfo(true);
        List<UserRedEnvelopeRecord> userRedEnvelopeRecordList = userRedEnvelopeRecordService.query(
                userRedEnvelopeRecordQueryParam);
        if (CollectionUtils.isEmpty(userRedEnvelopeRecordList)) {
            result.setData(userRedEnvelopeRecordList);
            return result;
        }
        result.setData(userRedEnvelopeRecordList);
        return result;
    }

    @PostMapping("/dismantle")
    @ResponseBody
    public Result<UserRedEnvelopeRecord> dismantle(@RequestParam(value = "userId", required = false) String userId,
                                                   @RequestParam(value = "redEnvelopeId") String redEnvelopeId) {
        Result<UserRedEnvelopeRecord> result = new Result<>();
        if (StringUtils.isEmpty(userId)) {
            result.setSuccess(false);
            result.setMessage("用户id不能为空！");
            return result;
        }
        if (StringUtils.isEmpty(redEnvelopeId)) {
            result.setSuccess(false);
            result.setMessage("红包id不能为空！");
            return result;
        }
        //查询剩余的预定红包个数
        PreSingleRedEnvelopeQueryParam param = new PreSingleRedEnvelopeQueryParam();
        param.setRedEnvelopeId(redEnvelopeId);
        param.setNeedRedEnvelope(true);
        List<PreSingleRedEnvelope> preSingleRedEnvelopeList = preSingleRedEnvelopeService.query(param);
        if (CollectionUtils.isEmpty(preSingleRedEnvelopeList)) {
            result.setSuccess(false);
            result.setMessage("未查询到任何预定红包");
            return result;
        }
        RedEnvelope redEnvelope = preSingleRedEnvelopeList.get(0).getRedEnvelope();

        //判断红包是否已经全部领取完毕
        UserRedEnvelopeRecordQueryParam userRedEnvelopeRecordQueryParam = new UserRedEnvelopeRecordQueryParam();
        userRedEnvelopeRecordQueryParam.setRedEnvelopeId(redEnvelopeId);
        List<UserRedEnvelopeRecord> userRedEnvelopeRecords = userRedEnvelopeRecordService.query(
                userRedEnvelopeRecordQueryParam);
        if (!CollectionUtils.isEmpty(userRedEnvelopeRecords)) {
            List<UserRedEnvelopeRecord> userRedEnvelopeRecordLists = userRedEnvelopeRecords.stream().filter(
                    userRedEnvelopeRecord -> {
                        return !StringUtils.isEmpty(userRedEnvelopeRecord.getUserId()) && userRedEnvelopeRecord.getUserId()
                                .equals(userId);
                    }).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(userRedEnvelopeRecordLists)) {
                result.setMessage("您已领取了该红包，不要重复领取！");
                return result;
            }
            List<UserRedEnvelopeRecord> userRedEnvelopeRecordList = userRedEnvelopeRecords.stream().filter(
                    userRedEnvelopeRecord -> {
                        return !StringUtils.isEmpty(userRedEnvelopeRecord.getUserId());
                    }).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(userRedEnvelopeRecordList)
                    && redEnvelope.getTotalAmount() == userRedEnvelopeRecordList.size()) {
                result.setSuccess(false);
                result.setMessage("红包已经领取完毕！");
                return result;
            }
            RLock rLock = redissonClient.getLock("RED_ENVELOPE");
            try {
                boolean unlock = rLock.tryLock(3, 10, TimeUnit.SECONDS);
                if (unlock) {
                    //拆红包
                    UserRedEnvelopeRecord userRedEnvelopeRecord = dismantleRedEnvelope(userId, preSingleRedEnvelopeList,
                            redEnvelopeId);
                    if (userRedEnvelopeRecord == null) {
                        result.setSuccess(false);
                        result.setMessage("拆取红包失败！");
                        return result;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            //拆红包
            UserRedEnvelopeRecord userRedEnvelopeRecord = dismantleRedEnvelope(userId, preSingleRedEnvelopeList,
                    redEnvelopeId);
            if (userRedEnvelopeRecord == null) {
                result.setSuccess(false);
                result.setMessage("拆取红包失败！");
                return result;
            }
        }

        return result;
    }

    /**
     * 拆红包
     *
     * @param userId                   用户Id
     * @param preSingleRedEnvelopeList 未领取的预定红包
     */
    private UserRedEnvelopeRecord dismantleRedEnvelope(String userId,
                                                       List<PreSingleRedEnvelope> preSingleRedEnvelopeList,
                                                       String redEnvelopeId) {
        PreSingleRedEnvelope preSingleRedEnvelope = null;
        if (!CollectionUtils.isEmpty(preSingleRedEnvelopeList)) {
            preSingleRedEnvelope = preSingleRedEnvelopeList.get((int)(Math.random() * preSingleRedEnvelopeList.size()));
            while (PreSingleRedEnvelopeStatus.DONE.equals(preSingleRedEnvelope.getStatus())) {
                preSingleRedEnvelope = preSingleRedEnvelopeList.get(
                        (int)(Math.random() * preSingleRedEnvelopeList.size()));
            }
            preSingleRedEnvelope.setStatus(PreSingleRedEnvelopeStatus.DONE);
            preSingleRedEnvelopeService.save(preSingleRedEnvelope);
        }
        UserRedEnvelopeRecord userRedEnvelopeRecord = new UserRedEnvelopeRecord();
        userRedEnvelopeRecord.setUserId(userId);
        userRedEnvelopeRecord.setRedEnvelopeId(redEnvelopeId);
        if (preSingleRedEnvelope != null) {
            userRedEnvelopeRecord.setPreSingleRedEnvelopeId(preSingleRedEnvelope.getId());
        }
        UserRedEnvelopeRecord resultUserRedEnvelope = userRedEnvelopeRecordService.save(userRedEnvelopeRecord);
        return resultUserRedEnvelope;
    }
}
