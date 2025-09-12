package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.judge.stack.bean.entity.Task;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 任务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JSFixedFastJsonTask implements Bean {

    private static final long serialVersionUID = 1550953435793606417L;

    public static JSFixedFastJsonTask of(Task task) {
        if (Objects.isNull(task)) {
            return null;
        } else {
            return new JSFixedFastJsonTask(
                    JSFixedFastJsonLongIdKey.of(task.getKey()),
                    JSFixedFastJsonLongIdKey.of(task.getSectionKey()),
                    task.getStatus(),
                    task.getCreatedDate(),
                    task.getStartedDate(),
                    task.getEndedDate(),
                    task.getDuration(),
                    task.getShouldExpireDate(),
                    task.getShouldDieDate(),
                    task.getExpiredDate(),
                    task.getDiedDate(),
                    task.getAnchorMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey sectionKey;

    @JSONField(name = "status", ordinal = 3)
    private int status;

    @JSONField(name = "created_date", ordinal = 4)
    private Date createdDate;

    @JSONField(name = "started_date", ordinal = 5)
    private Date startedDate;

    @JSONField(name = "ended_date", ordinal = 6)
    private Date endedDate;

    @JSONField(name = "duration", ordinal = 7, serializeUsing = ToStringSerializer.class)
    private Long duration;

    @JSONField(name = "should_expire_date", ordinal = 8)
    private Date shouldExpireDate;

    @JSONField(name = "should_die_date", ordinal = 9)
    private Date shouldDieDate;

    @JSONField(name = "expired_date", ordinal = 10)
    private Date expiredDate;

    @JSONField(name = "died_date", ordinal = 11)
    private Date diedDate;

    @JSONField(name = "current_step", ordinal = 12)
    private String anchorMessage;

    public JSFixedFastJsonTask() {
    }

    public JSFixedFastJsonTask(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey sectionKey, int status, Date createdDate,
            Date startedDate, Date endedDate, Long duration, Date shouldExpireDate, Date shouldDieDate,
            Date expiredDate, Date diedDate, String anchorMessage
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.status = status;
        this.createdDate = createdDate;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
        this.duration = duration;
        this.shouldExpireDate = shouldExpireDate;
        this.shouldDieDate = shouldDieDate;
        this.expiredDate = expiredDate;
        this.diedDate = diedDate;
        this.anchorMessage = anchorMessage;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(JSFixedFastJsonLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(Date endedDate) {
        this.endedDate = endedDate;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getShouldExpireDate() {
        return shouldExpireDate;
    }

    public void setShouldExpireDate(Date shouldExpireDate) {
        this.shouldExpireDate = shouldExpireDate;
    }

    public Date getShouldDieDate() {
        return shouldDieDate;
    }

    public void setShouldDieDate(Date shouldDieDate) {
        this.shouldDieDate = shouldDieDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Date getDiedDate() {
        return diedDate;
    }

    public void setDiedDate(Date diedDate) {
        this.diedDate = diedDate;
    }

    public String getAnchorMessage() {
        return anchorMessage;
    }

    public void setAnchorMessage(String anchorMessage) {
        this.anchorMessage = anchorMessage;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonTask{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", startedDate=" + startedDate +
                ", endedDate=" + endedDate +
                ", duration=" + duration +
                ", shouldExpireDate=" + shouldExpireDate +
                ", shouldDieDate=" + shouldDieDate +
                ", expiredDate=" + expiredDate +
                ", diedDate=" + diedDate +
                ", anchorMessage='" + anchorMessage + '\'' +
                '}';
    }
}
