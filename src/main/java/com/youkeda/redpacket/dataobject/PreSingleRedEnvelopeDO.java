package com.youkeda.redpacket.dataobject;

import com.youkeda.redpacket.model.PreSingleRedEnvelope;
import com.youkeda.redpacket.model.PreSingleRedEnvelopeStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class PreSingleRedEnvelopeDO {
    /**
     * 主键id
     */
    private String id;

    /**
     * 分享红包Id
     */
    private String redEnvelopeId;

    /**
     * 状态
     */
    private String status;

    /**
     * 每个红包金额
     */
    private Double redEnvelopeAmount;

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
     * 获取主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取分享红包Id
     */
    public String getRedEnvelopeId() {
        return redEnvelopeId;
    }

    /**
     * 设置分享红包Id
     */
    public void setRedEnvelopeId(String redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
    }

    /**
     * 获取每个红包金额
     */
    public Double getRedEnvelopeAmount() {
        return redEnvelopeAmount;
    }

    /**
     * 设置每个红包金额
     */
    public void setRedEnvelopeAmount(Double redEnvelopeAmount) {
        this.redEnvelopeAmount = redEnvelopeAmount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PreSingleRedEnvelopeDO() {
    }

    public PreSingleRedEnvelopeDO(PreSingleRedEnvelope preSingleRedEnvelope) {
        if (preSingleRedEnvelope.getGmtCreated() != null) {
            this.setGmtCreated(
                Date.from(preSingleRedEnvelope.getGmtCreated().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            this.setGmtCreated(new Date());
        }
        if (preSingleRedEnvelope.getGmtModified() != null) {
            this.setGmtModified(
                Date.from(preSingleRedEnvelope.getGmtModified().atZone(ZoneId.systemDefault()).toInstant()));
        } else {
            this.setGmtModified(new Date());
        }
        if (preSingleRedEnvelope.getStatus() != null) {
            this.setStatus(preSingleRedEnvelope.getStatus().toString());
        }
        if (preSingleRedEnvelope.getRedEnvelopeAmount() != 0) {
            this.setRedEnvelopeAmount(preSingleRedEnvelope.getRedEnvelopeAmount());
        }
        this.setRedEnvelopeId(preSingleRedEnvelope.getRedEnvelopeId());
        this.setNote(preSingleRedEnvelope.getNote());
        if (!StringUtils.isEmpty(preSingleRedEnvelope.getId())) {
            this.setId(preSingleRedEnvelope.getId());
        }
    }

    public PreSingleRedEnvelope convertToModel() {
        PreSingleRedEnvelope preSingleRedEnvelope = new PreSingleRedEnvelope();
        BeanUtils.copyProperties(this, preSingleRedEnvelope);
        if (this.getRedEnvelopeAmount() != null) {
            preSingleRedEnvelope.setRedEnvelopeAmount(this.getRedEnvelopeAmount().doubleValue());
        }
        if (!StringUtils.isEmpty(this.getStatus())) {
            preSingleRedEnvelope.setStatus(PreSingleRedEnvelopeStatus.valueOf(this.getStatus()));
        }
        Date date = this.getGmtCreated();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        preSingleRedEnvelope.setGmtCreated(localDateTime);
        Date date1 = this.getGmtModified();
        Instant instant1 = date1.toInstant();
        ZoneId zoneId1 = ZoneId.systemDefault();
        LocalDateTime localDateTime2 = instant1.atZone(zoneId1).toLocalDateTime();
        preSingleRedEnvelope.setGmtModified(localDateTime2);
        return preSingleRedEnvelope;
    }
}