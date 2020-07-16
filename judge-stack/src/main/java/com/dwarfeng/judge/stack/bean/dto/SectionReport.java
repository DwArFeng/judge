package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;
import java.util.List;

/**
 * 部件报告。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class SectionReport implements Dto {

    private static final long serialVersionUID = 6649417543973848382L;

    private LongIdKey sectionKey;
    private Date happenedDate;
    private double normalization;
    private double sum;
    private double expected;
    private double variance;
    private List<JudgerReport> judgerReports;

    public SectionReport() {
    }

    public SectionReport(
            LongIdKey sectionKey, Date happenedDate, double normalization, double sum, double expected,
            double variance, List<JudgerReport> judgerReports) {
        this.sectionKey = sectionKey;
        this.happenedDate = happenedDate;
        this.normalization = normalization;
        this.sum = sum;
        this.expected = expected;
        this.variance = variance;
        this.judgerReports = judgerReports;
    }

    public LongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(LongIdKey sectionKey) {
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

    public List<JudgerReport> getJudgerReports() {
        return judgerReports;
    }

    public void setJudgerReports(List<JudgerReport> judgerReports) {
        this.judgerReports = judgerReports;
    }

    @Override
    public String toString() {
        return "SectionReport{" +
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
