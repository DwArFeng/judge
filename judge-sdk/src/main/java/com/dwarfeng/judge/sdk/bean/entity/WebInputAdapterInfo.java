package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.entity.AdapterInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 适配器信息。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class WebInputAdapterInfo implements Bean {

    private static final long serialVersionUID = 6517420226435700365L;

    public static AdapterInfo toStackBean(WebInputAdapterInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AdapterInfo(
                    WebInputLongIdKey.toStackBean(webInput.getKey()),
                    webInput.isEnabled(),
                    webInput.getType(),
                    webInput.getParam(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "param")
    private String param;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputAdapterInfo() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputAdapterInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", type=" + type + '\'' +
                ", param=" + param + '\'' +
                ", remark=" + remark + '\'' +
                '}';
    }
}

