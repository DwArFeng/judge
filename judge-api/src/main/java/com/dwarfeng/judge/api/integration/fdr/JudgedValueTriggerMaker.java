package com.dwarfeng.judge.api.integration.fdr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.fdr.impl.handler.TriggerMaker;
import com.dwarfeng.fdr.stack.bean.entity.TriggerInfo;
import com.dwarfeng.fdr.stack.bean.entity.TriggeredValue;
import com.dwarfeng.fdr.stack.exception.TriggerException;
import com.dwarfeng.fdr.stack.exception.TriggerMakeException;
import com.dwarfeng.fdr.stack.handler.Trigger;
import com.dwarfeng.judge.sdk.bean.dto.FastJsonJudgementInfo;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 判断结果过滤器制造器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class JudgedValueTriggerMaker implements TriggerMaker {

    public static final String SUPPORT_TYPE = "judged_value_trigger";

    @Autowired
    private ApplicationContext ctx;

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public Trigger makeTrigger(TriggerInfo triggerInfo) throws TriggerException {
        try {
            Config config = JSON.parseObject(triggerInfo.getContent(), Config.class);
            JudgedValueTrigger trigger = ctx.getBean(JudgedValueTrigger.class);
            trigger.setPointKey(triggerInfo.getPointKey());
            trigger.setTriggerInfoKey(triggerInfo.getKey());
            trigger.setConfig(config);
            return trigger;
        } catch (Exception e) {
            throw new TriggerMakeException(e);
        }
    }

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "判断结果触发器";
    }

    @Override
    public String provideDescription() {
        return "judge框架提供的触发器，用于判断DataInfo对象转化为JudgedValue之后其" +
                "JudgementInfo.value是否满足定义的约束，若满足定义的约束则进行触发。";
    }

    @Override
    public String provideExampleContent() {
        Config config = new Config(0.5, "GE");
        return JSON.toJSONString(config, true) +
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
    public static class JudgedValueTrigger implements Trigger, Bean {

        private static final long serialVersionUID = -735143848090656543L;
        private static final Logger LOGGER = LoggerFactory.getLogger(JudgedValueTrigger.class);

        private LongIdKey pointKey;
        private LongIdKey triggerInfoKey;
        private Config config;

        public JudgedValueTrigger() {
        }

        @Override
        public TriggeredValue test(DataInfo dataInfo) throws TriggerException {
            try {
                String message = null;
                String constraint = config.getConstraint().toUpperCase();
                FastJsonJudgementInfo info = JSON.parseObject(dataInfo.getValue(), FastJsonJudgementInfo.class);
                switch (constraint) {
                    case Config.EQ:
                        if (info.getValue() == config.getValue()) {
                            message = "judgementInfo.value 等于 config.value";
                        }
                        break;
                    case Config.NE:
                        if (info.getValue() != config.getValue()) {
                            message = "judgementInfo.value 不等于 config.value";
                        }
                        break;
                    case Config.LE:
                        if (info.getValue() <= config.getValue()) {
                            message = "judgementInfo.value 小于等于 config.value";
                        }
                        break;
                    case Config.LT:
                        if (info.getValue() < config.getValue()) {
                            message = "judgementInfo.value 小于 config.value";
                        }
                        break;
                    case Config.GE:
                        if (info.getValue() >= config.getValue()) {
                            message = "judgementInfo.value 大于等于 config.value";
                        }
                        break;
                    case Config.GT:
                        if (info.getValue() > config.getValue()) {
                            message = "judgementInfo.value 大于 config.value";
                        }
                        break;
                    default:
                        LOGGER.debug("不能识别约束 " + constraint + ", 将抛出异常...");
                        throw new IllegalArgumentException("不能识别的约束: " + constraint);
                }

                if (Objects.isNull(message)) {
                    return null;
                } else {
                    return new TriggeredValue(
                            null,
                            pointKey,
                            triggerInfoKey,
                            dataInfo.getHappenedDate(),
                            dataInfo.getValue(),
                            message
                    );
                }
            } catch (Exception e) {
                throw new TriggerException(e);
            }
        }

        public LongIdKey getPointKey() {
            return pointKey;
        }

        public void setPointKey(LongIdKey pointKey) {
            this.pointKey = pointKey;
        }

        public LongIdKey getTriggerInfoKey() {
            return triggerInfoKey;
        }

        public void setTriggerInfoKey(LongIdKey triggerInfoKey) {
            this.triggerInfoKey = triggerInfoKey;
        }

        public Config getConfig() {
            return config;
        }

        public void setConfig(Config config) {
            this.config = config;
        }

        @Override
        public String toString() {
            return "JudgedValueTrigger{" +
                    "pointKey=" + pointKey +
                    ", triggerInfoKey=" + triggerInfoKey +
                    ", config=" + config +
                    '}';
        }
    }


    public static class Config implements Bean {

        public static final String EQ = "EQ";
        public static final String NE = "NE";
        public static final String LE = "LE";
        public static final String LT = "LT";
        public static final String GE = "GE";
        public static final String GT = "GT";

        private static final long serialVersionUID = -4007748497549433676L;

        @JSONField(name = "value")
        private Double value;

        @JSONField(name = "constraint")
        private String constraint;

        public Config() {
        }

        public Config(Double value, String constraint) {
            this.value = value;
            this.constraint = constraint;
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
                    "value=" + value +
                    ", constraint='" + constraint + '\'' +
                    '}';
        }
    }
}
