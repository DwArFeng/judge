package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonSinkerMetaKey;
import com.dwarfeng.judge.stack.bean.entity.SinkerMeta;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixedFastJson 下沉关联元数据。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class JSFixedFastJsonSinkerMeta implements Bean {

    private static final long serialVersionUID = 2686848891934833491L;

    public static JSFixedFastJsonSinkerMeta of(SinkerMeta sinkerMeta) {
        if (Objects.isNull(sinkerMeta)) {
            return null;
        } else {
            return new JSFixedFastJsonSinkerMeta(
                    JSFixedFastJsonSinkerMetaKey.of(sinkerMeta.getKey()),
                    sinkerMeta.getValue(),
                    sinkerMeta.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonSinkerMetaKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonSinkerMeta() {
    }

    public JSFixedFastJsonSinkerMeta(JSFixedFastJsonSinkerMetaKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    public JSFixedFastJsonSinkerMetaKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonSinkerMetaKey key) {
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
        return "JSFixedFastJsonSinkerMeta{" +
                "key=" + key +
                ", value=" + value + '\'' +
                ", remark=" + remark + '\'' +
                '}';
    }
} 
