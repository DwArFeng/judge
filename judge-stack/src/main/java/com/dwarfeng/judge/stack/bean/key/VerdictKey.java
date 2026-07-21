package com.dwarfeng.judge.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 部件裁决结果键。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class VerdictKey implements Key {

    private static final long serialVersionUID = -3516480333733943405L;

    private Long sectionLongId;
    private String dataStringId;

    public VerdictKey() {
    }

    public VerdictKey(Long sectionLongId, String dataStringId) {
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

        VerdictKey that = (VerdictKey) o;
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
        return "VerdictKey{" +
                "sectionLongId=" + sectionLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
