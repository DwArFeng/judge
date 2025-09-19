package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 提供器信息。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class JSFixedFastJsonProviderInfo implements Bean {

    private static final long serialVersionUID = -4704316808396640813L;

    public static JSFixedFastJsonProviderInfo of(ProviderInfo providerInfo) {
        if (Objects.isNull(providerInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonProviderInfo(
                    JSFixedFastJsonLongIdKey.of(providerInfo.getKey()),
                    providerInfo.isEnabled(),
                    providerInfo.getType(),
                    providerInfo.getParam(),
                    providerInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;
    @JSONField(name = "enabled", ordinal = 2)
    private boolean enabled;
    @JSONField(name = "type", ordinal = 3)
    private String type;
    @JSONField(name = "param", ordinal = 4)
    private String param;
    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonProviderInfo() {
    }

    public JSFixedFastJsonProviderInfo(
            JSFixedFastJsonLongIdKey key,
            boolean enabled,
            String type,
            String param,
            String remark) {
        this.key = key;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
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
        return "JSFixedFastJsonProviderInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", type=" + type + '\'' +
                ", param=" + param + '\'' +
                ", remark=" + remark + '\'' +
                '}';
    }
}

