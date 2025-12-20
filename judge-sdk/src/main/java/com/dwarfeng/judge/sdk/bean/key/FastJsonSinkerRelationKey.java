package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 下沉关联主键。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class FastJsonSinkerRelationKey implements Key {

    private static final long serialVersionUID = -6880361800399355924L;

    public static FastJsonSinkerRelationKey of(SinkerRelationKey sinkerRelationKey) {
        if (Objects.isNull(sinkerRelationKey)) {
            return null;
        } else {
            return new FastJsonSinkerRelationKey(
                    sinkerRelationKey.getSectionLongId(),
                    sinkerRelationKey.getSinkerLongId()
            );
        }
    }

    @JSONField(name = "section_id", ordinal = 1)
    private Long sectionLongId;

    @JSONField(name = "sinker_id", ordinal = 2)
    private Long sinkerLongId;

    public FastJsonSinkerRelationKey() {
    }

    public FastJsonSinkerRelationKey(Long sectionLongId, Long sinkerLongId) {
        this.sectionLongId = sectionLongId;
        this.sinkerLongId = sinkerLongId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FastJsonSinkerRelationKey that = (FastJsonSinkerRelationKey) o;

        if (!Objects.equals(sectionLongId, that.sectionLongId))
            return false;
        return Objects.equals(sinkerLongId, that.sinkerLongId);
    }

    @Override
    public int hashCode() {
        int result = sectionLongId != null ? sectionLongId.hashCode() : 0;
        result = 31 * result + (sinkerLongId != null ? sinkerLongId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FastJsonSinkerRelationKey{" +
                "sectionLongId=" + sectionLongId +
                ", sinkerLongId=" + sinkerLongId +
                '}';
    }
} 
