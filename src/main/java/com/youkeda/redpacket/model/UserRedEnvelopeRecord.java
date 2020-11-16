package com.youkeda.redpacket.model;

import java.time.LocalDateTime;

/**
 * 用户领取红包记录
 */
public class UserRedEnvelopeRecord {
    /**
     * 主键id
     */
    private String id;

    /**
     * 预制红包id
     */
    private String preSingleRedEnvelopeId;

    /**
     * 主红包id
     */
    private String redEnvelopeId;
    /**
     * 预制红包模型
     */
    private PreSingleRedEnvelope preSingleRedEnvelope;
    /**
     * 抢到红包的用户id
     */
    private String userId;

    /**
     * 红包备注信息
     */
    private String note;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreated;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreSingleRedEnvelopeId() {
        return preSingleRedEnvelopeId;
    }

    public void setPreSingleRedEnvelopeId(String preSingleRedEnvelopeId) {
        this.preSingleRedEnvelopeId = preSingleRedEnvelopeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public PreSingleRedEnvelope getPreSingleRedEnvelope() {
        return preSingleRedEnvelope;
    }

    public void setPreSingleRedEnvelope(PreSingleRedEnvelope preSingleRedEnvelope) {
        this.preSingleRedEnvelope = preSingleRedEnvelope;
    }

    public String getRedEnvelopeId() {
        return redEnvelopeId;
    }

    public void setRedEnvelopeId(String redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
    }


}
