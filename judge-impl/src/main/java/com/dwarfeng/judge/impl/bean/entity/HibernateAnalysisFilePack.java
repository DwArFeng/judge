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
@Table(name = "tbl_analysis_file_pack")
public class HibernateAnalysisFilePack implements Bean {

    private static final long serialVersionUID = 330874119325599677L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "item_anchor_index", nullable = false)
    private int itemAnchorIndex;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateAnalysisFilePackItemInfo.class, mappedBy = "pack")
    private Set<HibernateAnalysisFilePackItemInfo> items = new HashSet<>();

    public HibernateAnalysisFilePack() {
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

    public int getItemAnchorIndex() {
        return itemAnchorIndex;
    }

    public void setItemAnchorIndex(int itemAnchorIndex) {
        this.itemAnchorIndex = itemAnchorIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<HibernateAnalysisFilePackItemInfo> getItems() {
        return items;
    }

    public void setItems(Set<HibernateAnalysisFilePackItemInfo> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "itemAnchorIndex = " + itemAnchorIndex + ", " +
                "remark = " + remark + ")";
    }
}
