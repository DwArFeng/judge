package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Objects;

/**
 * WebInput 下沉器信息。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class WebInputSinkerInfo implements Bean {

    private static final long serialVersionUID = -7259571175438430042L;

    public static SinkerInfo toStackBean(WebInputSinkerInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerInfo(
                    webInput.getKey(),
                    webInput.isEnabled(),
                    webInput.getType(),
                    webInput.getParam(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    private LongIdKey key;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "param")
    private String param;

    @JSONField(name = "remark")
    private String remark;

    public WebInputSinkerInfo() {
    }

    public LongIdKey getKey() {
        return key;
    }

    public void setKey(LongIdKey key) {
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
        return "WebInputSinkerInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", type=" + type + '\'' +
                ", param=" + param + '\'' +
                ", remark=" + remark + '\'' +
                '}';
    }
}

