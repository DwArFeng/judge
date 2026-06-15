package com.dwarfeng.judge.impl.handler.judger.linear;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.handler.judger.AbstractJudgerRegistry;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.exception.JudgerMakeException;
import com.dwarfeng.judge.stack.handler.Judger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 线性映射判断器注册。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
public class LinearMappingJudgerRegistry extends AbstractJudgerRegistry {

    public static final String JUDGER_TYPE = "linear_mapping_judger";

    private final ApplicationContext ctx;

    public LinearMappingJudgerRegistry(ApplicationContext ctx) {
        super(JUDGER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "线性映射判断器";
    }

    @Override
    public String provideDescription() {
        return "将输入值 x、系数 k、偏移 b 按 k * x + b 线性映射并标准化为判断结果。";
    }

    @Override
    public String provideExampleParam() {
        LinearMappingJudgerConfig config = new LinearMappingJudgerConfig(
                "example.judgement",
                "analysis", "10", "example.input", "0.0", "1.0",
                "constant", "0.1", null, null, null,
                "constant", "0.0", null, null, null
        );
        return JSON.toJSONString(config, true);
    }

    @Override
    public Judger makeJudger(String type, String param) throws JudgerException {
        try {
            LinearMappingJudgerConfig config = JSON.parseObject(param, LinearMappingJudgerConfig.class);
            if (config == null) {
                throw new IllegalArgumentException("线性映射判断器配置不能为空");
            }
            return ctx.getBean(LinearMappingJudger.class, ctx, config);
        } catch (Exception e) {
            throw new JudgerMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "LinearMappingJudgerRegistry{" +
                "ctx=" + ctx +
                ", judgerType='" + judgerType + '\'' +
                '}';
    }
}
