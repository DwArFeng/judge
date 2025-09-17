package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 判断器变量查看信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JudgerVariableInspectInfo implements Dto {

    private static final long serialVersionUID = -7839834253735271787L;

    private LongIdKey judgerInfoKey;
    private String judgerVariableId;

    public JudgerVariableInspectInfo() {
    }

    public JudgerVariableInspectInfo(LongIdKey judgerInfoKey, String judgerVariableId) {
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
        return "JudgerVariableInspectInfo{" +
                "judgerInfoKey=" + judgerInfoKey +
                ", judgerVariableId='" + judgerVariableId + '\'' +
                '}';
    }
}
