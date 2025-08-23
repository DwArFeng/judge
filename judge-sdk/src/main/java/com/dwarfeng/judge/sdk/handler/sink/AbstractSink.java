package com.dwarfeng.judge.sdk.handler.sink;

import com.dwarfeng.judge.sdk.handler.Sink;

import java.util.Objects;

/**
 * 抽象 Sink。
 *
 * <p>
 * Sink 的抽象实现。
 *
 * @author DwArFeng
 * @since 1.7.0
 */
public abstract class AbstractSink implements Sink {

    protected String sinkType;

    public AbstractSink() {
    }

    public AbstractSink(String sinkType) {
        this.sinkType = sinkType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(sinkType, type);
    }

    public String getSinkType() {
        return sinkType;
    }

    public void setSinkType(String sinkType) {
        this.sinkType = sinkType;
    }

    @Override
    public String toString() {
        return "AbstractSink{" +
                "sinkType='" + sinkType + '\'' +
                '}';
    }
}
