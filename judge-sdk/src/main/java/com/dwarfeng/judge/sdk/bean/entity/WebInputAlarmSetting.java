package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
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
 * @since 3.2.0
 */
public class WebInputAlarmSetting implements Bean {

    private static final long serialVersionUID = 9190849121128321259L;

    public static AlarmSetting toStackBean(WebInputAlarmSetting webInputAlarmSetting) {
        if (Objects.isNull(webInputAlarmSetting)) {
            return null;
        } else {
            return new AlarmSetting(
                    WebInputLongIdKey.toStackBean(webInputAlarmSetting.getKey()),
                    WebInputLongIdKey.toStackBean(webInputAlarmSetting.getSectionKey()),
                    webInputAlarmSetting.getDataId(),
                    webInputAlarmSetting.getThreshold(),
                    webInputAlarmSetting.getMessage(),
                    webInputAlarmSetting.getType(),
                    webInputAlarmSetting.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "section_key")
    @Valid
    private WebInputLongIdKey sectionKey;

    @JSONField(name = "data_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String dataId;

    @JSONField(name = "threshold")
    private double threshold;

    @JSONField(name = "message")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_MESSAGE)
    private String message;

    @JSONField(name = "type")
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String type;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputAlarmSetting() {
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
        return "WebInputAlarmSetting{" +
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
