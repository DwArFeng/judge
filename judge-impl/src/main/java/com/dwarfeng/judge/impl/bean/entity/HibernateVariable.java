package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateVariableKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateVariableKey.class)
@Table(name = "tbl_variable")
public class HibernateVariable implements Bean {

    private static final long serialVersionUID = -6044111688087156752L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "long_id", nullable = false)
    private Long longId;

    @Id
    @Column(name = "string_id", length = Constraints.LENGTH_CATEGORY, nullable = false)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    public HibernateVariable() {
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public HibernateVariableKey getKey() {
        return new HibernateVariableKey(longId, stringId);
    }

    public void setKey(HibernateVariableKey key) {
        if (Objects.isNull(key)) {
            this.longId = null;
            this.stringId = null;
        } else {
            this.longId = key.getLongId();
            this.stringId = key.getStringId();
        }
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

    @Override
    public String toString() {
        return "HibernateVariable{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
