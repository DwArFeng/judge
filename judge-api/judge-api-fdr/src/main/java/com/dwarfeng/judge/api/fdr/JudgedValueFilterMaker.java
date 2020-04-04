package com.dwarfeng.judge.api.fdr;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.fdr.impl.handler.FilterMaker;
import com.dwarfeng.fdr.impl.handler.filter.DoubleFilterMaker;
import com.dwarfeng.fdr.stack.bean.entity.FilterInfo;
import com.dwarfeng.fdr.stack.bean.entity.FilteredValue;
import com.dwarfeng.fdr.stack.exception.FilterException;
import com.dwarfeng.fdr.stack.exception.FilterMakeException;
import com.dwarfeng.fdr.stack.handler.Filter;
import com.dwarfeng.judge.sdk.bean.dto.FastJsonJudgementInfo;
import com.dwarfeng.judge.sdk.bean.dto.FastJsonJudgementInfo.FastJsonPersistenceInfo;
import com.dwarfeng.judge.sdk.bean.dto.FastJsonJudgementInfo.FastJsonRealtimeInfo;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * 判断结果过滤器制造器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class JudgedValueFilterMaker implements FilterMaker {

    public static final String SUPPORT_TYPE = "judged_value_filter";

    @Autowired
    private ApplicationContext ctx;

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public Filter makeFilter(FilterInfo filterInfo) throws FilterException {
        try {
            JudgedValueFilter filter = ctx.getBean(JudgedValueFilter.class);
            filter.setPointKey(filterInfo.getPointKey());
            filter.setFilterInfoKey(filterInfo.getKey());
            return filter;
        } catch (Exception e) {
            throw new FilterMakeException(e);
        }
    }

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "判断结果过滤器";
    }

    @Override
    public String provideDescription() {
        return "judge框架提供的过滤器，用于判断DataInfo对象是否能转换成JudgedValue，以及转化后是否合法。";
    }

    @Override
    public String provideExampleContent() {
        return "";
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class JudgedValueFilter implements Filter, Bean {

        private static final long serialVersionUID = -7726824172080776924L;
        private static final Logger LOGGER = LoggerFactory.getLogger(DoubleFilterMaker.DoubleFilter.class);

        private LongIdKey pointKey;
        private LongIdKey filterInfoKey;

        public JudgedValueFilter() {
        }

        @Override
        public FilteredValue test(DataInfo dataInfo) throws FilterException {
            try {
                // 1. 补全发生日期。
                if (Objects.isNull(dataInfo.getHappenedDate())) {
                    dataInfo.setHappenedDate(new Date());
                }
                // 1. 判断DataInfo中数据是否完整。
                if (Objects.isNull(dataInfo.getValue())) {
                    LOGGER.debug("DataInfo值为 null, 不能通过过滤...");
                    return new FilteredValue(
                            null,
                            pointKey,
                            filterInfoKey,
                            dataInfo.getHappenedDate(),
                            "",
                            "值为null"
                    );
                }
                // 2. 取出值，转换成 FastJsonJudgementInfo，过程中出现异常不能通过过滤。
                FastJsonJudgementInfo judgementInfo;
                try {
                    judgementInfo = JSON.parseObject(dataInfo.getValue(), FastJsonJudgementInfo.class);
                } catch (Exception e) {
                    LOGGER.debug("解析value时出现异常，不能通过过滤", e);
                    return new FilteredValue(
                            null,
                            pointKey,
                            filterInfoKey,
                            dataInfo.getHappenedDate(),
                            dataInfo.getValue(),
                            "解析JSON时出现异常"
                    );
                }
                // 3. 判断 judgementInfo 的 value 是否在合理的区间内。
                if (judgementInfo.getValue() < 0.0 || judgementInfo.getValue() > 1.0) {
                    LOGGER.debug("judgementInfo.value 为" + judgementInfo.getValue() + ", 不在区间[0.0,1.0]之内");
                    return new FilteredValue(
                            null,
                            pointKey,
                            filterInfoKey,
                            dataInfo.getHappenedDate(),
                            dataInfo.getValue(),
                            "judgementInfo.value 不在区间[0.0, 1.0]之内"
                    );
                }
                // 4. 判断persistenceInfos, realtimeInfos是否为null。
                if (Objects.isNull(judgementInfo.getPersistenceInfos())) {
                    LOGGER.debug("judgementInfo.persistenceInfos 为 null");
                    return new FilteredValue(
                            null,
                            pointKey,
                            filterInfoKey,
                            dataInfo.getHappenedDate(),
                            dataInfo.getValue(),
                            "judgementInfo.persistenceInfos 为 null"
                    );
                }
                if (Objects.isNull(judgementInfo.getRealtimeInfos())) {
                    LOGGER.debug("judgementInfo.realtimeInfos 为 null");
                    return new FilteredValue(
                            null,
                            pointKey,
                            filterInfoKey,
                            dataInfo.getHappenedDate(),
                            dataInfo.getValue(),
                            "judgementInfo.realtimeInfos 为 null"
                    );
                }
                // 5. 循环判断 judgement.persistenceInfos 中的元素是否合法。
                for (FastJsonPersistenceInfo persistenceInfo : judgementInfo.getPersistenceInfos()) {
                    String nullField = null;
                    if (Objects.isNull(persistenceInfo.getPersistenceKey())) {
                        nullField = "persistenceKey";
                    }
                    if (Objects.isNull(persistenceInfo.getStartDate())) {
                        nullField = "startDate";
                    }
                    if (Objects.isNull(persistenceInfo.getEndDate())) {
                        nullField = "endDate";
                    }
                    if (Objects.nonNull(nullField)) {
                        LOGGER.debug("至少一个 persistenceInfo 的 " + nullField + " 字段是 null");
                        return new FilteredValue(
                                null,
                                pointKey,
                                filterInfoKey,
                                dataInfo.getHappenedDate(),
                                dataInfo.getValue(),
                                "至少一个 persistenceInfo 的 " + nullField + " 字段是 null"
                        );
                    }

                    if (persistenceInfo.getStartDate().compareTo(persistenceInfo.getEndDate()) > 0) {
                        LOGGER.debug("至少一个 persistenceInfo 的 startDate 大于 endDate");
                        return new FilteredValue(
                                null,
                                pointKey,
                                filterInfoKey,
                                dataInfo.getHappenedDate(),
                                dataInfo.getValue(),
                                "至少一个 persistenceInfo 的 startDate 大于 endDate"
                        );
                    }
                }

                // 6. 循环判断 judgement.realtimeInfos 中的元素是否合法。
                for (FastJsonRealtimeInfo realtimeInfo : judgementInfo.getRealtimeInfos()) {
                    String nullField = null;
                    if (Objects.isNull(realtimeInfo.getRealtimeKey())) {
                        nullField = "realtimeKey";
                    }
                    if (Objects.isNull(realtimeInfo.getValue())) {
                        nullField = "value";
                    }
                    if (Objects.nonNull(nullField)) {
                        LOGGER.debug("至少一个 realtimeInfo 的 " + nullField + " 字段是 null");
                        return new FilteredValue(
                                null,
                                pointKey,
                                filterInfoKey,
                                dataInfo.getHappenedDate(),
                                dataInfo.getValue(),
                                "至少一个 realtimeInfo 的 " + nullField + " 字段是 null"
                        );
                    }
                }

                // 7. 所有测试通过，DataInfo合法。
                return null;
            } catch (Exception e) {
                throw new FilterException(e);
            }
        }

        public LongIdKey getPointKey() {
            return pointKey;
        }

        public void setPointKey(LongIdKey pointKey) {
            this.pointKey = pointKey;
        }

        public LongIdKey getFilterInfoKey() {
            return filterInfoKey;
        }

        public void setFilterInfoKey(LongIdKey filterInfoKey) {
            this.filterInfoKey = filterInfoKey;
        }

        @Override
        public String toString() {
            return "JudgedValueFilter{" +
                    "pointKey=" + pointKey +
                    ", filterInfoKey=" + filterInfoKey +
                    '}';
        }
    }
}
