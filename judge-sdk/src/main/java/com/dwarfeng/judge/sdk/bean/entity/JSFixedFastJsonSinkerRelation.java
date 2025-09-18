package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonSinkerRelationKey;
import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixedFastJson 下沉器关联信息。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class JSFixedFastJsonSinkerRelation implements Bean {

    private static final long serialVersionUID = -8452732865063452382L;

    public static JSFixedFastJsonSinkerRelation of(SinkerRelation sinkerRelation) {
        if (Objects.isNull(sinkerRelation)) {
            return null;
        } else {
            return new JSFixedFastJsonSinkerRelation(
                    JSFixedFastJsonSinkerRelationKey.of(sinkerRelation.getKey()),
                    sinkerRelation.isEnabled(),
                    sinkerRelation.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonSinkerRelationKey key;

    @JSONField(name = "enabled", ordinal = 2)
    private boolean enabled;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonSinkerRelation() {
    }

    public JSFixedFastJsonSinkerRelation(JSFixedFastJsonSinkerRelationKey key, boolean enabled, String remark) {
        this.key = key;
        this.enabled = enabled;
        this.remark = remark;
    }

    public JSFixedFastJsonSinkerRelationKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonSinkerRelationKey key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonSinkerRelation{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", remark=" + remark + '\'' +
                '}';
    }
}

