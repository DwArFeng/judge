package com.dwarfeng.judge.impl.handler.judger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.dcti.stack.bean.dto.TimedValue;
import com.dwarfeng.judge.impl.handler.JudgerMaker;
import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
import com.dwarfeng.judge.stack.bean.dto.JudgementInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgementInfo.RealtimeInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.exception.JudgerMakeException;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.RepositoryHandler;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.Objects;

/**
 * 截断判断器生成器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class TruncationJudgerMaker implements JudgerMaker {

    public static final String SUPPORT_TYPE = "truncation_judger";

    @Autowired
    private ApplicationContext ctx;

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public Judger makeJudger(JudgerInfo judgerInfo) throws JudgerException {
        try {
            TruncationJudger judger = ctx.getBean(TruncationJudger.class);
            judger.setJudgerInfoKey(judgerInfo.getKey());
            judger.setConfig(JSON.parseObject(judgerInfo.getContent(), Config.class));
            return judger;
        } catch (Exception e) {
            throw new JudgerMakeException(e);
        }
    }

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "截断判断器";
    }

    @Override
    public String provideDescription() {
        return "给定一个最大值和最小值，并取出指定数据点的实时值。" +
                "若实时值低于最小值，则返回0.0; " +
                "若实时值高于最大值，则返回1.0;" +
                "若实时值介于最小值和最大值之间，则返回线性的中间值。" +
                "如果反转属性为true，则反过来，低于最小值是1.0，高于最大值是0.0";
    }

    @Override
    public String provideExampleContent() {
        Config config = new Config(692151005587959809L, -50.0, 100.0, false);
        return JSON.toJSONString(config, true);
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class TruncationJudger implements Judger, Bean {

        private static final long serialVersionUID = -3161256701795506170L;
        private static final Logger LOGGER = LoggerFactory.getLogger(TruncationJudger.class);

        private LongIdKey judgerInfoKey;
        private Config config;

        public TruncationJudger() {
        }

        @Override
        public JudgedValue judge(RepositoryHandler repositoryHandler) throws JudgerException {
            try {
                Date happenedDate = new Date();
                LongIdKey realtimePointKey = new LongIdKey(config.getPointKey());

                double judgementValue;
                double realtimeValue;
                TimedValue timedValue;
                
                try {
                    timedValue = repositoryHandler.realtimeValue(realtimePointKey);
                } catch (Exception e) {
                    LOGGER.warn("获取数据点 " + realtimePointKey + " 的实时值时发生错误，将抛出异常");
                    throw e;
                }
                try {
                    realtimeValue = Double.parseDouble(timedValue.getValue());
                } catch (Exception e) {
                    LOGGER.warn("将字符串 " + timedValue.getValue() + " 解析为 double 时发生错误，将抛出异常");
                    throw e;
                }

                if (realtimeValue > config.getMax()) {
                    judgementValue = 1.0;
                } else if (realtimeValue < config.getMin()) {
                    judgementValue = 0.0;
                } else {
                    double offset = realtimeValue - config.getMin();
                    double length = config.getMax() - config.getMin();
                    judgementValue = offset / length;
                }

                if (config.getInverse()) {
                    judgementValue = judgementValue * -1 + 1;
                }

                judgementValue = Math.max(judgementValue, 0.0);
                judgementValue = Math.min(judgementValue, 1.0);

                RealtimeInfo realtimeInfo = new RealtimeInfo(realtimePointKey, timedValue.getValue());
                JudgementInfo judgementInfo = new JudgementInfo(
                        judgementValue,
                        Collections.singletonList(realtimeInfo),
                        Collections.emptyList());
                return new JudgedValue(judgerInfoKey, judgementInfo, happenedDate);
            } catch (Exception e) {
                throw new JudgerException(e);
            }
        }

        public LongIdKey getJudgerInfoKey() {
            return judgerInfoKey;
        }

        public void setJudgerInfoKey(LongIdKey judgerInfoKey) {
            this.judgerInfoKey = judgerInfoKey;
        }

        public Config getConfig() {
            return config;
        }

        public void setConfig(Config config) {
            this.config = config;
        }

        @Override
        public String toString() {
            return "TruncationJudger{" +
                    "judgerInfoKey=" + judgerInfoKey +
                    ", config=" + config +
                    '}';
        }
    }

    public static class Config implements Bean {

        private static final long serialVersionUID = -887631237829999860L;

        @JSONField(name = "point_key")
        private Long pointKey;

        @JSONField(name = "min")
        private Double min;

        @JSONField(name = "max")
        private Double max;

        @JSONField(name = "inverse")
        private Boolean inverse;

        public Config() {
        }

        public Config(Long pointKey, Double min, Double max, Boolean inverse) {
            this.pointKey = pointKey;
            this.min = min;
            this.max = max;
            this.inverse = inverse;
        }

        public Long getPointKey() {
            return pointKey;
        }

        public void setPointKey(Long pointKey) {
            this.pointKey = pointKey;
        }

        public Double getMin() {
            return min;
        }

        public void setMin(Double min) {
            this.min = min;
        }

        public Double getMax() {
            return max;
        }

        public void setMax(Double max) {
            this.max = max;
        }

        public Boolean getInverse() {
            return inverse;
        }

        public void setInverse(Boolean inverse) {
            this.inverse = inverse;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "pointKey=" + pointKey +
                    ", min=" + min +
                    ", max=" + max +
                    ", inverse=" + inverse +
                    '}';
        }
    }
}
