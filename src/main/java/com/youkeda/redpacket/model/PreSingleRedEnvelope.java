package com.youkeda.redpacket.model;

import java.time.LocalDateTime;

/**
 * 预置的每个单个红包模型
 */
public class PreSingleRedEnvelope {
    /**
     * 主键id
     */
    private String id;

    /**
     * 分享红包Id
     */
    private String redEnvelopeId;

    /**
     * 分享红包模型
     */
    private RedEnvelope redEnvelope;

    /**
     * 每个红包金额
     */
    private double redEnvelopeAmount;

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

    /**
     * 分配状态
     */
    private PreSingleRedEnvelopeStatus status = PreSingleRedEnvelopeStatus.TODO;

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

    public double getRedEnvelopeAmount() {
        return redEnvelopeAmount;
    }

    public void setRedEnvelopeAmount(double redEnvelopeAmount) {
        this.redEnvelopeAmount = redEnvelopeAmount;
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

    public RedEnvelope getRedEnvelope() {
        return redEnvelope;
    }

    public void setRedEnvelope(RedEnvelope redEnvelope) {
        this.redEnvelope = redEnvelope;
    }

    public PreSingleRedEnvelopeStatus getStatus() {
        return status;
    }

    public void setStatus(PreSingleRedEnvelopeStatus status) {
        this.status = status;
    }
}
