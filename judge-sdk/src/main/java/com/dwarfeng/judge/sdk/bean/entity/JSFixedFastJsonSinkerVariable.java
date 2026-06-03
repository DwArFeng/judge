package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonSinkerVariableKey;
import com.dwarfeng.judge.stack.bean.entity.SinkerVariable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 下沉器变量。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JSFixedFastJsonSinkerVariable implements Bean {

    private static final long serialVersionUID = 8557544374499554942L;

    public static JSFixedFastJsonSinkerVariable of(SinkerVariable sinkerVariable) {
        if (Objects.isNull(sinkerVariable)) {
            return null;
        } else {
            return new JSFixedFastJsonSinkerVariable(
                    JSFixedFastJsonSinkerVariableKey.of(sinkerVariable.getKey()),
                    sinkerVariable.getValueType(),
                    sinkerVariable.getStringValue(),
                    sinkerVariable.getLongValue(),
                    sinkerVariable.getDoubleValue(),
                    sinkerVariable.getBooleanValue(),
                    sinkerVariable.getDateValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonSinkerVariableKey key;

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

    public JSFixedFastJsonSinkerVariable() {
    }

    public JSFixedFastJsonSinkerVariable(
            JSFixedFastJsonSinkerVariableKey key, int valueType, String stringValue, Long longValue,
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

    public JSFixedFastJsonSinkerVariableKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonSinkerVariableKey key) {
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
        return "JSFixedFastJsonSinkerVariable{" +
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
