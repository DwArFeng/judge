package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 判断器信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JSFixedFastJsonJudgerInfo implements Bean {

    private static final long serialVersionUID = -5680670915166633252L;

    public static JSFixedFastJsonJudgerInfo of(JudgerInfo judgerInfo) {
        if (Objects.isNull(judgerInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonJudgerInfo(
                    JSFixedFastJsonLongIdKey.of(judgerInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(judgerInfo.getSectionKey()),
                    judgerInfo.getIndex(),
                    judgerInfo.isEnabled(),
                    judgerInfo.getType(),
                    judgerInfo.getParam(),
                    judgerInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey sectionKey;

    @JSONField(name = "index", ordinal = 3)
    private int index;

    @JSONField(name = "enabled", ordinal = 4)
    private boolean enabled;

    @JSONField(name = "type", ordinal = 5)
    private String type;

    @JSONField(name = "param", ordinal = 6)
    private String param;

    @JSONField(name = "remark", ordinal = 7)
    private String remark;

    public JSFixedFastJsonJudgerInfo() {
    }

    public JSFixedFastJsonJudgerInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey sectionKey, int index, boolean enabled,
            String type, String param, String remark
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.index = index;
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

    public JSFixedFastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(JSFixedFastJsonLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
        return "JSFixedFastJsonJudgerInfo{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", index=" + index +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
