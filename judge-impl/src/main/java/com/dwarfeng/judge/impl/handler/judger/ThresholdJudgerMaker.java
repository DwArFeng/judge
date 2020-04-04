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
 * 阈值判断器制造器。
 *
 * @author DwArFeng
 * @since beta-1.0.1
 */
@Component
public class ThresholdJudgerMaker implements JudgerMaker {

    public static final String SUPPORT_TYPE = "threshold_judger";

    @Autowired
    private ApplicationContext ctx;

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public Judger makeJudger(JudgerInfo judgerInfo) throws JudgerException {
        try {
            Config config = JSON.parseObject(judgerInfo.getContent(), Config.class);
            ThresholdJudger judger = ctx.getBean(ThresholdJudger.class);
            judger.setJudgerInfoKey(judgerInfo.getKey());
            judger.setConfig(config);
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
        return "阈值判断器";
    }

    @Override
    public String provideDescription() {
        return "获取数据点的实时值，并且与预设的阈值进行比较。如果实时值与阈值满足约束关系，则返回1.0，否则返回0.0.";
    }

    @Override
    public String provideExampleContent() {
        Config config = new Config();
        return JSON.toJSONString(config) +
                '\n' +
                "# 以下的内容为注释，在实际配置时删除这些注释。\n" +
                "# 可用的constraint内容 (可忽略大小写): \n" +
                "#   LE: judgementInfo.value的值 等于 配置的 value\n" +
                "#   LE: judgementInfo.value的值 不等于 配置的 value\n" +
                "#   LE: judgementInfo.value的值 小于等于 配置的 value\n" +
                "#   LT: judgementInfo.value的值 小于 配置的 value\n" +
                "#   GE: judgementInfo.value的值 大于等于 配置的 value\n" +
                "#   GT: judgementInfo.value的值 大于 配置的 value";
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class ThresholdJudger implements Judger, Bean {

        private static final long serialVersionUID = 3103022253500261506L;
        private static final Logger LOGGER = LoggerFactory.getLogger(ThresholdJudger.class);

        private LongIdKey judgerInfoKey;
        private Config config;

        public ThresholdJudger() {
        }

        @Override
        public JudgedValue judge(RepositoryHandler repositoryHandler) throws JudgerException {
            try {
                Date happenedDate = new Date();
                LongIdKey realtimePointKey = new LongIdKey(config.realtimePointKey);

                double judgementValue = 0.0;
                @SuppressWarnings("UnusedAssignment")
                double realtimeValue = 0.0;

                String constraint = config.getConstraint().toUpperCase();
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

                switch (constraint) {
                    case Config.EQ:
                        if (realtimeValue == config.getValue()) {
                            judgementValue = 1.0;
                        }
                        break;
                    case Config.NE:
                        if (realtimeValue != config.getValue()) {
                            judgementValue = 1.0;
                        }
                        break;
                    case Config.LE:
                        if (realtimeValue <= config.getValue()) {
                            judgementValue = 1.0;
                        }
                        break;
                    case Config.LT:
                        if (realtimeValue < config.getValue()) {
                            judgementValue = 1.0;
                        }
                        break;
                    case Config.GE:
                        if (realtimeValue >= config.getValue()) {
                            judgementValue = 1.0;
                        }
                        break;
                    case Config.GT:
                        if (realtimeValue > config.getValue()) {
                            judgementValue = 1.0;
                        }
                        break;
                    default:
                        LOGGER.debug("不能识别约束 " + constraint + ", 将抛出异常...");
                        throw new IllegalArgumentException("不能识别的约束: " + constraint);
                }

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
            return "ThresholdJudger{" +
                    "judgerInfoKey=" + judgerInfoKey +
                    ", config=" + config +
                    '}';
        }
    }

    public static final class Config implements Bean {

        public static final String EQ = "EQ";
        public static final String NE = "NE";
        public static final String LE = "LE";
        public static final String LT = "LT";
        public static final String GE = "GE";
        public static final String GT = "GT";

        private static final long serialVersionUID = -8951793052735614431L;

        @JSONField(name = "realtime_point_key")
        private Long realtimePointKey;

        @JSONField(name = "value")
        private Double value;

        @JSONField(name = "constraint")
        private String constraint;

        public Config() {
        }

        public Config(Long realtimePointKey, Double value, String constraint) {
            this.realtimePointKey = realtimePointKey;
            this.value = value;
            this.constraint = constraint;
        }

        public Long getRealtimePointKey() {
            return realtimePointKey;
        }

        public void setRealtimePointKey(Long realtimePointKey) {
            this.realtimePointKey = realtimePointKey;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public String getConstraint() {
            return constraint;
        }

        public void setConstraint(String constraint) {
            this.constraint = constraint;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "realtimePointKey=" + realtimePointKey +
                    ", value=" + value +
                    ", constraint='" + constraint + '\'' +
                    '}';
        }
    }
}
