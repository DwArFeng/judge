package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.JudgementInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgementInfo.PersistenceInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgementInfo.RealtimeInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FastJsonJudgementInfo implements Bean {

    private static final long serialVersionUID = 7916204675752256460L;

    public static FastJsonJudgementInfo of(JudgementInfo judgementInfo) {
        if (Objects.isNull(judgementInfo)) {
            return null;
        }

        return new FastJsonJudgementInfo(
                judgementInfo.getValue(),
                judgementInfo.getRealtimeInfos().stream()
                        .map(FastJsonRealtimeInfo::of).collect(Collectors.toList()),
                judgementInfo.getPersistenceInfos().stream()
                        .map(FastJsonPersistenceInfo::of).collect(Collectors.toList())
        );
    }

    public static JudgementInfo toStackBean(FastJsonJudgementInfo fastJsonJudgementInfo) {
        if (Objects.isNull(fastJsonJudgementInfo)) {
            return null;
        }

        return new JudgementInfo(
                fastJsonJudgementInfo.getValue(),
                fastJsonJudgementInfo.getRealtimeInfos().stream()
                        .map(FastJsonRealtimeInfo::toStackBean).collect(Collectors.toList()),
                fastJsonJudgementInfo.getPersistenceInfos().stream()
                        .map(FastJsonPersistenceInfo::toStackBean).collect(Collectors.toList())
        );
    }

    @JSONField(name = "value", ordinal = 1)
    private double value;
    @JSONField(name = "realtime_infos", ordinal = 2)
    private List<FastJsonRealtimeInfo> realtimeInfos;
    @JSONField(name = "persistence_infos", ordinal = 3)
    private List<FastJsonPersistenceInfo> persistenceInfos;

    public FastJsonJudgementInfo() {
    }

    public FastJsonJudgementInfo(
            double value, List<FastJsonRealtimeInfo> realtimeInfos, List<FastJsonPersistenceInfo> persistenceInfos) {
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

    public List<FastJsonRealtimeInfo> getRealtimeInfos() {
        return realtimeInfos;
    }

    public void setRealtimeInfos(List<FastJsonRealtimeInfo> realtimeInfos) {
        this.realtimeInfos = realtimeInfos;
    }

    public List<FastJsonPersistenceInfo> getPersistenceInfos() {
        return persistenceInfos;
    }

    public void setPersistenceInfos(List<FastJsonPersistenceInfo> persistenceInfos) {
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

    public static class FastJsonRealtimeInfo implements Bean {

        private static final long serialVersionUID = 8013316590434592116L;

        public static FastJsonRealtimeInfo of(RealtimeInfo realtimeInfo) {
            if (Objects.isNull(realtimeInfo)) {
                return null;
            }

            return new FastJsonRealtimeInfo(
                    FastJsonLongIdKey.of(realtimeInfo.getRealtimeKey()),
                    realtimeInfo.getValue()
            );
        }

        public static RealtimeInfo toStackBean(FastJsonRealtimeInfo fastJsonRealtimeInfo) {
            if (Objects.isNull(fastJsonRealtimeInfo)) {
                return null;
            }

            return new RealtimeInfo(
                    new LongIdKey(fastJsonRealtimeInfo.getRealtimeKey().getLongId()),
                    fastJsonRealtimeInfo.getValue()
            );
        }

        @JSONField(name = "realtime_key", ordinal = 1)
        private FastJsonLongIdKey realtimeKey;
        @JSONField(name = "value", ordinal = 2)
        private String value;

        public FastJsonRealtimeInfo() {
        }

        public FastJsonRealtimeInfo(FastJsonLongIdKey realtimeKey, String value) {
            this.realtimeKey = realtimeKey;
            this.value = value;
        }

        public FastJsonLongIdKey getRealtimeKey() {
            return realtimeKey;
        }

        public void setRealtimeKey(FastJsonLongIdKey realtimeKey) {
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

    public static class FastJsonPersistenceInfo implements Bean {

        private static final long serialVersionUID = 6889680028883220625L;

        public static FastJsonPersistenceInfo of(PersistenceInfo persistenceInfo) {
            if (Objects.isNull(persistenceInfo)) {
                return null;
            }

            return new FastJsonPersistenceInfo(
                    FastJsonLongIdKey.of(persistenceInfo.getPersistenceKey()),
                    persistenceInfo.getStartDate(),
                    persistenceInfo.getEndDate()
            );
        }

        public static PersistenceInfo toStackBean(FastJsonPersistenceInfo fastJsonPersistenceInfo) {
            if (Objects.isNull(fastJsonPersistenceInfo)) {
                return null;
            }

            return new PersistenceInfo(
                    new LongIdKey(fastJsonPersistenceInfo.getPersistenceKey().getLongId()),
                    fastJsonPersistenceInfo.getStartDate(),
                    fastJsonPersistenceInfo.getEndDate()
            );
        }

        @JSONField(name = "persistence_key", ordinal = 1)
        private FastJsonLongIdKey persistenceKey;
        @JSONField(name = "start_date", ordinal = 2)
        private Date startDate;
        @JSONField(name = "end_date", ordinal = 3)
        private Date endDate;

        public FastJsonPersistenceInfo() {
        }

        public FastJsonPersistenceInfo(FastJsonLongIdKey persistenceKey, Date startDate, Date endDate) {
            this.persistenceKey = persistenceKey;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public FastJsonLongIdKey getPersistenceKey() {
            return persistenceKey;
        }

        public void setPersistenceKey(FastJsonLongIdKey persistenceKey) {
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
