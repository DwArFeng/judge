package com.dwarfeng.judge.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

public class HibernateSinkerRelationKey implements Key {

    private static final long serialVersionUID = 1474389915762867796L;

    private Long sectionLongId;
    private Long sinkerLongId;

    public HibernateSinkerRelationKey() {
    }

    public HibernateSinkerRelationKey(Long sectionLongId, Long sinkerLongId) {
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

        HibernateSinkerRelationKey that = (HibernateSinkerRelationKey) o;

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
        return "HibernateSinkerRelationKey{" +
                "sectionLongId=" + sectionLongId +
                ", sinkerLongId=" + sinkerLongId +
                '}';
    }
}
