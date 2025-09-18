package com.dwarfeng.judge.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

public class HibernateSinkerMetaIndicatorKey implements Key {

    private static final long serialVersionUID = -4225688121642661166L;

    private String sinkerTypeStringId;
    private String metaStringId;

    public HibernateSinkerMetaIndicatorKey() {
    }

    public HibernateSinkerMetaIndicatorKey(String sinkerTypeStringId, String metaStringId) {
        this.sinkerTypeStringId = sinkerTypeStringId;
        this.metaStringId = metaStringId;
    }

    public String getSinkerTypeStringId() {
        return sinkerTypeStringId;
    }

    public void setSinkerTypeStringId(String sinkerTypeStringId) {
        this.sinkerTypeStringId = sinkerTypeStringId;
    }

    public String getMetaStringId() {
        return metaStringId;
    }

    public void setMetaStringId(String metaStringId) {
        this.metaStringId = metaStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        HibernateSinkerMetaIndicatorKey that = (HibernateSinkerMetaIndicatorKey) o;
        return Objects.equals(sinkerTypeStringId, that.sinkerTypeStringId) &&
                Objects.equals(metaStringId, that.metaStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(sinkerTypeStringId);
        result = 31 * result + Objects.hashCode(metaStringId);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateSinkerMetaIndicatorKey{" +
                "sinkerTypeStringId='" + sinkerTypeStringId + '\'' +
                ", metaStringId='" + metaStringId + '\'' +
                '}';
    }
}
