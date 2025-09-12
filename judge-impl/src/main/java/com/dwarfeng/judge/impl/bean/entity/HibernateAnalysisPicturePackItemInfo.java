package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_analysis_picture_pack_item_info")
public class HibernateAnalysisPicturePackItemInfo implements Bean {

    private static final long serialVersionUID = 8926796495444205656L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "pack_id")
    private Long packLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "column_index", nullable = false)
    private int index;

    @Column(name = "origin_name", length = Constraints.LENGTH_NAME)
    private String originName;

    @Column(name = "length")
    private Long length;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateAnalysisPicturePack.class)
    @JoinColumns({ //
            @JoinColumn(name = "pack_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateAnalysisPicturePack pack;

    public HibernateAnalysisPicturePackItemInfo() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getPackKey() {
        return Optional.ofNullable(packLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setPackKey(HibernateLongIdKey key) {
        this.packLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getPackLongId() {
        return packLongId;
    }

    public void setPackLongId(Long packLongId) {
        this.packLongId = packLongId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateAnalysisPicturePack getPack() {
        return pack;
    }

    public void setPack(HibernateAnalysisPicturePack pack) {
        this.pack = pack;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "packLongId = " + packLongId + ", " +
                "index = " + index + ", " +
                "originName = " + originName + ", " +
                "length = " + length + ", " +
                "remark = " + remark + ", " +
                "pack = " + pack + ")";
    }
}
