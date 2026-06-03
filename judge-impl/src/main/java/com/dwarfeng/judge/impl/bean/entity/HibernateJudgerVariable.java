package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateJudgerVariableKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
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

    private static final long serialVersionUID = -6102041394646599726L;

    // region 主键

    @Id
    @Column(name = "judger_info_id", nullable = false)
    private Long judgerInfoLongId;

    @Id
    @Column(name = "variable_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String variableStringId;

    // endregion

    // region 主属性字段

    @Column(name = "value_type", nullable = false)
    private int valueType;

    @Column(name = "string_value", columnDefinition = "TEXT")
    private String stringValue;

    @Column(name = "long_value")
    private Long longValue;

    @Column(name = "double_value")
    private Double doubleValue;

    @Column(name = "boolean_value")
    private Boolean booleanValue;

    @Column(name = "date_value")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValue;

    // endregion

    // region 多对一

    @ManyToOne(targetEntity = HibernateJudgerInfo.class)
    @JoinColumns({ //
            @JoinColumn(name = "judger_info_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateJudgerInfo judgerInfo;

    // endregion

    public HibernateJudgerVariable() {
    }

    // region 映射用属性区

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

    // endregion

    // region 常规属性区

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

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public HibernateJudgerInfo getJudgerInfo() {
        return judgerInfo;
    }

    public void setJudgerInfo(HibernateJudgerInfo judgerInfo) {
        this.judgerInfo = judgerInfo;
    }

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "judgerInfoLongId = " + judgerInfoLongId + ", " +
                "variableStringId = " + variableStringId + ", " +
                "valueType = " + valueType + ", " +
                "stringValue = " + stringValue + ", " +
                "longValue = " + longValue + ", " +
                "doubleValue = " + doubleValue + ", " +
                "booleanValue = " + booleanValue + ", " +
                "dateValue = " + dateValue + ", " +
                "judgerInfo = " + judgerInfo + ")";
    }
}
