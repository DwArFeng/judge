package com.dwarfeng.judge.impl.handler.judger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.judge.impl.handler.JudgerMaker;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.exception.JudgerMakeException;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.RepositoryHandler;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
        return "处理指定点位的实时数据，如果数据小于0，则取0；如果数据大于1，则取1；如果数据处于0.0-1.0之间，则取其本身。";
    }

    @Override
    public String provideExampleContent() {
        return JSON.toJSONString(new Config(
                692151005587959809L
        ), true);
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class TruncationJudger implements Judger, Bean {

        private static final long serialVersionUID = -3161256701795506170L;

        private LongIdKey judgerInfoKey;
        private Config config;

        public TruncationJudger() {
        }

        @Override
        public DataInfo judge(RepositoryHandler repositoryHandler) throws JudgerException {
            try {
                String value = repositoryHandler.realtimeValue(new LongIdKey(config.getPointKey()));
                double doubleValue = Double.parseDouble(value);
                if (doubleValue < 0.0) {
                    doubleValue = 0.0;
                } else {
                    doubleValue = Math.min(doubleValue, 1.0);
                }
                return new DataInfo(
                        judgerInfoKey.getLongId(),
                        Double.toString(doubleValue),
                        new Date()
                );
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
        private long pointKey;

        public Config() {
        }

        public Config(long pointKey) {
            this.pointKey = pointKey;
        }

        public long getPointKey() {
            return pointKey;
        }

        public void setPointKey(long pointKey) {
            this.pointKey = pointKey;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "pointKey=" + pointKey +
                    '}';
        }
    }
}
