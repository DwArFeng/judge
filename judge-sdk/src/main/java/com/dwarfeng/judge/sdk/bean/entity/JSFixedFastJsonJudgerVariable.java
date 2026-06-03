package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonJudgerVariableKey;
import com.dwarfeng.judge.stack.bean.entity.JudgerVariable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 判断器变量。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JSFixedFastJsonJudgerVariable implements Bean {

    private static final long serialVersionUID = 1382935275172311975L;

    public static JSFixedFastJsonJudgerVariable of(JudgerVariable judgerVariable) {
        if (Objects.isNull(judgerVariable)) {
            return null;
        } else {
            return new JSFixedFastJsonJudgerVariable(
                    JSFixedFastJsonJudgerVariableKey.of(judgerVariable.getKey()),
                    judgerVariable.getValueType(),
                    judgerVariable.getStringValue(),
                    judgerVariable.getLongValue(),
                    judgerVariable.getDoubleValue(),
                    judgerVariable.getBooleanValue(),
                    judgerVariable.getDateValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonJudgerVariableKey key;

    @JSONField(name = "value_type", ordinal = 2)
    private int valueType;

    @JSONField(name = "string_value", ordinal = 3)
    private String stringValue;

    @JSONField(name = "long_value", ordinal = 4, serializeUsing = ToStringSerializer.class)
    private Long longValue;

    @JSONField(name = "double_value", ordinal = 5)
    private Double doubleValue;

    @JSONField(name = "boolean_value", ordinal = 6)
    private Boolean booleanValue;

    @JSONField(name = "date_value", ordinal = 7)
    private Date dateValue;

    public JSFixedFastJsonJudgerVariable() {
    }

    public JSFixedFastJsonJudgerVariable(
            JSFixedFastJsonJudgerVariableKey key, int valueType, String stringValue, Long longValue,
            Double doubleValue, Boolean booleanValue, Date dateValue
    ) {
        this.key = key;
        this.valueType = valueType;
        this.stringValue = stringValue;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
        this.booleanValue = booleanValue;
        this.dateValue = dateValue;
    }

    public JSFixedFastJsonJudgerVariableKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonJudgerVariableKey key) {
        this.key = key;
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

    @Override
    public String toString() {
        return "JSFixedFastJsonJudgerVariable{" +
                "key=" + key +
                ", valueType=" + valueType +
                ", stringValue='" + stringValue + '\'' +
                ", longValue=" + longValue +
                ", doubleValue=" + doubleValue +
                ", booleanValue=" + booleanValue +
                ", dateValue=" + dateValue +
                '}';
    }
}
