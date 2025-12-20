package com.dwarfeng.judge.sdk.handler.filter;

import com.dwarfeng.judge.sdk.handler.FilterMaker;
import com.dwarfeng.judge.sdk.handler.FilterSupporter;

import java.util.Objects;

/**
 * 抽象过滤器注册。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.3.0
 */
public abstract class AbstractFilterRegistry implements FilterMaker, FilterSupporter {

    protected String FilterType;

    public AbstractFilterRegistry() {
    }

    public AbstractFilterRegistry(String FilterType) {
        this.FilterType = FilterType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(FilterType, type);
    }

    /**
     * {@inheritDoc}
     *
     * @author DwArFeng
     * @since 2.4.0
     */
    @Override
    public String provideType() {
        return FilterType;
    }

    public String getFilterType() {
        return FilterType;
    }

    public void setFilterType(String FilterType) {
        this.FilterType = FilterType;
    }

    @Override
    public String toString() {
        return "AbstractFilterRegistry{" +
                "FilterType='" + FilterType + '\'' +
                '}';
    }
}
