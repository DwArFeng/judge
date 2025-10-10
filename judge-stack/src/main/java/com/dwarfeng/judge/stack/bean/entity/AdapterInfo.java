package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 适配器信息。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class AdapterInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = 6122608674979608533L;

    private LongIdKey key;
    private boolean enabled;
    private String type;
    private String param;
    private String remark;

    public AdapterInfo() {
    }

    public AdapterInfo(
            LongIdKey key,
            boolean enabled,
            String type,
            String param,
            String remark
    ) {
        this.key = key;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
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
        return "AdapterInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", type=" + type + '\'' +
                ", param=" + param + '\'' +
                ", remark=" + remark + '\'' +
                '}';
    }
}
