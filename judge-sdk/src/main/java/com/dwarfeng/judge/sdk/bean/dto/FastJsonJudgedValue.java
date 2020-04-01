package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;
import java.util.Objects;

public class FastJsonJudgedValue implements Dto {

    private static final long serialVersionUID = 7057049183015803910L;

    public static FastJsonJudgedValue of(JudgedValue judgedValue) {
        if (Objects.isNull(judgedValue)) {
            return null;
        }

        return new FastJsonJudgedValue(
                FastJsonLongIdKey.of(judgedValue.getJudgerKey()),
                FastJsonJudgementInfo.of(judgedValue.getJudgementInfo()),
                judgedValue.getHappenedDate()
        );
    }

    public static JudgedValue toStackBean(FastJsonJudgedValue fastJsonJudgedValue) {
        if (Objects.isNull(fastJsonJudgedValue)) {
            return null;
        }

        return new JudgedValue(
                new LongIdKey(fastJsonJudgedValue.getJudgerKey().getLongId()),
                FastJsonJudgementInfo.toStackBean(fastJsonJudgedValue.getJudgementInfo()),
                fastJsonJudgedValue.getHappenedDate()
        );
    }

    @JSONField(name = "judger_key", ordinal = 1)
    private FastJsonLongIdKey judgerKey;

    @JSONField(name = "judgement_info", ordinal = 2)
    private FastJsonJudgementInfo judgementInfo;

    @JSONField(name = "happened_date", ordinal = 3)
    private Date happenedDate;

    public FastJsonJudgedValue() {
    }

    public FastJsonJudgedValue(FastJsonLongIdKey judgerKey, FastJsonJudgementInfo judgementInfo, Date happenedDate) {
        this.judgerKey = judgerKey;
        this.judgementInfo = judgementInfo;
        this.happenedDate = happenedDate;
    }

    public FastJsonLongIdKey getJudgerKey() {
        return judgerKey;
    }

    public void setJudgerKey(FastJsonLongIdKey judgerKey) {
        this.judgerKey = judgerKey;
    }

    public FastJsonJudgementInfo getJudgementInfo() {
        return judgementInfo;
    }

    public void setJudgementInfo(FastJsonJudgementInfo judgementInfo) {
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
        return "FastJsonJudgedValue{" +
                "judgerKey=" + judgerKey +
                ", judgementInfo=" + judgementInfo +
                ", happenedDate=" + happenedDate +
                '}';
    }
}
