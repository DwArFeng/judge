package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.sdk.util.ValidVariableValueType;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableUpsertInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析器变量插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputAnalyserVariableUpsertInfo implements Bean {

    private static final long serialVersionUID = 388849440661812618L;

    public static AnalyserVariableUpsertInfo toStackBean(WebInputAnalyserVariableUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalyserVariableUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAnalyserInfoKey()),
                    webInput.getAnalyserVariableId(),
                    webInput.getValueType(),
                    webInput.getValue()
            );
        }
    }

    @JSONField(name = "analyser_info_key")
    @NotNull
    @Valid
    private WebInputLongIdKey analyserInfoKey;

    @JSONField(name = "analyser_variable_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String analyserVariableId;

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

    public WebInputAnalyserVariableUpsertInfo() {
    }

    public WebInputLongIdKey getAnalyserInfoKey() {
        return analyserInfoKey;
    }

    public void setAnalyserInfoKey(WebInputLongIdKey analyserInfoKey) {
        this.analyserInfoKey = analyserInfoKey;
    }

    public String getAnalyserVariableId() {
        return analyserVariableId;
    }

    public void setAnalyserVariableId(String analyserVariableId) {
        this.analyserVariableId = analyserVariableId;
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
        return "WebInputAnalyserVariableUpsertInfo{" +
                "analyserInfoKey=" + analyserInfoKey +
                ", analyserVariableId='" + analyserVariableId + '\'' +
                ", valueType=" + valueType +
                ", value=" + value +
                '}';
    }
}
