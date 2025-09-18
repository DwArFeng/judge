package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonSinkerMetaKey;
import com.dwarfeng.judge.stack.bean.entity.SinkerMeta;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 下沉关联元数据。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class FastJsonSinkerMeta implements Bean {

    private static final long serialVersionUID = -3438744952944354399L;

    public static FastJsonSinkerMeta of(SinkerMeta sinkerMeta) {
        if (Objects.isNull(sinkerMeta)) {
            return null;
        } else {
            return new FastJsonSinkerMeta(
                    FastJsonSinkerMetaKey.of(sinkerMeta.getKey()),
                    sinkerMeta.getValue(),
                    sinkerMeta.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonSinkerMetaKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonSinkerMeta() {
    }

    public FastJsonSinkerMeta(FastJsonSinkerMetaKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    public FastJsonSinkerMetaKey getKey() {
        return key;
    }

    public void setKey(FastJsonSinkerMetaKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonSinkerMeta{" +
                "key=" + key +
                ", value=" + value + '\'' +
                ", remark=" + remark + '\'' +
                '}';
    }
}

