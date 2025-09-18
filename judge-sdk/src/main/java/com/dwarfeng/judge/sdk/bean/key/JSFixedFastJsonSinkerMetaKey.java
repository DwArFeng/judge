package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixedFastJson 下沉元数据主键。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JSFixedFastJsonSinkerMetaKey implements Key {

    private static final long serialVersionUID = 8963780838441139768L;

    public static JSFixedFastJsonSinkerMetaKey of(SinkerMetaKey sinkerMetaKey) {
        if (Objects.isNull(sinkerMetaKey)) {
            return null;
        } else {
            return new JSFixedFastJsonSinkerMetaKey(
                    sinkerMetaKey.getSectionLongId(),
                    sinkerMetaKey.getSinkerLongId(),
                    sinkerMetaKey.getMetaStringId()
            );
        }
    }

    @JSONField(name = "section_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long sectionLongId;

    @JSONField(name = "sinker_id", ordinal = 2)
    private Long sinkerLongId;

    @JSONField(name = "meta_string_id", ordinal = 3)
    private String metaStringId;

    public JSFixedFastJsonSinkerMetaKey() {
    }

    public JSFixedFastJsonSinkerMetaKey(Long sectionLongId, Long sinkerLongId, String metaStringId) {
        this.sectionLongId = sectionLongId;
        this.sinkerLongId = sinkerLongId;
        this.metaStringId = metaStringId;
    }

    public Long getSectionLongId() {
        return sectionLongId;
    }

    public void setSectionLongId(Long sectionLongId) {
        this.sectionLongId = sectionLongId;
    }

    public Long getSinkerLongId() {
        return sinkerLongId;
    }

    public void setSinkerLongId(Long sinkerLongId) {
        this.sinkerLongId = sinkerLongId;
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

        JSFixedFastJsonSinkerMetaKey that = (JSFixedFastJsonSinkerMetaKey) o;
        return Objects.equals(sectionLongId, that.sectionLongId) && Objects.equals(sinkerLongId, that.sinkerLongId) &&
                Objects.equals(metaStringId, that.metaStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(sectionLongId);
        result = 31 * result + Objects.hashCode(sinkerLongId);
        result = 31 * result + Objects.hashCode(metaStringId);
        return result;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonSinkerMetaKey{" +
                "sectionLongId=" + sectionLongId +
                ", sinkerLongId=" + sinkerLongId +
                ", metaStringId='" + metaStringId + '\'' +
                '}';
    }
}
