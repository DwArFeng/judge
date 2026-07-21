package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateVerdictKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;

@Entity
@IdClass(HibernateVerdictKey.class)
@Table(name = "tbl_verdict")
public class HibernateVerdict implements Bean {

    private static final long serialVersionUID = 7398342741844720576L;

    // region 主键

    @Id
    @Column(name = "section_id", nullable = false)
    private Long sectionLongId;

    @Id
    @Column(name = "data_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String dataStringId;

    // endregion

    // region 主属性字段

    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "message", length = Constraints.LENGTH_MESSAGE)
    private String message;

    // endregion

    // region 多对一

    @ManyToOne(targetEntity = HibernateSection.class)
    @JoinColumns({ //
            @JoinColumn(name = "section_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateSection section;

    // endregion


    public HibernateVerdict() {
    }

    // region 映射用属性区

    public HibernateVerdictKey getKey() {
        if (java.util.Objects.isNull(sectionLongId) || java.util.Objects.isNull(dataStringId)) {
            return null;
        }
        return new HibernateVerdictKey(sectionLongId, dataStringId);
    }

    public void setKey(HibernateVerdictKey key) {
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

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "sectionLongId = " + sectionLongId + ", " +
                "dataStringId = " + dataStringId + ", " +
                "value = " + value + ", " +
                "message = " + message + ", " +
                "section = " + section + ")";
    }
}
