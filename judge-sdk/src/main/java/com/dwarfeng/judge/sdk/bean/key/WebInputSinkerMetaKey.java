package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 下沉元数据主键。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class WebInputSinkerMetaKey implements Key {

    private static final long serialVersionUID = -3649282314754101858L;

    public static SinkerMetaKey toStackBean(WebInputSinkerMetaKey webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerMetaKey(
                    webInput.getSectionLongId(),
                    webInput.getSinkerLongId(),
                    webInput.getMetaStringId()
            );
        }
    }

    @JSONField(name = "section_id")
    @NotNull
    private Long sectionLongId;

    @JSONField(name = "sinker_id")
    @NotNull
    private Long sinkerLongId;

    @JSONField(name = "meta_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String metaStringId;

    public WebInputSinkerMetaKey() {
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

        WebInputSinkerMetaKey that = (WebInputSinkerMetaKey) o;
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
        return "WebInputSinkerMetaKey{" +
                "sectionLongId=" + sectionLongId +
                ", sinkerLongId=" + sinkerLongId +
                ", metaStringId='" + metaStringId + '\'' +
                '}';
    }
}
