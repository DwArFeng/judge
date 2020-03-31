package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 判断结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JudgedValue implements Dto {

    private static final long serialVersionUID = 1576579760877791081L;

    private LongIdKey judgerKey;
    private JudgementInfo judgementInfo;
    private Date happenedDate;

    public JudgedValue() {
    }

    public JudgedValue(LongIdKey judgerKey, JudgementInfo judgementInfo, Date happenedDate) {
        this.judgerKey = judgerKey;
        this.judgementInfo = judgementInfo;
        this.happenedDate = happenedDate;
    }

    public LongIdKey getJudgerKey() {
        return judgerKey;
    }

    public void setJudgerKey(LongIdKey judgerKey) {
        this.judgerKey = judgerKey;
    }

    public JudgementInfo getJudgementInfo() {
        return judgementInfo;
    }

    public void setJudgementInfo(JudgementInfo judgementInfo) {
        this.judgementInfo = judgementInfo;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    @Override
    public String toString() {
        return "JudgedValue{" +
                "judgerKey=" + judgerKey +
                ", judgementInfo=" + judgementInfo +
                ", happenedDate=" + happenedDate +
                '}';
    }
}
