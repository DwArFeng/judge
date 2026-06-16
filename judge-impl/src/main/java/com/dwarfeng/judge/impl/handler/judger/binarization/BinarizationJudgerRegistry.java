package com.dwarfeng.judge.impl.handler.judger.binarization;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.handler.judger.AbstractJudgerRegistry;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.exception.JudgerMakeException;
import com.dwarfeng.judge.stack.handler.Judger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 二值化判断器注册。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
public class BinarizationJudgerRegistry extends AbstractJudgerRegistry {

    public static final String JUDGER_TYPE = "binarization_judger";

    private final ApplicationContext ctx;

    public BinarizationJudgerRegistry(ApplicationContext ctx) {
        super(JUDGER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "二值化判断器";
    }

    @Override
    public String provideDescription() {
        return "将输入值与基准值比较后输出二值判断结果。";
    }

    @Override
    public String provideExampleParam() {
        BinarizationJudgerConfig config = new BinarizationJudgerConfig(
                "example.judgement",
                "analysis", "5", "example.input", "0.0", "1.0",
                "constant", "3", null, null, null,
                "ge"
        );
        return JSON.toJSONString(config, true);
    }

    @Override
    public Judger makeJudger(String type, String param) throws JudgerException {
        try {
            BinarizationJudgerConfig config = JSON.parseObject(param, BinarizationJudgerConfig.class);
            if (config == null) {
                throw new IllegalArgumentException("二值化判断器配置不能为空");
            }
            return ctx.getBean(BinarizationJudger.class, ctx, config);
        } catch (Exception e) {
            throw new JudgerMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "BinarizationJudgerRegistry{" +
                "ctx=" + ctx +
                ", judgerType='" + judgerType + '\'' +
                '}';
    }
}
