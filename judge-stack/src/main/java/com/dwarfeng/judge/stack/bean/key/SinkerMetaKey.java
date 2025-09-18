package com.dwarfeng.judge.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 下沉元数据主键。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerMetaKey implements Key {

    private static final long serialVersionUID = 3308892807648813198L;

    private Long sectionLongId;
    private Long sinkerLongId;
    private String metaStringId;

    public SinkerMetaKey() {
    }

    public SinkerMetaKey(Long sectionLongId, Long sinkerLongId, String metaStringId) {
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

        SinkerMetaKey that = (SinkerMetaKey) o;
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
        return "SinkerMetaKey{" +
                "sectionLongId=" + sectionLongId +
                ", sinkerLongId=" + sinkerLongId +
                ", metaStringId='" + metaStringId + '\'' +
                '}';
    }
}
