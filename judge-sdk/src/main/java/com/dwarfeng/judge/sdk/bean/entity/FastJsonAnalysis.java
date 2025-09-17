package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonAnalysisKey;
import com.dwarfeng.judge.stack.bean.entity.Analysis;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 分析结果。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class FastJsonAnalysis implements Bean {

    private static final long serialVersionUID = -8459780478000144048L;

    public static FastJsonAnalysis of(Analysis analysis) {
        if (Objects.isNull(analysis)) {
            return null;
        } else {
            return new FastJsonAnalysis(
                    FastJsonAnalysisKey.of(analysis.getKey()),
                    analysis.getDataType(),
                    analysis.getStringValue(),
                    analysis.getLongValue(),
                    analysis.getDoubleValue(),
                    analysis.getBooleanValue(),
                    analysis.getDateValue(),
                    analysis.getPictureValue(),
                    analysis.getPicturePackValue(),
                    analysis.getFileValue(),
                    analysis.getFilePackValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonAnalysisKey key;

    @JSONField(name = "data_type", ordinal = 2)
    private int dataType;

    @JSONField(name = "string_value", ordinal = 3)
    private String stringValue;

    @JSONField(name = "long_value", ordinal = 4)
    private Long longValue;

    @JSONField(name = "double_value", ordinal = 5)
    private Double doubleValue;

    @JSONField(name = "boolean_value", ordinal = 6)
    private Boolean booleanValue;

    @JSONField(name = "date_value", ordinal = 7)
    private Date dateValue;

    @JSONField(name = "picture_value", ordinal = 8)
    private Long pictureValue;

    @JSONField(name = "picture_pack_value", ordinal = 9)
    private Long picturePackValue;

    @JSONField(name = "file_value", ordinal = 10)
    private Long fileValue;

    @JSONField(name = "file_pack_value", ordinal = 11)
    private Long filePackValue;

    public FastJsonAnalysis() {
    }

    public FastJsonAnalysis(
            FastJsonAnalysisKey key, int dataType, String stringValue, Long longValue, Double doubleValue,
            Boolean booleanValue, Date dateValue, Long pictureValue, Long picturePackValue, Long fileValue,
            Long filePackValue
    ) {
        this.key = key;
        this.dataType = dataType;
        this.stringValue = stringValue;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
        this.booleanValue = booleanValue;
        this.dateValue = dateValue;
        this.pictureValue = pictureValue;
        this.picturePackValue = picturePackValue;
        this.fileValue = fileValue;
        this.filePackValue = filePackValue;
    }

    public FastJsonAnalysisKey getKey() {
        return key;
    }

    public void setKey(FastJsonAnalysisKey key) {
        this.key = key;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
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

    public Long getPictureValue() {
        return pictureValue;
    }

    public void setPictureValue(Long pictureValue) {
        this.pictureValue = pictureValue;
    }

    public Long getPicturePackValue() {
        return picturePackValue;
    }

    public void setPicturePackValue(Long picturePackValue) {
        this.picturePackValue = picturePackValue;
    }

    public Long getFileValue() {
        return fileValue;
    }

    public void setFileValue(Long fileValue) {
        this.fileValue = fileValue;
    }

    public Long getFilePackValue() {
        return filePackValue;
    }

    public void setFilePackValue(Long filePackValue) {
        this.filePackValue = filePackValue;
    }

    @Override
    public String toString() {
        return "FastJsonAnalysis{" +
                "key=" + key +
                ", dataType=" + dataType +
                ", stringValue='" + stringValue + '\'' +
                ", longValue=" + longValue +
                ", doubleValue=" + doubleValue +
                ", booleanValue=" + booleanValue +
                ", dateValue=" + dateValue +
                ", pictureValue=" + pictureValue +
                ", picturePackValue=" + picturePackValue +
                ", fileValue=" + fileValue +
                ", filePackValue=" + filePackValue +
                '}';
    }
}
