package com.youkeda.redpacket.params;

public class PreSingleRedEnvelopeQueryParam {

    /**
     * 主键id
     */
    private String id;
    /**
     * 主红包id
     */
    private String redEnvelopeId;

    private boolean needRedEnvelope = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRedEnvelopeId() {
        return redEnvelopeId;
    }

    public void setRedEnvelopeId(String redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
    }

    public boolean isNeedRedEnvelope() {
        return needRedEnvelope;
    }

    public void setNeedRedEnvelope(boolean needRedEnvelope) {
        this.needRedEnvelope = needRedEnvelope;
    }
}
