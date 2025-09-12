package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

/**
 * Hibernate 报警模态。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_alarm_modal")
public class HibernateAlarmModal implements Bean {

    private static final long serialVersionUID = 2906345216825546449L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "happened_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date happenedDate;

    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "alarming", nullable = false)
    private boolean alarming;

    @Column(name = "alarm_message", length = Constraints.LENGTH_MESSAGE)
    private String alarmMessage;

    // -----------------------------------------------------------一对一-----------------------------------------------------------
    @OneToOne(targetEntity = HibernateSection.class)
    @JoinColumns({ //
            @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateSection section;

    public HibernateAlarmModal() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isAlarming() {
        return alarming;
    }

    public void setAlarming(boolean alarming) {
        this.alarming = alarming;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    public HibernateSection getSection() {
        return section;
    }

    public void setSection(HibernateSection section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "happenedDate = " + happenedDate + ", " +
                "value = " + value + ", " +
                "alarming = " + alarming + ", " +
                "alarmMessage = " + alarmMessage + ", " +
                "section = " + section + ")";
    }
}
