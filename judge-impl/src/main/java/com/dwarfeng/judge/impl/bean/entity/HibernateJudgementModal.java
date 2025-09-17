package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

/**
 * Hibernate 判断结果模态。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_judgement_modal")
public class HibernateJudgementModal implements Bean {

    private static final long serialVersionUID = -251394224640230106L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "judger_info_id")
    private Long judgerInfoLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "happened_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date happenedDate;

    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "message", length = Constraints.LENGTH_MESSAGE)
    private String message;

    // -----------------------------------------------------------一对一-----------------------------------------------------------
    @OneToOne(targetEntity = HibernateSection.class)
    @JoinColumns({ //
            @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateSection section;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateJudgerInfo.class)
    @JoinColumns({ //
            @JoinColumn(name = "judger_info_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateJudgerInfo judgerInfo;

    public HibernateJudgementModal() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getJudgerInfoKey() {
        return Optional.ofNullable(judgerInfoLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setJudgerInfoKey(HibernateLongIdKey key) {
        this.judgerInfoLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getJudgerInfoLongId() {
        return judgerInfoLongId;
    }

    public void setJudgerInfoLongId(Long judgerInfoLongId) {
        this.judgerInfoLongId = judgerInfoLongId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HibernateSection getSection() {
        return section;
    }

    public void setSection(HibernateSection section) {
        this.section = section;
    }

    public HibernateJudgerInfo getJudgerInfo() {
        return judgerInfo;
    }

    public void setJudgerInfo(HibernateJudgerInfo judgerInfo) {
        this.judgerInfo = judgerInfo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "judgerInfoLongId = " + judgerInfoLongId + ", " +
                "happenedDate = " + happenedDate + ", " +
                "value = " + value + ", " +
                "message = " + message + ", " +
                "section = " + section + ", " +
                "judgerInfo = " + judgerInfo + ")";
    }
}
