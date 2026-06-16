package com.dwarfeng.judge.impl.handler.judger.threshold;

import com.dwarfeng.judge.sdk.handler.judger.AbstractJudger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 阈值判断器。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("thresholdJudgerRegistry.thresholdJudger")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ThresholdJudger extends AbstractJudger {

    private final ApplicationContext ctx;

    private final ThresholdJudgerConfig config;

    public ThresholdJudger(ApplicationContext ctx, ThresholdJudgerConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(ThresholdExecutor.class, config);
    }

    @Override
    public String toString() {
        return "ThresholdJudger{" +
                "ctx=" + ctx +
                ", config=" + config +
                '}';
    }
}
