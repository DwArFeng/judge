package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.sdk.util.ValidVariableValueType;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableUpsertInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 判断器变量插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputJudgerVariableUpsertInfo implements Bean {

    private static final long serialVersionUID = 4554461909138851636L;

    public static JudgerVariableUpsertInfo toStackBean(WebInputJudgerVariableUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new JudgerVariableUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getJudgerInfoKey()),
                    webInput.getJudgerVariableId(),
                    webInput.getValueType(),
                    webInput.getValue()
            );
        }
    }

    @JSONField(name = "judger_info_key")
    @NotNull
    @Valid
    private WebInputLongIdKey judgerInfoKey;

    @JSONField(name = "judger_variable_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String judgerVariableId;

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

    public WebInputJudgerVariableUpsertInfo() {
    }

    public WebInputLongIdKey getJudgerInfoKey() {
        return judgerInfoKey;
    }

    public void setJudgerInfoKey(WebInputLongIdKey judgerInfoKey) {
        this.judgerInfoKey = judgerInfoKey;
    }

    public String getJudgerVariableId() {
        return judgerVariableId;
    }

    public void setJudgerVariableId(String judgerVariableId) {
        this.judgerVariableId = judgerVariableId;
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
        return "WebInputJudgerVariableUpsertInfo{" +
                "judgerInfoKey=" + judgerInfoKey +
                ", judgerVariableId='" + judgerVariableId + '\'' +
                ", valueType=" + valueType +
                ", value=" + value +
                '}';
    }
}
