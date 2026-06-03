package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.sdk.util.ValidVariableValueType;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableUpsertInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 下沉器变量插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputSinkerVariableUpsertInfo implements Bean {

    private static final long serialVersionUID = 2169897662770315013L;

    public static SinkerVariableUpsertInfo toStackBean(WebInputSinkerVariableUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerVariableUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSinkerInfoKey()),
                    webInput.getSinkerVariableId(),
                    webInput.getValueType(),
                    webInput.getValue()
            );
        }
    }

    @JSONField(name = "sinker_info_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sinkerInfoKey;

    @JSONField(name = "sinker_variable_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String sinkerVariableId;

    @JSONField(name = "value_type")
    @ValidVariableValueType
    private int valueType;

    /**
     * 变量值。
     *
     * <p>
     * 需要注意的是，许多序列化工具并不能很好地支持 Object 类型的字段的序列化与反序列化，
     * 因此在使用该字段时需要注意可能出现的问题。
     *
     * <p>
     * 如果有必要，建议开发人员为自己的项目定制专门的 WebInput 类，并应用明确的类型和转换规则，
     * 以避免使用通用的 Object 类型所带来的潜在问题。
     */
    @JSONField(name = "value")
    private Object value;

    public WebInputSinkerVariableUpsertInfo() {
    }

    public WebInputLongIdKey getSinkerInfoKey() {
        return sinkerInfoKey;
    }

    public void setSinkerInfoKey(WebInputLongIdKey sinkerInfoKey) {
        this.sinkerInfoKey = sinkerInfoKey;
    }

    public String getSinkerVariableId() {
        return sinkerVariableId;
    }

    public void setSinkerVariableId(String sinkerVariableId) {
        this.sinkerVariableId = sinkerVariableId;
    }

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WebInputSinkerVariableUpsertInfo{" +
                "sinkerInfoKey=" + sinkerInfoKey +
                ", sinkerVariableId='" + sinkerVariableId + '\'' +
                ", valueType=" + valueType +
                ", value=" + value +
                '}';
    }
}
