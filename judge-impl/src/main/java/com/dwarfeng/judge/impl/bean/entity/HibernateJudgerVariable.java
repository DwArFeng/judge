package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateJudgerVariableKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

/**
 * Hibernate 判断器变量。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Entity
@IdClass(HibernateJudgerVariableKey.class)
@Table(name = "tbl_judger_variable")
public class HibernateJudgerVariable implements Bean {

    private static final long serialVersionUID = -8856294361960537070L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "judger_info_id", nullable = false)
    private Long judgerInfoLongId;

    @Id
    @Column(name = "variable_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String variableStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateJudgerInfo.class)
    @JoinColumns({ //
            @JoinColumn(name = "judger_info_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateJudgerInfo judgerInfo;

    public HibernateJudgerVariable() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateJudgerVariableKey getKey() {
        if (Objects.isNull(judgerInfoLongId) || Objects.isNull(variableStringId)) {
            return null;
        }
        return new HibernateJudgerVariableKey(judgerInfoLongId, variableStringId);
    }

    public void setKey(HibernateJudgerVariableKey key) {
        if (Objects.isNull(key)) {
            this.judgerInfoLongId = null;
            this.variableStringId = null;
        } else {
            this.judgerInfoLongId = key.getJudgerInfoLongId();
            this.variableStringId = key.getVariableStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getJudgerInfoLongId() {
        return judgerInfoLongId;
    }

    public void setJudgerInfoLongId(Long judgerInfoLongId) {
        this.judgerInfoLongId = judgerInfoLongId;
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

    public HibernateJudgerInfo getJudgerInfo() {
        return judgerInfo;
    }

    public void setJudgerInfo(HibernateJudgerInfo judgerInfo) {
        this.judgerInfo = judgerInfo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "judgerInfoLongId = " + judgerInfoLongId + ", " +
                "variableStringId = " + variableStringId + ", " +
                "value = " + value + ", " +
                "judgerInfo = " + judgerInfo + ")";
    }
}
