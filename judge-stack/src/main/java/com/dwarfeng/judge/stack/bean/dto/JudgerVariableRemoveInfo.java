package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 判断器变量删除信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JudgerVariableRemoveInfo implements Dto {

    private static final long serialVersionUID = 3781208848297211807L;

    private LongIdKey judgerInfoKey;
    private String judgerVariableId;

    public JudgerVariableRemoveInfo() {
    }

    public JudgerVariableRemoveInfo(LongIdKey judgerInfoKey, String judgerVariableId) {
        this.judgerInfoKey = judgerInfoKey;
        this.judgerVariableId = judgerVariableId;
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

    @Override
    public String toString() {
        return "JudgerVariableRemoveInfo{" +
                "judgerInfoKey=" + judgerInfoKey +
                ", judgerVariableId='" + judgerVariableId + '\'' +
                '}';
    }
}
