package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateAnalyserVariableKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

/**
 * Hibernate 分析器变量。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Entity
@IdClass(HibernateAnalyserVariableKey.class)
@Table(name = "tbl_analyser_variable")
public class HibernateAnalyserVariable implements Bean {

    private static final long serialVersionUID = -5526559940001417406L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "analyser_info_id", nullable = false)
    private Long analyserInfoLongId;

    @Id
    @Column(name = "variable_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String variableStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateAnalyserInfo.class)
    @JoinColumns({ //
            @JoinColumn(name = "analyser_info_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateAnalyserInfo analyserInfo;

    public HibernateAnalyserVariable() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateAnalyserVariableKey getKey() {
        if (Objects.isNull(analyserInfoLongId) || Objects.isNull(variableStringId)) {
            return null;
        }
        return new HibernateAnalyserVariableKey(analyserInfoLongId, variableStringId);
    }

    public void setKey(HibernateAnalyserVariableKey key) {
        if (Objects.isNull(key)) {
            this.analyserInfoLongId = null;
            this.variableStringId = null;
        } else {
            this.analyserInfoLongId = key.getAnalyserInfoLongId();
            this.variableStringId = key.getVariableStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getAnalyserInfoLongId() {
        return analyserInfoLongId;
    }

    public void setAnalyserInfoLongId(Long analyserInfoLongId) {
        this.analyserInfoLongId = analyserInfoLongId;
    }

    public String getVariableStringId() {
        return variableStringId;
    }

    public void setVariableStringId(String variableStringId) {
        this.variableStringId = variableStringId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HibernateAnalyserInfo getAnalyserInfo() {
        return analyserInfo;
    }

    public void setAnalyserInfo(HibernateAnalyserInfo analyserInfo) {
        this.analyserInfo = analyserInfo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "analyserInfoLongId = " + analyserInfoLongId + ", " +
                "variableStringId = " + variableStringId + ", " +
                "value = " + value + ", " +
                "analyserInfo = " + analyserInfo + ")";
    }
}
