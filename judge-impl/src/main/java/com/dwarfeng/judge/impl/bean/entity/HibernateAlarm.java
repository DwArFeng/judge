package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateAlarmKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(HibernateAlarmKey.class)
@Table(name = "tbl_alarm")
public class HibernateAlarm implements Bean {

    private static final long serialVersionUID = -5594270933619776005L;

    // region 主键

    @Id
    @Column(name = "section_id", nullable = false)
    private Long sectionLongId;

    @Id
    @Column(name = "data_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String dataStringId;

    // endregion

    // region 主属性字段

    @Column(name = "happened_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date happenedDate;

    @Column(name = "message", length = Constraints.LENGTH_MESSAGE)
    private String message;

    @Column(name = "type", length = Constraints.LENGTH_TYPE)
    private String type;

    @Column(name = "verdict_value", nullable = false)
    private double verdictValue;

    @Column(name = "verdict_message", length = Constraints.LENGTH_MESSAGE)
    private String verdictMessage;

    // endregion

    // region 多对一

    @ManyToOne(targetEntity = HibernateSection.class)
    @JoinColumns({ //
            @JoinColumn(name = "section_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateSection section;

    // endregion


    public HibernateAlarm() {
    }

    // region 映射用属性区

    public HibernateAlarmKey getKey() {
        if (java.util.Objects.isNull(sectionLongId) || java.util.Objects.isNull(dataStringId)) {
            return null;
        }
        return new HibernateAlarmKey(sectionLongId, dataStringId);
    }

    public void setKey(HibernateAlarmKey key) {
        if (java.util.Objects.isNull(key)) {
            this.sectionLongId = null;
            this.dataStringId = null;
        } else {
            this.sectionLongId = key.getSectionLongId();
            this.dataStringId = key.getDataStringId();
        }
    }

    // endregion

    // region 常规属性区

    public Long getSectionLongId() {
        return sectionLongId;
    }

    public void setSectionLongId(Long sectionLongId) {
        this.sectionLongId = sectionLongId;
    }

    public String getDataStringId() {
        return dataStringId;
    }

    public void setDataStringId(String dataStringId) {
        this.dataStringId = dataStringId;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getVerdictValue() {
        return verdictValue;
    }

    public void setVerdictValue(double verdictValue) {
        this.verdictValue = verdictValue;
    }

    public String getVerdictMessage() {
        return verdictMessage;
    }

    public void setVerdictMessage(String verdictMessage) {
        this.verdictMessage = verdictMessage;
    }

    public HibernateSection getSection() {
        return section;
    }

    public void setSection(HibernateSection section) {
        this.section = section;
    }

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "sectionLongId = " + sectionLongId + ", " +
                "dataStringId = " + dataStringId + ", " +
                "happenedDate = " + happenedDate + ", " +
                "message = " + message + ", " +
                "type = " + type + ", " +
                "verdictValue = " + verdictValue + ", " +
                "verdictMessage = " + verdictMessage + ", " +
                "section = " + section + ")";
    }
}
