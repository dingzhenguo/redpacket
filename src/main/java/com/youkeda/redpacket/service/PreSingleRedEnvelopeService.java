package com.youkeda.redpacket.service;

import com.youkeda.redpacket.model.PreSingleRedEnvelope;
import com.youkeda.redpacket.model.RedEnvelope;
import com.youkeda.redpacket.params.BatchAddPreSingleRedEnvelopeParam;
import com.youkeda.redpacket.params.PreSingleRedEnvelopeQueryParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PreSingleRedEnvelopeService {
    /**
     * 添加预制单个红包信息
     *
     * @param preSingleRedEnvelope 预制单个红包信息
     * @return RedEnvelope
     */
    PreSingleRedEnvelope save(@RequestBody PreSingleRedEnvelope preSingleRedEnvelope);

    /**
     * 批量插入预制红包信息
     *
     * @param batchAddPreSingleRedEnvelopeParam
     * @return List
     */
    List<PreSingleRedEnvelope> batchAdd(
        @RequestBody BatchAddPreSingleRedEnvelopeParam batchAddPreSingleRedEnvelopeParam);

    /**
     * 查询预制红包
     *
     * @param param 预制查询参数
     * @return List
     */
    List<PreSingleRedEnvelope> query(PreSingleRedEnvelopeQueryParam param);
}
