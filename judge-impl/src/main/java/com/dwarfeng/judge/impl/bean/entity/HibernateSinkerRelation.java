package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.datamark.bean.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.bean.jpa.DatamarkField;
import com.dwarfeng.judge.impl.bean.key.HibernateSinkerRelationKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateSinkerRelationKey.class)
@Table(name = "tbl_sinker_relation")
@EntityListeners(DatamarkEntityListener.class)
public class HibernateSinkerRelation implements Bean {

    private static final long serialVersionUID = 8483375426873764806L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "section_id", nullable = false)
    private Long sectionLongId;

    @Id
    @Column(name = "sinker_id", nullable = false)
    private Long sinkerLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateSection.class)
    @JoinColumns({ //
            @JoinColumn(name = "section_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateSection section;

    @ManyToOne(targetEntity = HibernateSinkerInfo.class)
    @JoinColumns({ //
            @JoinColumn(name = "sinker_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateSinkerInfo sinkerInfo;

    // -----------------------------------------------------------审计-----------------------------------------------------------
    @DatamarkField(handlerName = "sinkDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "sinkDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    public HibernateSinkerRelation() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateSinkerRelationKey getKey() {
        return new HibernateSinkerRelationKey(sectionLongId, sinkerLongId);
    }

    public void setKey(HibernateSinkerRelationKey key) {
        if (Objects.isNull(key)) {
            this.sectionLongId = null;
            this.sinkerLongId = null;
        } else {
            this.sectionLongId = key.getSectionLongId();
            this.sinkerLongId = key.getSinkerLongId();
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

    public HibernateSection getSection() {
        return section;
    }

    public void setSection(HibernateSection section) {
        this.section = section;
    }

    public HibernateSinkerInfo getSinkerInfo() {
        return sinkerInfo;
    }

    public void setSinkerInfo(HibernateSinkerInfo sinkerInfo) {
        this.sinkerInfo = sinkerInfo;
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
                "enabled = " + enabled + ", " +
                "remark = " + remark + ", " +
                "section = " + section + ", " +
                "sinkerInfo = " + sinkerInfo + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }
}
