package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.judge.stack.bean.key.VerdictKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 部件裁决结果键。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class JSFixedFastJsonVerdictKey implements Key {

    private static final long serialVersionUID = -483606499881609476L;

    public static JSFixedFastJsonVerdictKey of(VerdictKey verdictKey) {
        if (Objects.isNull(verdictKey)) {
            return null;
        } else {
            return new JSFixedFastJsonVerdictKey(
                    verdictKey.getSectionLongId(),
                    verdictKey.getDataStringId()
            );
        }
    }

    @JSONField(name = "section_long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long sectionLongId;

    @JSONField(name = "data_string_id", ordinal = 2)
    private String dataStringId;

    public JSFixedFastJsonVerdictKey() {
    }

    public JSFixedFastJsonVerdictKey(Long sectionLongId, String dataStringId) {
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

        JSFixedFastJsonVerdictKey that = (JSFixedFastJsonVerdictKey) o;
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
        return "JSFixedFastJsonVerdictKey{" +
                "sectionLongId=" + sectionLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
