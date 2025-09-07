package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 判断器变量插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JudgerVariableUpsertInfo implements Dto {

    private static final long serialVersionUID = 8415487653520969715L;

    private LongIdKey judgerInfoKey;
    private String judgerVariableId;
    private String value;

    public JudgerVariableUpsertInfo() {
    }

    public JudgerVariableUpsertInfo(LongIdKey judgerInfoKey, String judgerVariableId, String value) {
        this.judgerInfoKey = judgerInfoKey;
        this.judgerVariableId = judgerVariableId;
        this.value = value;
    }

    public LongIdKey getJudgerInfoKey() {
        return judgerInfoKey;
    }

    public void setJudgerInfoKey(LongIdKey judgerInfoKey) {
        this.judgerInfoKey = judgerInfoKey;
    }

    public String getJudgerVariableId() {
        return judgerVariableId;
    }

    public void setJudgerVariableId(String judgerVariableId) {
        this.judgerVariableId = judgerVariableId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "JudgerVariableUpsertInfo{" +
                "judgerInfoKey=" + judgerInfoKey +
                ", judgerVariableId='" + judgerVariableId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
