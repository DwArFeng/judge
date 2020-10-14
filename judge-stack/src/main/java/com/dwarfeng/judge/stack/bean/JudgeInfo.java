package com.dwarfeng.judge.stack.bean;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Map;

/**
 * 指派信息。
 *
 * @author DwArFeng
 * @since 1.4.1
 */
public class JudgeInfo implements Bean {

    private static final long serialVersionUID = 6060637083462258232L;

    private Section section;
    private Map<DriverInfo, Driver> driverMap;
    private Map<JudgerInfo, Judger> judgerMap;

    public JudgeInfo() {
    }

    public JudgeInfo(Section section, Map<DriverInfo, Driver> driverMap, Map<JudgerInfo, Judger> judgerMap) {
        this.section = section;
        this.driverMap = driverMap;
        this.judgerMap = judgerMap;
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

    public Map<JudgerInfo, Judger> getJudgerMap() {
        return judgerMap;
    }

    public void setJudgerMap(Map<JudgerInfo, Judger> judgerMap) {
        this.judgerMap = judgerMap;
    }

    @Override
    public String toString() {
        return "JudgeInfo{" +
                "section=" + section +
                ", driverMap=" + driverMap +
                ", judgerMap=" + judgerMap +
                '}';
    }
}
