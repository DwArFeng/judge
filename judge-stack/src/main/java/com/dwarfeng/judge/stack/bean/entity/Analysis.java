package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

import java.util.Date;

/**
 * 分析结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class Analysis implements Entity<AnalysisKey> {

    private static final long serialVersionUID = 3726421394355235168L;

    private AnalysisKey key;

    /**
     * 数据类型。
     *
     * <p>
     * int 枚举，可能的状态为：
     * <ul>
     *     <li>文本</li>
     *     <li>整数</li>
     *     <li>浮点数</li>
     *     <li>布尔值</li>
     *     <li>日期值</li>
     *     <li>图片</li>
     *     <li>图片包</li>
     *     <li>文件</li>
     *     <li>文件包</li>
     * </ul>
     * 详细值参考 sdk 模块的常量工具类。
     */
    private int dataType;

    private String stringValue;
    private Long longValue;
    private Double doubleValue;
    private Boolean booleanValue;
    private Date dateValue;
    private Long pictureValue;
    private Long picturePackValue;
    private Long fileValue;
    private Long filePackValue;

    public Analysis() {
    }

    public Analysis(
            AnalysisKey key, int dataType, String stringValue, Long longValue, Double doubleValue,
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

    @Override
    public AnalysisKey getKey() {
        return key;
    }

    @Override
    public void setKey(AnalysisKey key) {
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
        return "Analysis{" +
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
