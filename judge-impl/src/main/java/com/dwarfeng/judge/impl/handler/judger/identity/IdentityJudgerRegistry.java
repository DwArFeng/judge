package com.dwarfeng.judge.impl.handler.judger.identity;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.handler.judger.AbstractJudgerRegistry;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.exception.JudgerMakeException;
import com.dwarfeng.judge.stack.handler.Judger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 本征判断器注册。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
public class IdentityJudgerRegistry extends AbstractJudgerRegistry {

    public static final String JUDGER_TYPE = "identity_judger";

    private final ApplicationContext ctx;

    public IdentityJudgerRegistry(ApplicationContext ctx) {
        super(JUDGER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "本征判断器";
    }

    @Override
    public String provideDescription() {
        return "将常量或分析结果直接标准化为判断结果。";
    }

    @Override
    public String provideExampleParam() {
        IdentityJudgerConfig config = new IdentityJudgerConfig(
                "example.judgement", "analysis", "0.6", "example.analysis", "0.0", "1.0"
        );
        return JSON.toJSONString(config, true);
    }

    @Override
    public Judger makeJudger(String type, String param) throws JudgerException {
        try {
            IdentityJudgerConfig config = JSON.parseObject(param, IdentityJudgerConfig.class);
            if (config == null) {
                throw new IllegalArgumentException("本征判断器配置不能为空");
            }
            return ctx.getBean(IdentityJudger.class, ctx, config);
        } catch (Exception e) {
            throw new JudgerMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "IdentityJudgerRegistry{" +
                "ctx=" + ctx +
                ", judgerType='" + judgerType + '\'' +
                '}';
    }
}
