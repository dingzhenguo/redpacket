package com.youkeda.redpacket.api;

import com.youkeda.redpacket.model.PreSingleRedEnvelope;
import com.youkeda.redpacket.model.RedEnvelope;
import com.youkeda.redpacket.model.Result;
import com.youkeda.redpacket.params.BatchAddPreSingleRedEnvelopeParam;
import com.youkeda.redpacket.service.PreSingleRedEnvelopeService;
import com.youkeda.redpacket.service.RedEnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/api")
public class RedEnvelopeApi {
    @Autowired
    private RedEnvelopeService redEnvelopeService;
    @Autowired
    private PreSingleRedEnvelopeService preSingleRedEnvelopeService;

    //红包最小值
    private static final BigDecimal MINVALUE = new BigDecimal("0.01");
    //红包最大值
    private static final BigDecimal MAXVALUE = new BigDecimal("200");
    // 这里为了避免某一个红包占用大量资金，我们需要设定非最后一个红包的最大金额，我们把他设置为红包金额平均值的N倍
    private static final BigDecimal TIMES = new BigDecimal("2.1");
    //小数点位数
    private static final int MAX_DIGIT = 2;

    @PostMapping("/create")
    @ResponseBody
    public Result<RedEnvelope> create(@RequestBody RedEnvelope redEnvelope) {
        Result<RedEnvelope> result = new Result<>();
        result.setSuccess(true);
        result.setData(redEnvelopeService.save(redEnvelope));
        //插入预分配红包金额
        int people = redEnvelope.getTotalAmount();
        double redEnvelopeAmount = redEnvelope.getRedEnvelopeAmount();
        if (people == 0) {
            result.setSuccess(false);
            result.setMessage("红包数量不能为0");
        }
        if (redEnvelopeAmount == 0.0) {
            result.setSuccess(false);
            result.setMessage("红包总金额不能为0");
        }
        //分配红包
        List<BigDecimal> moneyList = spiltRedPackets(new BigDecimal(redEnvelopeAmount), people);
        List<PreSingleRedEnvelope> preSingleRedEnvelopeList = new ArrayList<>();
        for (int i = 0; i <= moneyList.size()-1; i++) {
            PreSingleRedEnvelope preSingleRedEnvelope = new PreSingleRedEnvelope();
            preSingleRedEnvelope.setRedEnvelopeAmount(moneyList.get(i).doubleValue());
            preSingleRedEnvelope.setRedEnvelopeId(redEnvelope.getId());
            preSingleRedEnvelopeList.add(preSingleRedEnvelope);
        }
        //插入分配红包
        BatchAddPreSingleRedEnvelopeParam param = new BatchAddPreSingleRedEnvelopeParam();
        param.setPreSingleRedEnvelopeList(preSingleRedEnvelopeList);
        List<PreSingleRedEnvelope> batchAddResult = preSingleRedEnvelopeService.batchAdd(param);
        if (CollectionUtils.isEmpty(batchAddResult)) {
            result.setSuccess(false);
            result.setMessage("批量插入预分配红包失败！");
            return result;
        }
        return result;
    }

    /**
     * 分红包
     *
     * @param money
     * @param count
     * @return
     */
    public List<BigDecimal> spiltRedPackets(BigDecimal money, int count) {
        //首先判断红包是否合情理
        if (!isRight(money, count)) {
            return null;
        }
        List<BigDecimal> list = new ArrayList<>();
        BigDecimal average = money.divide(new BigDecimal(count), MAX_DIGIT, BigDecimal.ROUND_UP);
        BigDecimal max = TIMES.multiply(average).setScale(MAX_DIGIT, BigDecimal.ROUND_UP);
        max = max.compareTo(money) > 0 ? money : max;
        for (int i = 0; i < count; i++) {
            BigDecimal value = randomRedPacket(money, MINVALUE, max, count - i);
            list.add(value);
            money = money.subtract(value);
        }
        return list;
    }

    /**
     * 分红包核心算法
     *
     * @param money
     * @param minS
     * @param maxS
     * @param count
     * @return
     */
    public BigDecimal randomRedPacket(BigDecimal money, BigDecimal minS, BigDecimal maxS, int count) {
        //当人数剩余一个时，把当前剩余全部返回
        if (count == 1) {
            return money;
        }
        //如果当前最小红包等于最大红包，之间返回当前红包
        if (minS.compareTo(maxS) == 0) {
            return minS;
        }
        BigDecimal max = maxS.compareTo(money) > 0 ? money : maxS;
        //随机产生一个红包
        BigDecimal range = max.subtract(minS);
        BigDecimal randomNumner = new BigDecimal(Math.random()).setScale(MAX_DIGIT, BigDecimal.ROUND_HALF_UP);
        BigDecimal randomResult = range.multiply(randomNumner).setScale(MAX_DIGIT, BigDecimal.ROUND_UP);
        BigDecimal one = randomResult.add(minS);
        BigDecimal balance = money.subtract(one);
        //判断此次分配后，后续是否合理
        if (isRight(balance, count - 1)) {
            return one;
        } else {
            //重新分配
            BigDecimal avg = balance.divide(new BigDecimal(count - 1), 10, BigDecimal.ROUND_UP);
            //本次红包过大，导致下次的红包过小；如果红包过大，下次就随机一个小值到本次红包金额的一个红包
            if (avg.compareTo(MINVALUE) < 0) {
                //递归调用，修改红包最大金额
                BigDecimal average = money.divide(new BigDecimal(count), MAX_DIGIT, BigDecimal.ROUND_UP);
                return randomRedPacket(money, minS, average, count);
            } else {
                //递归调用，修改红包最小金额
                return randomRedPacket(money, one, maxS, count);
            }
        }
    }

    /**
     * 判断红包是否合情理
     *
     * @param money
     * @param count
     * @return
     */
    public boolean isRight(BigDecimal money, int count) {
        if (count <= 0 || money == null || money.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        BigDecimal avg = money.divide(new BigDecimal(count), 10, BigDecimal.ROUND_UP); //防止舍入造成误差
        if (avg.compareTo(MINVALUE) < 0) {
            return false;
        } else if (avg.compareTo(MAXVALUE) > 0) {
            return false;
        }
        return true;
    }
}
