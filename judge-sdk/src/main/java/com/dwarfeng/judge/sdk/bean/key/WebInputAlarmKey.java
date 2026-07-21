package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 报警键。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class WebInputAlarmKey implements Key {

    private static final long serialVersionUID = -7223485594079473754L;

    public static AlarmKey toStackBean(WebInputAlarmKey webInputAlarmKey) {
        if (Objects.isNull(webInputAlarmKey)) {
            return null;
        } else {
            return new AlarmKey(
                    webInputAlarmKey.getSectionLongId(),
                    webInputAlarmKey.getDataStringId()
            );
        }
    }

    @JSONField(name = "section_long_id")
    @NotNull
    private Long sectionLongId;

    @JSONField(name = "data_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String dataStringId;

    public WebInputAlarmKey() {
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

        WebInputAlarmKey that = (WebInputAlarmKey) o;
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
        return "WebInputAlarmKey{" +
                "sectionLongId=" + sectionLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
