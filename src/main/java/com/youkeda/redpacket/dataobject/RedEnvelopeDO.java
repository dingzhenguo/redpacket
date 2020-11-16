package com.youkeda.redpacket.dataobject;


import com.youkeda.redpacket.model.RedEnvelope;
import com.youkeda.redpacket.util.UUIDUtils;
import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class RedEnvelopeDO {
    /**
     * 主键
     */
    private String id;

    /**
     * 发送红包的用户id
     */
    private String sendUserId;

    /**
     * 红包金额
     */
    private Double redEnvelopeAmount;

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
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 获取主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取发送红包的用户id
     */
    public String getSendUserId() {
        return sendUserId;
    }

    /**
     * 设置发送红包的用户id
     */
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    /**
     * 获取红包金额
     */
    public Double getRedEnvelopeAmount() {
        return redEnvelopeAmount;
    }

    /**
     * 设置红包金额
     */
    public void setRedEnvelopeAmount(Double redEnvelopeAmount) {
        this.redEnvelopeAmount = redEnvelopeAmount;
    }

    /**
     * 获取红包总数
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置红包总数
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取红包备注信息
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置红包备注信息
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 设置创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 获取修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public RedEnvelopeDO() {
    }

    public RedEnvelopeDO(RedEnvelope redEnvelope) {
        if (StringUtils.isEmpty(redEnvelope.getId())) {
            this.setId(UUIDUtils.getUUID());
        } else {
            this.id = redEnvelope.getId();
        }
        this.setNote(redEnvelope.getNote());
        this.setRedEnvelopeAmount(redEnvelope.getRedEnvelopeAmount());
        this.setSendUserId(redEnvelope.getSendUserId());
        this.setTotalAmount(redEnvelope.getTotalAmount());

        if (redEnvelope.getGmtCreated() != null) {
            this.setGmtCreated(
                Date.from(redEnvelope.getGmtCreated().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            this.setGmtCreated(new Date());
        }
        if (redEnvelope.getGmtModified() != null) {
            this.setGmtModified(
                Date.from(redEnvelope.getGmtModified().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            this.setGmtModified(new Date());
        }
    }

    public RedEnvelope convertToModel() {
        RedEnvelope redEnvelope = new RedEnvelope();
        redEnvelope.setId(this.getId());
        redEnvelope.setNote(this.getNote());
        if (this.getRedEnvelopeAmount() != null) {
            redEnvelope.setRedEnvelopeAmount(this.getRedEnvelopeAmount().doubleValue());
        }
        redEnvelope.setSendUserId(this.getSendUserId());
        redEnvelope.setTotalAmount(this.getTotalAmount());
        Date date = this.getGmtCreated();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        redEnvelope.setGmtCreated(localDateTime);
        Date date1 = this.getGmtModified();
        Instant instant1 = date1.toInstant();
        ZoneId zoneId1 = ZoneId.systemDefault();
        LocalDateTime localDateTime2 = instant1.atZone(zoneId1).toLocalDateTime();
        redEnvelope.setGmtModified(localDateTime2);
        return redEnvelope;
    }
}