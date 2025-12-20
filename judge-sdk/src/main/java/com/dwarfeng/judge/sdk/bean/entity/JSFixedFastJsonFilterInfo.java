package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.FilterInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 过滤器信息。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class JSFixedFastJsonFilterInfo implements Bean {

    private static final long serialVersionUID = -676388490571742188L;

    public static JSFixedFastJsonFilterInfo of(FilterInfo filterInfo) {
        if (Objects.isNull(filterInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonFilterInfo(
                    JSFixedFastJsonLongIdKey.of(filterInfo.getKey()),
                    filterInfo.isEnabled(),
                    filterInfo.getType(),
                    filterInfo.getParam(),
                    filterInfo.getRemark()
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

    public JSFixedFastJsonFilterInfo() {
    }

    public JSFixedFastJsonFilterInfo(
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
        return "JSFixedFastJsonFilterInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", type=" + type + '\'' +
                ", param=" + param + '\'' +
                ", remark=" + remark + '\'' +
                '}';
    }
} 
