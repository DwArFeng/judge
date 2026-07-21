package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AlarmSetting;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 报警设置。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class JSFixedFastJsonAlarmSetting implements Bean {

    private static final long serialVersionUID = 322360812691137416L;

    public static JSFixedFastJsonAlarmSetting of(AlarmSetting alarmSetting) {
        if (Objects.isNull(alarmSetting)) {
            return null;
        } else {
            return new JSFixedFastJsonAlarmSetting(
                    JSFixedFastJsonLongIdKey.of(alarmSetting.getKey()),
                    JSFixedFastJsonLongIdKey.of(alarmSetting.getSectionKey()),
                    alarmSetting.getDataId(),
                    alarmSetting.getThreshold(),
                    alarmSetting.getMessage(),
                    alarmSetting.getType(),
                    alarmSetting.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey sectionKey;

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

    public JSFixedFastJsonAlarmSetting() {
    }

    public JSFixedFastJsonAlarmSetting(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey sectionKey, String dataId, double threshold,
            String message, String type, String remark
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.dataId = dataId;
        this.threshold = threshold;
        this.message = message;
        this.type = type;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(JSFixedFastJsonLongIdKey sectionKey) {
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
        return "JSFixedFastJsonAlarmSetting{" +
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
