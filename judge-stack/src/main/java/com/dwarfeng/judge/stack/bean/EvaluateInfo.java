package com.dwarfeng.judge.stack.bean;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Map;

/**
 * 评估信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class EvaluateInfo implements Bean {

    private static final long serialVersionUID = 8552958241911198678L;

    private Section section;
    private Map<JudgerInfo, Judger> judgerMap;

    public EvaluateInfo() {
    }

    public EvaluateInfo(Section section, Map<JudgerInfo, Judger> judgerMap) {
        this.section = section;
        this.judgerMap = judgerMap;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Map<JudgerInfo, Judger> getJudgerMap() {
        return judgerMap;
    }

    public void setJudgerMap(Map<JudgerInfo, Judger> judgerMap) {
        this.judgerMap = judgerMap;
    }

    @Override
    public String toString() {
        return "EvaluateInfo{" +
                "section=" + section +
                ", judgerMap=" + judgerMap +
                '}';
    }
}
