package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.datamark.bean.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.bean.jpa.DatamarkField;
import com.dwarfeng.judge.impl.bean.key.HibernateSinkerMetaKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateSinkerMetaKey.class)
@Table(name = "tbl_sinker_meta")
@EntityListeners(DatamarkEntityListener.class)
public class HibernateSinkerMeta implements Bean {

    private static final long serialVersionUID = -2082142672684476777L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "section_id", nullable = false)
    private Long sectionLongId;

    @Id
    @Column(name = "sinker_id", nullable = false)
    private Long sinkerLongId;

    @Id
    @Column(name = "meta_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String metaStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------审计-----------------------------------------------------------
    @DatamarkField(handlerName = "sinkerDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "sinkerDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    public HibernateSinkerMeta() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateSinkerMetaKey getKey() {
        if (Objects.isNull(sectionLongId) || Objects.isNull(sinkerLongId) || Objects.isNull(metaStringId)) {
            return null;
        }
        return new HibernateSinkerMetaKey(sectionLongId, sinkerLongId, metaStringId);
    }

    public void setKey(HibernateSinkerMetaKey key) {
        if (Objects.isNull(key)) {
            this.sectionLongId = null;
            this.sinkerLongId = null;
            this.metaStringId = null;
        } else {
            this.sectionLongId = key.getSectionLongId();
            this.sinkerLongId = key.getSinkerLongId();
            this.metaStringId = key.getMetaStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getSectionLongId() {
        return sectionLongId;
    }

    public void setSectionLongId(Long sectionLongId) {
        this.sectionLongId = sectionLongId;
    }

    public Long getSinkerLongId() {
        return sinkerLongId;
    }

    public void setSinkerLongId(Long sinkerLongId) {
        this.sinkerLongId = sinkerLongId;
    }

    public String getMetaStringId() {
        return metaStringId;
    }

    public void setMetaStringId(String metaStringId) {
        this.metaStringId = metaStringId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
                "sectionLongId = " + sectionLongId + ", " +
                "sinkerLongId = " + sinkerLongId + ", " +
                "metaStringId = " + metaStringId + ", " +
                "value = " + value + ", " +
                "remark = " + remark + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }
}
