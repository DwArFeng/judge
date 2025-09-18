package com.dwarfeng.judge.sdk.handler.provider;

import com.dwarfeng.judge.sdk.handler.ProviderMaker;
import com.dwarfeng.judge.sdk.handler.ProviderSupporter;

import java.util.Objects;

/**
 * 抽象提供器注册。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public abstract class AbstractProviderRegistry implements ProviderMaker, ProviderSupporter {

    protected String providerType;

    public AbstractProviderRegistry() {
    }

    public AbstractProviderRegistry(String providerType) {
        this.providerType = providerType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(providerType, type);
    }

    @Override
    public String provideType() {
        return providerType;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    @Override
    public String toString() {
        return "AbstractProviderRegistry{" +
                "providerType='" + providerType + '\'' +
                '}';
    }
}
