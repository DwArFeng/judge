package com.dwarfeng.judge.impl.handler.driver;

import com.dwarfeng.judge.impl.handler.DriverProvider;
import com.dwarfeng.judge.impl.handler.DriverSupporter;

import java.util.Objects;

/**
 * 抽象判断器注册。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public abstract class AbstractDriverRegistry implements DriverProvider, DriverSupporter {

    protected String driverType;

    public AbstractDriverRegistry() {
    }

    public AbstractDriverRegistry(String driverType) {
        this.driverType = driverType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(driverType, type);
    }

    @Override
    public String provideType() {
        return driverType;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    @Override
    public String toString() {
        return "AbstractDriverRegistry{" +
                "driverType='" + driverType + '\'' +
                '}';
    }
}
