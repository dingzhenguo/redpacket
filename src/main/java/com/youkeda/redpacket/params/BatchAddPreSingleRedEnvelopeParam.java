package com.youkeda.redpacket.params;

import com.youkeda.redpacket.model.PreSingleRedEnvelope;

import java.util.List;

public class BatchAddPreSingleRedEnvelopeParam {
    /**
     * 预定红包List
     */
    private List<PreSingleRedEnvelope> preSingleRedEnvelopeList;

    public List<PreSingleRedEnvelope> getPreSingleRedEnvelopeList() {
        return preSingleRedEnvelopeList;
    }

    public void setPreSingleRedEnvelopeList(List<PreSingleRedEnvelope> preSingleRedEnvelopeList) {
        this.preSingleRedEnvelopeList = preSingleRedEnvelopeList;
    }
}
