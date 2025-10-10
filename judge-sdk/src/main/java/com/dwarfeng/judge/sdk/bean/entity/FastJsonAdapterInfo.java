package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AdapterInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 适配器信息。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class FastJsonAdapterInfo implements Bean {

    private static final long serialVersionUID = 3864957790197470875L;

    public static FastJsonAdapterInfo of(AdapterInfo adapterInfo) {
        if (Objects.isNull(adapterInfo)) {
            return null;
        } else {
            return new FastJsonAdapterInfo(
                    FastJsonLongIdKey.of(adapterInfo.getKey()),
                    adapterInfo.isEnabled(),
                    adapterInfo.getType(),
                    adapterInfo.getParam(),
                    adapterInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;
    @JSONField(name = "enabled", ordinal = 2)
    private boolean enabled;
    @JSONField(name = "type", ordinal = 3)
    private String type;
    @JSONField(name = "param", ordinal = 4)
    private String param;
    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public FastJsonAdapterInfo() {
    }

    public FastJsonAdapterInfo(
            FastJsonLongIdKey key,
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

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
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
        return "FastJsonAdapterInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", type=" + type + '\'' +
                ", param=" + param + '\'' +
                ", remark=" + remark + '\'' +
                '}';
    }
}

