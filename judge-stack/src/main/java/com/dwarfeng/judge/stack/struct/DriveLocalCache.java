package com.dwarfeng.judge.stack.struct;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.Driver;

import java.util.Map;

/**
 * 驱动本地缓存。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public final class DriveLocalCache {

    private final Section section;
    private final Map<DriverInfo, Driver> driverMap;

    public DriveLocalCache(Section section, Map<DriverInfo, Driver> driverMap) {
        this.section = section;
        this.driverMap = driverMap;
    }

    public Section getSection() {
        return section;
    }

    public Map<DriverInfo, Driver> getDriverMap() {
        return driverMap;
    }

    @Override
    public String toString() {
        return "DriveLocalCache{" +
                "section=" + section +
                ", driverMap=" + driverMap +
                '}';
    }
}
