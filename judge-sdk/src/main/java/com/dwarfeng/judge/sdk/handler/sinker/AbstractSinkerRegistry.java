package com.dwarfeng.judge.sdk.handler.sinker;

import com.dwarfeng.judge.sdk.handler.SinkerMaker;
import com.dwarfeng.judge.sdk.handler.SinkerSupporter;

import java.util.Objects;

/**
 * 抽象下沉器注册。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public abstract class AbstractSinkerRegistry implements SinkerMaker, SinkerSupporter {

    protected String sinkerType;

    public AbstractSinkerRegistry() {
    }

    public AbstractSinkerRegistry(String sinkerType) {
        this.sinkerType = sinkerType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(sinkerType, type);
    }

    @Override
    public String provideType() {
        return sinkerType;
    }

    public String getSinkerType() {
        return sinkerType;
    }

    public void setSinkerType(String sinkerType) {
        this.sinkerType = sinkerType;
    }

    @Override
    public String toString() {
        return "AbstractSinkerRegistry{" +
                "sinkerType='" + sinkerType + '\'' +
                '}';
    }
}
