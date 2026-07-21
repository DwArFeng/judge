package com.dwarfeng.judge.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

public class HibernateVerdictKey implements Key {

    private static final long serialVersionUID = -2819913179007672114L;

    private Long sectionLongId;
    private String dataStringId;

    public HibernateVerdictKey() {
    }

    public HibernateVerdictKey(Long sectionLongId, String dataStringId) {
        this.sectionLongId = sectionLongId;
        this.dataStringId = dataStringId;
    }

    public Long getSectionLongId() {
        return sectionLongId;
    }

    public void setSectionLongId(Long sectionLongId) {
        this.sectionLongId = sectionLongId;
    }

    public String getDataStringId() {
        return dataStringId;
    }

    public void setDataStringId(String dataStringId) {
        this.dataStringId = dataStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        HibernateVerdictKey that = (HibernateVerdictKey) o;
        return Objects.equals(sectionLongId, that.sectionLongId)
                && Objects.equals(dataStringId, that.dataStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(sectionLongId);
        result = 31 * result + Objects.hashCode(dataStringId);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateVerdictKey{" +
                "sectionLongId=" + sectionLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
