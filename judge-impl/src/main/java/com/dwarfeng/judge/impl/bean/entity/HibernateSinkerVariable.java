package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateSinkerVariableKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateSinkerVariableKey.class)
@Table(name = "tbl_sinker_variable")
public class HibernateSinkerVariable implements Bean {

    private static final long serialVersionUID = -1651382760273589063L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "sinker_id", nullable = false)
    private Long sinkerLongId;

    @Id
    @Column(name = "variable_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String variableStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
                "value = " + value + ", " +
                "sinkerInfo = " + sinkerInfo + ")";
    }
}
