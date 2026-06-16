package com.dwarfeng.judge.impl.handler.judger.binarization;

import com.dwarfeng.judge.sdk.handler.judger.AbstractJudger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 二值化判断器。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("binarizationJudgerRegistry.binarizationJudger")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BinarizationJudger extends AbstractJudger {

    private final ApplicationContext ctx;

    private final BinarizationJudgerConfig config;

    public BinarizationJudger(ApplicationContext ctx, BinarizationJudgerConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(BinarizationExecutor.class, config);
    }

    @Override
    public String toString() {
        return "BinarizationJudger{" +
                "ctx=" + ctx +
                ", config=" + config +
                '}';
    }
}
