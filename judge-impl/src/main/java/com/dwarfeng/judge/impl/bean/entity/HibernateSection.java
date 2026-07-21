package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.datamark.sdk.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.sdk.jpa.DatamarkField;
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

    private static final long serialVersionUID = -7330800258642226431L;

    // region 主键

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // endregion

    // region 主属性字段

    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // endregion

    // region 一对多

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateAlarm.class, mappedBy = "section")
    private Set<HibernateAlarm> alarms = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateAlarmHistory.class, mappedBy = "section")
    private Set<HibernateAlarmHistory> alarmHistories = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateAlarmModal.class, mappedBy = "section")
    private Set<HibernateAlarmModal> alarmModals = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateAlarmSetting.class, mappedBy = "section")
    private Set<HibernateAlarmSetting> alarmSettings = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateDriverInfo.class, mappedBy = "section")
    private Set<HibernateDriverInfo> driverInfos = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateAnalyserInfo.class, mappedBy = "section")
    private Set<HibernateAnalyserInfo> analyserInfos = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateJudgerInfo.class, mappedBy = "section")
    private Set<HibernateJudgerInfo> judgerInfos = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateTask.class, mappedBy = "section")
    private Set<HibernateTask> tasks = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateVerdict.class, mappedBy = "section")
    private Set<HibernateVerdict> verdicts = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateSinkerRelation.class, mappedBy = "section")
    private Set<HibernateSinkerRelation> sinkerRelations = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateVisualizerInfo.class, mappedBy = "section")
    private Set<HibernateVisualizerInfo> visualizerInfos = new HashSet<>();

    // endregion

    // region 审计

    @DatamarkField(handlerName = "sectionDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.sdk.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "sectionDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.sdk.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    // endregion

    public HibernateSection() {
    }

    // region 映射用属性区

    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // endregion

    // region 常规属性区

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

    public Set<HibernateAlarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(Set<HibernateAlarm> alarms) {
        this.alarms = alarms;
    }

    public Set<HibernateAlarmHistory> getAlarmHistories() {
        return alarmHistories;
    }

    public void setAlarmHistories(Set<HibernateAlarmHistory> alarmHistories) {
        this.alarmHistories = alarmHistories;
    }

    public Set<HibernateAlarmModal> getAlarmModals() {
        return alarmModals;
    }

    public void setAlarmModals(Set<HibernateAlarmModal> alarmModals) {
        this.alarmModals = alarmModals;
    }

    public Set<HibernateAlarmSetting> getAlarmSettings() {
        return alarmSettings;
    }

    public void setAlarmSettings(Set<HibernateAlarmSetting> alarmSettings) {
        this.alarmSettings = alarmSettings;
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

    public Set<HibernateVerdict> getVerdicts() {
        return verdicts;
    }

    public void setVerdicts(Set<HibernateVerdict> verdicts) {
        this.verdicts = verdicts;
    }

    public Set<HibernateSinkerRelation> getSinkerRelations() {
        return sinkerRelations;
    }

    public void setSinkerRelations(Set<HibernateSinkerRelation> sinkerRelations) {
        this.sinkerRelations = sinkerRelations;
    }

    public Set<HibernateVisualizerInfo> getVisualizerInfos() {
        return visualizerInfos;
    }

    public void setVisualizerInfos(Set<HibernateVisualizerInfo> visualizerInfos) {
        this.visualizerInfos = visualizerInfos;
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

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "name = " + name + ", " +
                "enabled = " + enabled + ", " +
                "remark = " + remark + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }
}
