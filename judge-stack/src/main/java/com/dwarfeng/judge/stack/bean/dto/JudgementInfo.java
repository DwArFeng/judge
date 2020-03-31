package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;
import java.util.List;

/**
 * 判断信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JudgementInfo implements Dto {

    private static final long serialVersionUID = -335251319705300757L;

    private double value;
    private List<RealtimeInfo> realtimeInfos;
    private List<PersistenceInfo> persistenceInfos;

    public JudgementInfo() {
    }

    public JudgementInfo(double value, List<RealtimeInfo> realtimeInfos, List<PersistenceInfo> persistenceInfos) {
        this.value = value;
        this.realtimeInfos = realtimeInfos;
        this.persistenceInfos = persistenceInfos;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<RealtimeInfo> getRealtimeInfos() {
        return realtimeInfos;
    }

    public void setRealtimeInfos(List<RealtimeInfo> realtimeInfos) {
        this.realtimeInfos = realtimeInfos;
    }

    public List<PersistenceInfo> getPersistenceInfos() {
        return persistenceInfos;
    }

    public void setPersistenceInfos(List<PersistenceInfo> persistenceInfos) {
        this.persistenceInfos = persistenceInfos;
    }

    @Override
    public String toString() {
        return "JudgementInfo{" +
                "value=" + value +
                ", realtimeInfos=" + realtimeInfos +
                ", persistenceInfos=" + persistenceInfos +
                '}';
    }

    public static class RealtimeInfo implements Dto {

        private static final long serialVersionUID = 4276966379168332277L;

        private LongIdKey realtimeKey;
        private String value;

        public RealtimeInfo() {
        }

        public RealtimeInfo(LongIdKey realtimeKey, String value) {
            this.realtimeKey = realtimeKey;
            this.value = value;
        }

        public LongIdKey getRealtimeKey() {
            return realtimeKey;
        }

        public void setRealtimeKey(LongIdKey realtimeKey) {
            this.realtimeKey = realtimeKey;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "RealtimeInfo{" +
                    "realtimeKey=" + realtimeKey +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class PersistenceInfo implements Dto {

        private static final long serialVersionUID = -3674265396584565087L;

        private LongIdKey persistenceKey;
        private Date startDate;
        private Date endDate;

        public PersistenceInfo() {
        }

        public PersistenceInfo(LongIdKey persistenceKey, Date startDate, Date endDate) {
            this.persistenceKey = persistenceKey;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public LongIdKey getPersistenceKey() {
            return persistenceKey;
        }

        public void setPersistenceKey(LongIdKey persistenceKey) {
            this.persistenceKey = persistenceKey;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return "PersistenceInfo{" +
                    "persistenceKey=" + persistenceKey +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    '}';
        }
    }
}
