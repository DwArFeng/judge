package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 下沉元数据指示器主键。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class FastJsonSinkerMetaIndicatorKey implements Key {

    private static final long serialVersionUID = 4602829218146527707L;

    public static FastJsonSinkerMetaIndicatorKey of(SinkerMetaIndicatorKey sinkerMetaIndicatorKey) {
        if (Objects.isNull(sinkerMetaIndicatorKey)) {
            return null;
        } else {
            return new FastJsonSinkerMetaIndicatorKey(
                    sinkerMetaIndicatorKey.getSinkerTypeStringId(),
                    sinkerMetaIndicatorKey.getMetaStringId()
            );
        }
    }

    @JSONField(name = "sinker_type_string_id", ordinal = 1)
    private String sinkerTypeStringId;

    @JSONField(name = "meta_string_id", ordinal = 2)
    private String metaStringId;

    public FastJsonSinkerMetaIndicatorKey() {
    }

    public FastJsonSinkerMetaIndicatorKey(String sinkerTypeStringId, String metaStringId) {
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

        FastJsonSinkerMetaIndicatorKey that = (FastJsonSinkerMetaIndicatorKey) o;
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
        return "FastJsonSinkerMetaIndicatorKey{" +
                "sinkerTypeStringId='" + sinkerTypeStringId + '\'' +
                ", metaStringId='" + metaStringId + '\'' +
                '}';
    }
}
