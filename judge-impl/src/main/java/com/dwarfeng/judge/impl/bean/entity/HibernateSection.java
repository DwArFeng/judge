package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_section")
public class HibernateSection implements Bean {

    private static final long serialVersionUID = -821143822988116128L;

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

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateDriverInfo.class, mappedBy = "section")
    private Set<HibernateDriverInfo> driverInfos = new HashSet<>();
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateJudgerInfo.class, mappedBy = "section")
    private Set<HibernateJudgerInfo> judgerInfos = new HashSet<>();

    public HibernateSection() {
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
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

    public Set<HibernateDriverInfo> getDriverInfos() {
        return driverInfos;
    }

    public void setDriverInfos(Set<HibernateDriverInfo> drives) {
        this.driverInfos = drives;
    }

    public Set<HibernateJudgerInfo> getJudgerInfos() {
        return judgerInfos;
    }

    public void setJudgerInfos(Set<HibernateJudgerInfo> judgerInfos) {
        this.judgerInfos = judgerInfos;
    }

    @Override
    public String toString() {
        return "HibernateSection{" +
                "longId=" + longId +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", driverInfos=" + driverInfos +
                ", judgerInfos=" + judgerInfos +
                '}';
    }
}
