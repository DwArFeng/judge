package com.dwarfeng.judge.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

public class HibernateSinkerMetaKey implements Key {

    private static final long serialVersionUID = 701687865748531346L;

    private Long sectionLongId;
    private Long sinkerLongId;
    private String metaStringId;

    public HibernateSinkerMetaKey() {
    }

    public HibernateSinkerMetaKey(Long sectionLongId, Long sinkerLongId, String metaStringId) {
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

        HibernateSinkerMetaKey that = (HibernateSinkerMetaKey) o;
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
        return "HibernateSinkerMetaKey{" +
                "sectionLongId=" + sectionLongId +
                ", sinkerLongId=" + sinkerLongId +
                ", metaStringId='" + metaStringId + '\'' +
                '}';
    }
}
