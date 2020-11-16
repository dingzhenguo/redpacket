package com.youkeda.redpacket.service;

import com.youkeda.redpacket.model.UserRedEnvelopeRecord;
import com.youkeda.redpacket.params.UserRedEnvelopeRecordQueryParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
public interface UserRedEnvelopeRecordService {
    /**
     * 添加用户红包记录信息
     *
     * @param userRedEnvelopeRecord 用户红包记录信息
     * @return UserRedEnvelopeRecord
     */
    UserRedEnvelopeRecord save(@RequestBody UserRedEnvelopeRecord userRedEnvelopeRecord);

    /**
     * 用户领取记录查询
     *
     * @param param 查询参数
     * @return List
     */
    List<UserRedEnvelopeRecord> query(UserRedEnvelopeRecordQueryParam param);

    /**
     * 获取单个用户红包记录信息
     *
     * @param id 主键id
     * @return UserRedEnvelopeRecord
     */
    UserRedEnvelopeRecord get(String id);

}
