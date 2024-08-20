package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.SectionReport;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * JSFixed FastJson 部件报告。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonSectionReport implements Dto {

    private static final long serialVersionUID = 2666078560525545111L;

    public static JSFixedFastJsonSectionReport of(SectionReport sectionReport) {
        if (Objects.isNull(sectionReport)) {
            return null;
        }
        return new JSFixedFastJsonSectionReport(
                JSFixedFastJsonLongIdKey.of(sectionReport.getSectionKey()),
                sectionReport.getHappenedDate(),
                sectionReport.getNormalization(),
                sectionReport.getSum(),
                sectionReport.getExpected(),
                sectionReport.getVariance(),
                Optional.ofNullable(sectionReport.getJudgerReports()).map(
                        f -> f.stream().map(JSFixedFastJsonJudgerReport::of).collect(Collectors.toList())
                ).orElse(null)
        );
    }

    @JSONField(name = "section_key", ordinal = 1)
    private JSFixedFastJsonLongIdKey sectionKey;

    @JSONField(name = "happened_date", ordinal = 2)
    private Date happenedDate;

    @JSONField(name = "normalization", ordinal = 3)
    private double normalization;

    @JSONField(name = "sum", ordinal = 4)
    private double sum;

    @JSONField(name = "expected", ordinal = 5)
    private double expected;

    @JSONField(name = "variance", ordinal = 6)
    private double variance;

    @JSONField(name = "judger_reports", ordinal = 7)
    private List<JSFixedFastJsonJudgerReport> judgerReports;

    public JSFixedFastJsonSectionReport() {
    }

    public JSFixedFastJsonSectionReport(
            JSFixedFastJsonLongIdKey sectionKey, Date happenedDate, double normalization, double sum, double expected,
            double variance, List<JSFixedFastJsonJudgerReport> judgerReports) {
        this.sectionKey = sectionKey;
        this.happenedDate = happenedDate;
        this.normalization = normalization;
        this.sum = sum;
        this.expected = expected;
        this.variance = variance;
        this.judgerReports = judgerReports;
    }

    public JSFixedFastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(JSFixedFastJsonLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    public double getNormalization() {
        return normalization;
    }

    public void setNormalization(double normalization) {
        this.normalization = normalization;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getExpected() {
        return expected;
    }

    public void setExpected(double expected) {
        this.expected = expected;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public List<JSFixedFastJsonJudgerReport> getJudgerReports() {
        return judgerReports;
    }

    public void setJudgerReports(List<JSFixedFastJsonJudgerReport> judgerReports) {
        this.judgerReports = judgerReports;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonSectionReport{" +
                "sectionKey=" + sectionKey +
                ", happenedDate=" + happenedDate +
                ", normalization=" + normalization +
                ", sum=" + sum +
                ", expected=" + expected +
                ", variance=" + variance +
                ", judgerReports=" + judgerReports +
                '}';
    }
}
