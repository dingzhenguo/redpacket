package com.youkeda.redpacket.params;

public class UserRedEnvelopeRecordQueryParam {

    /**
     * 主键id
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 预制红包id
     */
    private String preSingleRedEnvelopeId;

    /**
     * 主红包的id
     */
    private String redEnvelopeId;

    private boolean needUserInfo = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPreSingleRedEnvelopeId() {
        return preSingleRedEnvelopeId;
    }

    public void setPreSingleRedEnvelopeId(String preSingleRedEnvelopeId) {
        this.preSingleRedEnvelopeId = preSingleRedEnvelopeId;
    }

    public String getRedEnvelopeId() {
        return redEnvelopeId;
    }

    public void setRedEnvelopeId(String redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
    }

    public boolean isNeedUserInfo() {
        return needUserInfo;
    }

    public void setNeedUserInfo(boolean needUserInfo) {
        this.needUserInfo = needUserInfo;
    }
}
