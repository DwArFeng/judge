package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.SinkerMetaKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 下沉器关联元数据。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class SinkerMeta implements Entity<SinkerMetaKey> {

    private static final long serialVersionUID = 6845143550586853797L;

    private SinkerMetaKey key;
    private String value;
    private String remark;

    public SinkerMeta() {
    }

    public SinkerMeta(SinkerMetaKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    @Override
    public SinkerMetaKey getKey() {
        return key;
    }

    @Override
    public void setKey(SinkerMetaKey key) {
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
        return "SinkerMeta{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
