package com.dwarfeng.judge.sdk.handler.analyser;

import com.dwarfeng.judge.sdk.handler.AnalyserMaker;
import com.dwarfeng.judge.sdk.handler.AnalyserSupporter;

import java.util.Objects;

/**
 * 抽象分析器注册。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public abstract class AbstractAnalyserRegistry implements AnalyserMaker, AnalyserSupporter {

    protected String analyserType;

    public AbstractAnalyserRegistry() {
    }

    public AbstractAnalyserRegistry(String analyserType) {
        this.analyserType = analyserType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(analyserType, type);
    }

    @Override
    public String provideType() {
        return analyserType;
    }

    public String getAnalyserType() {
        return analyserType;
    }

    public void setAnalyserType(String analyserType) {
        this.analyserType = analyserType;
    }

    @Override
    public String toString() {
        return "AbstractAnalyserRegistry{" +
                "analyserType='" + analyserType + '\'' +
                '}';
    }
}
