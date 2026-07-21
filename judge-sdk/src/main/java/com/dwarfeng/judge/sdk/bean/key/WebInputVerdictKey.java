package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.key.VerdictKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 部件裁决结果键。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class WebInputVerdictKey implements Key {

    private static final long serialVersionUID = 8672455173723734322L;

    public static VerdictKey toStackBean(WebInputVerdictKey webInputVerdictKey) {
        if (Objects.isNull(webInputVerdictKey)) {
            return null;
        } else {
            return new VerdictKey(
                    webInputVerdictKey.getSectionLongId(),
                    webInputVerdictKey.getDataStringId()
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

    public WebInputVerdictKey() {
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

        WebInputVerdictKey that = (WebInputVerdictKey) o;
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
        return "WebInputVerdictKey{" +
                "sectionLongId=" + sectionLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
