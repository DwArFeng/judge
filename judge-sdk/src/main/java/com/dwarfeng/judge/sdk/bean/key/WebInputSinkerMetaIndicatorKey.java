package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 下沉元数据指示器主键。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class WebInputSinkerMetaIndicatorKey implements Key {

    private static final long serialVersionUID = 4622908519966307078L;

    public static SinkerMetaIndicatorKey toStackBean(WebInputSinkerMetaIndicatorKey webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerMetaIndicatorKey(
                    webInput.getSinkerTypeStringId(),
                    webInput.getMetaStringId()
            );
        }
    }

    @JSONField(name = "sinker_type_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_TYPE)
    private String sinkerTypeStringId;

    @JSONField(name = "meta_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String metaStringId;

    public WebInputSinkerMetaIndicatorKey() {
    }

    public String getSinkerTypeStringId() {
        return sinkerTypeStringId;
    }

    public void setSinkerTypeStringId(String sinkerTypeStringId) {
        this.sinkerTypeStringId = sinkerTypeStringId;
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

        WebInputSinkerMetaIndicatorKey that = (WebInputSinkerMetaIndicatorKey) o;
        return Objects.equals(sinkerTypeStringId, that.sinkerTypeStringId) &&
                Objects.equals(metaStringId, that.metaStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(sinkerTypeStringId);
        result = 31 * result + Objects.hashCode(metaStringId);
        return result;
    }

    @Override
    public String toString() {
        return "WebInputSinkerMetaIndicatorKey{" +
                "sinkerTypeStringId='" + sinkerTypeStringId + '\'' +
                ", metaStringId='" + metaStringId + '\'' +
                '}';
    }
}
