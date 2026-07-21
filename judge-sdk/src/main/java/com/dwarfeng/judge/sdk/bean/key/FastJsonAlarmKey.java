package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 报警键。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class FastJsonAlarmKey implements Key {

    private static final long serialVersionUID = -3579486630742104403L;

    public static FastJsonAlarmKey of(AlarmKey alarmKey) {
        if (Objects.isNull(alarmKey)) {
            return null;
        } else {
            return new FastJsonAlarmKey(
                    alarmKey.getSectionLongId(),
                    alarmKey.getDataStringId()
            );
        }
    }

    @JSONField(name = "section_long_id", ordinal = 1)
    private Long sectionLongId;

    @JSONField(name = "data_string_id", ordinal = 2)
    private String dataStringId;

    public FastJsonAlarmKey() {
    }

    public FastJsonAlarmKey(Long sectionLongId, String dataStringId) {
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

        FastJsonAlarmKey that = (FastJsonAlarmKey) o;
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
        return "FastJsonAlarmKey{" +
                "sectionLongId=" + sectionLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
