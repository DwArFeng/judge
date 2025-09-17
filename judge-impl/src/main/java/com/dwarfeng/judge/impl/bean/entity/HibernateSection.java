package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.datamark.bean.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.bean.jpa.DatamarkField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Hibernate 部件。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_section")
@EntityListeners(DatamarkEntityListener.class)
public class HibernateSection implements Bean {

    private static final long serialVersionUID = -1011909320768065769L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------一对一-----------------------------------------------------------
    @OneToOne(cascade = CascadeType.MERGE, targetEntity = HibernateJudgementModal.class, mappedBy = "section")
    private HibernateJudgementModal judgementModal;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateDriverInfo.class, mappedBy = "section")
    private Set<HibernateDriverInfo> driverInfos = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateAnalyserInfo.class, mappedBy = "section")
    private Set<HibernateAnalyserInfo> analyserInfos = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateJudgerInfo.class, mappedBy = "section")
    private Set<HibernateJudgerInfo> judgerInfos = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateTask.class, mappedBy = "section")
    private Set<HibernateTask> tasks = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateJudgementHistory.class, mappedBy = "section")
    private Set<HibernateJudgementHistory> judgementHistories = new HashSet<>();

    // -----------------------------------------------------------审计-----------------------------------------------------------
    @DatamarkField(handlerName = "sectionDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "sectionDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    public HibernateSection() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateJudgementModal getJudgementModal() {
        return judgementModal;
    }

    public void setJudgementModal(HibernateJudgementModal judgementModal) {
        this.judgementModal = judgementModal;
    }

    public Set<HibernateDriverInfo> getDriverInfos() {
        return driverInfos;
    }

    public void setDriverInfos(Set<HibernateDriverInfo> driverInfos) {
        this.driverInfos = driverInfos;
    }

    public Set<HibernateAnalyserInfo> getAnalyserInfos() {
        return analyserInfos;
    }

    public void setAnalyserInfos(Set<HibernateAnalyserInfo> analyserInfos) {
        this.analyserInfos = analyserInfos;
    }

    public Set<HibernateJudgerInfo> getJudgerInfos() {
        return judgerInfos;
    }

    public void setJudgerInfos(Set<HibernateJudgerInfo> judgerInfos) {
        this.judgerInfos = judgerInfos;
    }

    public Set<HibernateTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<HibernateTask> tasks) {
        this.tasks = tasks;
    }

    public Set<HibernateJudgementHistory> getJudgementHistories() {
        return judgementHistories;
    }

    public void setJudgementHistories(Set<HibernateJudgementHistory> judgementHistories) {
        this.judgementHistories = judgementHistories;
    }

    public String getCreatedDatamark() {
        return createdDatamark;
    }

    public void setCreatedDatamark(String createdDatamark) {
        this.createdDatamark = createdDatamark;
    }

    public String getModifiedDatamark() {
        return modifiedDatamark;
    }

    public void setModifiedDatamark(String modifiedDatamark) {
        this.modifiedDatamark = modifiedDatamark;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "name = " + name + ", " +
                "enabled = " + enabled + ", " +
                "remark = " + remark + ", " +
                "judgementModal = " + judgementModal + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }
}
