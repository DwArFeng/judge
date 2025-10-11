package com.dwarfeng.judge.sdk.handler.adapter;

import com.dwarfeng.judge.sdk.handler.AdapterMaker;
import com.dwarfeng.judge.sdk.handler.AdapterSupporter;

import java.util.Objects;

/**
 * 抽象适配器注册。
 *
 * @author wangyc
 * @since 2.3.0
 */
public abstract class AbstractAdapterRegistry implements AdapterMaker, AdapterSupporter {

    protected String AdapterType;

    public AbstractAdapterRegistry() {
    }

    public AbstractAdapterRegistry(String AdapterType) {
        this.AdapterType = AdapterType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(AdapterType, type);
    }

    @Override
    public String adaptType() {
        return AdapterType;
    }

    public String getAdapterType() {
        return AdapterType;
    }

    public void setAdapterType(String AdapterType) {
        this.AdapterType = AdapterType;
    }

    @Override
    public String toString() {
        return "AbstractAdapterRegistry{" +
                "AdapterType='" + AdapterType + '\'' +
                '}';
    }
}
