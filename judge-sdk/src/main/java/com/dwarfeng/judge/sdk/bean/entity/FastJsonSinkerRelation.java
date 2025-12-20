package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 下沉器关联信息。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class FastJsonSinkerRelation implements Bean {

    private static final long serialVersionUID = -4499200372698687577L;

    public static FastJsonSinkerRelation of(SinkerRelation sinkerRelation) {
        if (Objects.isNull(sinkerRelation)) {
            return null;
        } else {
            return new FastJsonSinkerRelation(
                    sinkerRelation.getKey(),
                    sinkerRelation.isEnabled(),
                    sinkerRelation.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private SinkerRelationKey key;

    @JSONField(name = "enabled", ordinal = 2)
    private boolean enabled;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonSinkerRelation() {
    }

    public FastJsonSinkerRelation(SinkerRelationKey key, boolean enabled, String remark) {
        this.key = key;
        this.enabled = enabled;
        this.remark = remark;
    }

    public SinkerRelationKey getKey() {
        return key;
    }

    public void setKey(SinkerRelationKey key) {
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
        return "FastJsonSinkerRelation{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", remark=" + remark + '\'' +
                '}';
    }
} 
