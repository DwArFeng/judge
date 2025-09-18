package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateSinkerVariableKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(HibernateSinkerVariableKey.class)
@Table(name = "tbl_sinker_variable")
public class HibernateSinkerVariable implements Bean {

    private static final long serialVersionUID = -9063036159511983050L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "sinker_id", nullable = false)
    private Long sinkerLongId;

    @Id
    @Column(name = "variable_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String variableStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
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

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateSinkerInfo.class)
    @JoinColumns({ //
            @JoinColumn(name = "sinker_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateSinkerInfo sinkerInfo;

    public HibernateSinkerVariable() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateSinkerVariableKey getKey() {
        if (Objects.isNull(sinkerLongId) || Objects.isNull(variableStringId)) {
            return null;
        }
        return new HibernateSinkerVariableKey(sinkerLongId, variableStringId);
    }

    public void setKey(HibernateSinkerVariableKey key) {
        if (Objects.isNull(key)) {
            this.sinkerLongId = null;
            this.variableStringId = null;
        } else {
            this.sinkerLongId = key.getSinkerLongId();
            this.variableStringId = key.getVariableStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getSinkerLongId() {
        return sinkerLongId;
    }

    public void setSinkerLongId(Long sinkerLongId) {
        this.sinkerLongId = sinkerLongId;
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

    public HibernateSinkerInfo getSinkerInfo() {
        return sinkerInfo;
    }

    public void setSinkerInfo(HibernateSinkerInfo sinkerInfo) {
        this.sinkerInfo = sinkerInfo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "sinkerLongId = " + sinkerLongId + ", " +
                "variableStringId = " + variableStringId + ", " +
                "valueType = " + valueType + ", " +
                "stringValue = " + stringValue + ", " +
                "longValue = " + longValue + ", " +
                "doubleValue = " + doubleValue + ", " +
                "booleanValue = " + booleanValue + ", " +
                "dateValue = " + dateValue + ", " +
                "sinkerInfo = " + sinkerInfo + ")";
    }
}
