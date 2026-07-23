package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.judge.stack.bean.entity.Verdict;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;

/**
 * 部件裁决结果按部件查看结果。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class VerdictInspectBySectionResult implements Dto {

    private static final long serialVersionUID = 7328813037015202509L;

    /**
     * 裁决结果列表。
     */
    private List<Verdict> verdicts;

    public VerdictInspectBySectionResult() {
    }

    public VerdictInspectBySectionResult(List<Verdict> verdicts) {
        this.verdicts = verdicts;
    }

    public List<Verdict> getVerdicts() {
        return verdicts;
    }

    public void setVerdicts(List<Verdict> verdicts) {
        this.verdicts = verdicts;
    }

    @Override
    public String toString() {
        return "VerdictInspectBySectionResult{" +
                "verdicts=" + verdicts +
                '}';
    }
}
