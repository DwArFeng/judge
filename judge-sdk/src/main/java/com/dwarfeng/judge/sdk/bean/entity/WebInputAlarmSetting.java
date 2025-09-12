package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.sdk.util.ValidThreshold;
import com.dwarfeng.judge.stack.bean.entity.AlarmSetting;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 报警设置。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputAlarmSetting implements Bean {

    private static final long serialVersionUID = 8121225188288071284L;

    public static AlarmSetting toStackBean(WebInputAlarmSetting webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AlarmSetting(
                    WebInputLongIdKey.toStackBean(webInput.getKey()),
                    WebInputLongIdKey.toStackBean(webInput.getSectionKey()),
                    webInput.isEnabled(),
                    webInput.getThreshold(),
                    webInput.getAlarmMessage(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "section_key")
    @Valid
    private WebInputLongIdKey sectionKey;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "threshold")
    @ValidThreshold
    private double threshold;

    @JSONField(name = "alarm_message")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_MESSAGE)
    private String alarmMessage;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputAlarmSetting() {
    }

    public WebInputAlarmSetting(
            WebInputLongIdKey key, WebInputLongIdKey sectionKey, boolean enabled, double threshold,
            String alarmMessage, String remark
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.enabled = enabled;
        this.threshold = threshold;
        this.alarmMessage = alarmMessage;
        this.remark = remark;
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(WebInputLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputAlarmSetting{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", enabled=" + enabled +
                ", threshold=" + threshold +
                ", alarmMessage='" + alarmMessage + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
