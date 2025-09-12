package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.entity.AlarmLevelIndicator;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 报警等级指示器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputAlarmLevelIndicator implements Bean {

    private static final long serialVersionUID = -3676468339445668875L;

    public static AlarmLevelIndicator toStackBean(WebInputAlarmLevelIndicator webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AlarmLevelIndicator(
                    WebInputStringIdKey.toStackBean(webInput.getKey()),
                    webInput.getLabel(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputStringIdKey key;

    @JSONField(name = "label")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_LABEL)
    private String label;

    @JSONField(name = "remark")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputAlarmLevelIndicator() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputAlarmLevelIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
