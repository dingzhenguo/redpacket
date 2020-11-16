package com.youkeda.redpacket.model;

import java.time.LocalDateTime;

/**
 * 红包模型
 */
public class RedEnvelope {
    /**
     * 主键id
     */
    private String id;

    /**
     * 发送红包的用户id
     */
    private String sendUserId;

    /**
     * 红包金额
     */
    private double redEnvelopeAmount;

    /**
     * 红包总数
     */
    private Integer totalAmount;

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

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public double getRedEnvelopeAmount() {
        return redEnvelopeAmount;
    }

    public void setRedEnvelopeAmount(double redEnvelopeAmount) {
        this.redEnvelopeAmount = redEnvelopeAmount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
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
}
