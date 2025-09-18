package com.dwarfeng.judge.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 下沉元数据指示器主键。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerMetaIndicatorKey implements Key {

    private static final long serialVersionUID = -2590064443801674095L;

    private String sinkerTypeStringId;
    private String metaStringId;

    public SinkerMetaIndicatorKey() {
    }

    public SinkerMetaIndicatorKey(String sinkerTypeStringId, String metaStringId) {
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

        SinkerMetaIndicatorKey that = (SinkerMetaIndicatorKey) o;
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
        return "SinkerMetaIndicatorKey{" +
                "sinkerTypeStringId='" + sinkerTypeStringId + '\'' +
                ", metaStringId='" + metaStringId + '\'' +
                '}';
    }
}
