package com.dwarfeng.judge.impl.handler.judger.linear;

import com.dwarfeng.judge.sdk.handler.judger.AbstractJudger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 线性映射判断器。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("linearMappingJudgerRegistry.linearMappingJudger")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LinearMappingJudger extends AbstractJudger {

    private final ApplicationContext ctx;

    private final LinearMappingJudgerConfig config;

    public LinearMappingJudger(ApplicationContext ctx, LinearMappingJudgerConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(LinearMappingExecutor.class, config);
    }

    @Override
    public String toString() {
        return "LinearMappingJudger{" +
                "ctx=" + ctx +
                ", config=" + config +
                '}';
    }
}
