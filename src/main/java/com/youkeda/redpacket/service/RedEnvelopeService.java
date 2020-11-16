package com.youkeda.redpacket.service;

import com.youkeda.redpacket.model.RedEnvelope;

public interface RedEnvelopeService {
    /**
     * 添加红包信息
     *
     * @param redEnvelope 红包信息
     * @return MatchData
     */
    RedEnvelope save(RedEnvelope redEnvelope);

    /**
     * 获取单个红包信息
     *
     * @param id 主键id
     * @return RedEnvelope
     */
    RedEnvelope get(String id);

}
