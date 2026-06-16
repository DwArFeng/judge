package com.dwarfeng.judge.impl.handler.judger.threshold;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.handler.judger.AbstractJudgerRegistry;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.exception.JudgerMakeException;
import com.dwarfeng.judge.stack.handler.Judger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 阈值判断器注册。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
public class ThresholdJudgerRegistry extends AbstractJudgerRegistry {

    public static final String JUDGER_TYPE = "threshold_judger";

    private final ApplicationContext ctx;

    public ThresholdJudgerRegistry(ApplicationContext ctx) {
        super(JUDGER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "阈值判断器";
    }

    @Override
    public String provideDescription() {
        return "在低阈值与高阈值之间按线性插值输出标准化判断结果。";
    }

    @Override
    public String provideExampleParam() {
        ThresholdJudgerConfig config = new ThresholdJudgerConfig(
                "example.judgement",
                "analysis", "25", "example.input", "0.0", "1.0",
                "constant", "0", null, null, null,
                "constant", "100", null, null, null
        );
        return JSON.toJSONString(config, true);
    }

    @Override
    public Judger makeJudger(String type, String param) throws JudgerException {
        try {
            ThresholdJudgerConfig config = JSON.parseObject(param, ThresholdJudgerConfig.class);
            if (config == null) {
                throw new IllegalArgumentException("阈值判断器配置不能为空");
            }
            return ctx.getBean(ThresholdJudger.class, ctx, config);
        } catch (Exception e) {
            throw new JudgerMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "ThresholdJudgerRegistry{" +
                "ctx=" + ctx +
                ", judgerType='" + judgerType + '\'' +
                '}';
    }
}
