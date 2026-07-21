package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AlarmSetting;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 报警设置。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class FastJsonAlarmSetting implements Bean {

    private static final long serialVersionUID = 8171057018681975315L;

    public static FastJsonAlarmSetting of(AlarmSetting alarmSetting) {
        if (Objects.isNull(alarmSetting)) {
            return null;
        } else {
            return new FastJsonAlarmSetting(
                    FastJsonLongIdKey.of(alarmSetting.getKey()),
                    FastJsonLongIdKey.of(alarmSetting.getSectionKey()),
                    alarmSetting.getDataId(),
                    alarmSetting.getThreshold(),
                    alarmSetting.getMessage(),
                    alarmSetting.getType(),
                    alarmSetting.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private FastJsonLongIdKey sectionKey;

    @JSONField(name = "data_id", ordinal = 3)
    private String dataId;

    @JSONField(name = "threshold", ordinal = 4)
    private double threshold;

    @JSONField(name = "message", ordinal = 5)
    private String message;

    @JSONField(name = "type", ordinal = 6)
    private String type;

    @JSONField(name = "remark", ordinal = 7)
    private String remark;

    public FastJsonAlarmSetting() {
    }

    public FastJsonAlarmSetting(
            FastJsonLongIdKey key, FastJsonLongIdKey sectionKey, String dataId, double threshold, String message,
            String type, String remark
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.dataId = dataId;
        this.threshold = threshold;
        this.message = message;
        this.type = type;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(FastJsonLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonAlarmSetting{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", dataId='" + dataId + '\'' +
                ", threshold=" + threshold +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
