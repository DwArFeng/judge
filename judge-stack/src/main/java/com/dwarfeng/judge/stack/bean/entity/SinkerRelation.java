package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 下沉器关联信息。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class SinkerRelation implements Entity<SinkerRelationKey> {

    private static final long serialVersionUID = 6032532615077392727L;

    private SinkerRelationKey key;
    private boolean enabled;
    private String remark;

    public SinkerRelation() {
    }

    public SinkerRelation(SinkerRelationKey key, boolean enabled, String remark) {
        this.key = key;
        this.enabled = enabled;
        this.remark = remark;
    }

    @Override
    public SinkerRelationKey getKey() {
        return key;
    }

    @Override
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
        return "SinkerRelation{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
