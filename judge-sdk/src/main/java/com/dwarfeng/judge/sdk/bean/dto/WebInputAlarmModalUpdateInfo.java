package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.dto.AlarmModalUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 报警模态更新信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class WebInputAlarmModalUpdateInfo implements Dto {

    private static final long serialVersionUID = 6406861231315806325L;

    public static AlarmModalUpdateInfo toStackBean(WebInputAlarmModalUpdateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AlarmModalUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSectionKey()),
                    webInput.getDataId(),
                    webInput.isAlarming(),
                    webInput.getHappenedDate(),
                    webInput.getMessage(),
                    webInput.getType(),
                    webInput.getVerdictValue(),
                    webInput.getVerdictMessage()
            );
        }
    }

    @JSONField(name = "section_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sectionKey;

    @JSONField(name = "data_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String dataId;

    @JSONField(name = "alarming")
    private boolean alarming;

    @JSONField(name = "happened_date")
    @NotNull
    private Date happenedDate;

    @JSONField(name = "message")
    @Length(max = Constraints.LENGTH_MESSAGE)
    private String message;

    @JSONField(name = "type")
    @Length(max = Constraints.LENGTH_TYPE)
    private String type;

    @JSONField(name = "verdict_value")
    private double verdictValue;

    @JSONField(name = "verdict_message")
    @Length(max = Constraints.LENGTH_MESSAGE)
    private String verdictMessage;

    public WebInputAlarmModalUpdateInfo() {
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

    public boolean isAlarming() {
        return alarming;
    }

    public void setAlarming(boolean alarming) {
        this.alarming = alarming;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
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

    public double getVerdictValue() {
        return verdictValue;
    }

    public void setVerdictValue(double verdictValue) {
        this.verdictValue = verdictValue;
    }

    public String getVerdictMessage() {
        return verdictMessage;
    }

    public void setVerdictMessage(String verdictMessage) {
        this.verdictMessage = verdictMessage;
    }

    @Override
    public String toString() {
        return "WebInputAlarmModalUpdateInfo{" +
                "sectionKey=" + sectionKey +
                ", dataId='" + dataId + '\'' +
                ", alarming=" + alarming +
                ", happenedDate=" + happenedDate +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", verdictValue=" + verdictValue +
                ", verdictMessage='" + verdictMessage + '\'' +
                '}';
    }
}
