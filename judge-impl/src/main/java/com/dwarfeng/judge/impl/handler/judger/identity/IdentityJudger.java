package com.dwarfeng.judge.impl.handler.judger.identity;

import com.dwarfeng.judge.sdk.handler.judger.AbstractJudger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 本征判断器。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("identityJudgerRegistry.identityJudger")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IdentityJudger extends AbstractJudger {

    private final ApplicationContext ctx;

    private final IdentityJudgerConfig config;

    public IdentityJudger(ApplicationContext ctx, IdentityJudgerConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(IdentityExecutor.class, config);
    }

    @Override
    public String toString() {
        return "IdentityJudger{" +
                "ctx=" + ctx +
                ", config=" + config +
                '}';
    }
}
