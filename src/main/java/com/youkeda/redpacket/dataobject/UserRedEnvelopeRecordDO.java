package com.youkeda.redpacket.dataobject;

import com.youkeda.redpacket.model.User;
import com.youkeda.redpacket.model.UserRedEnvelopeRecord;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class UserRedEnvelopeRecordDO {
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
     * 用户id
     */
    private String userId;

    /**
     * 用户
     */
    private User user;

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
     * 获取预制红包id
     */
    public String getPreSingleRedEnvelopeId() {
        return preSingleRedEnvelopeId;
    }

    /**
     * 设置预制红包id
     */
    public void setPreSingleRedEnvelopeId(String preSingleRedEnvelopeId) {
        this.preSingleRedEnvelopeId = preSingleRedEnvelopeId;
    }

    /**
     * 获取用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getRedEnvelopeId() {
        return redEnvelopeId;
    }

    public void setRedEnvelopeId(String redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
    }

    public UserRedEnvelopeRecordDO() {
    }

    public UserRedEnvelopeRecordDO(UserRedEnvelopeRecord userRedEnvelopeRecord) {
        BeanUtils.copyProperties(userRedEnvelopeRecord, this);
    }

    public UserRedEnvelopeRecord convertToModel() {
        UserRedEnvelopeRecord userRedEnvelopeRecord = new UserRedEnvelopeRecord();
        BeanUtils.copyProperties(this, userRedEnvelopeRecord);
        Date date = this.getGmtCreated();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        userRedEnvelopeRecord.setGmtCreated(localDateTime);
        Date date1 = this.getGmtModified();
        Instant instant1 = date1.toInstant();
        ZoneId zoneId1 = ZoneId.systemDefault();
        LocalDateTime localDateTime2 = instant1.atZone(zoneId1).toLocalDateTime();
        userRedEnvelopeRecord.setGmtModified(localDateTime2);
        return userRedEnvelopeRecord;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}