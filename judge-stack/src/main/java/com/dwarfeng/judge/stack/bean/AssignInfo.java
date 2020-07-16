package com.dwarfeng.judge.stack.bean;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Map;

/**
 * 指派信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class AssignInfo implements Bean {

    private static final long serialVersionUID = -3943708619460394624L;

    private Section section;
    private Map<DriverInfo, Driver> driverMap;

    public AssignInfo() {
    }

    public AssignInfo(Section section, Map<DriverInfo, Driver> driverMap) {
        this.section = section;
        this.driverMap = driverMap;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Map<DriverInfo, Driver> getDriverMap() {
        return driverMap;
    }

    public void setDriverMap(Map<DriverInfo, Driver> driverMap) {
        this.driverMap = driverMap;
    }

    @Override
    public String toString() {
        return "AssignInfo{" +
                "section=" + section +
                ", driverMap=" + driverMap +
                '}';
    }
}
