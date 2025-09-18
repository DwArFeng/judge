package com.dwarfeng.judge.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 下沉关联主键。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class SinkerRelationKey implements Key {

    private static final long serialVersionUID = 6435038115637955093L;

    private Long sectionLongId;
    private Long sinkerLongId;

    public SinkerRelationKey() {
    }

    public SinkerRelationKey(Long sectionLongId, Long sinkerLongId) {
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

        SinkerRelationKey that = (SinkerRelationKey) o;

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
        return "SinkerRelationKey{" +
                "sectionLongId=" + sectionLongId +
                ", sinkerLongId=" + sinkerLongId +
                '}';
    }
}
