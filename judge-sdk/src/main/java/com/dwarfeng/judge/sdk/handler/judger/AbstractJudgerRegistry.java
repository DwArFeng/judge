package com.dwarfeng.judge.sdk.handler.judger;

import com.dwarfeng.judge.sdk.handler.JudgerMaker;
import com.dwarfeng.judge.sdk.handler.JudgerSupporter;

import java.util.Objects;

/**
 * 抽象判断器注册。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public abstract class AbstractJudgerRegistry implements JudgerMaker, JudgerSupporter {

    protected String judgerType;

    public AbstractJudgerRegistry() {
    }

    public AbstractJudgerRegistry(String judgerType) {
        this.judgerType = judgerType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(judgerType, type);
    }

    @Override
    public String provideType() {
        return judgerType;
    }

    public String getJudgerType() {
        return judgerType;
    }

    public void setJudgerType(String judgerType) {
        this.judgerType = judgerType;
    }

    @Override
    public String toString() {
        return "AbstractJudgerRegistry{" +
                "judgerType='" + judgerType + '\'' +
                '}';
    }
}
