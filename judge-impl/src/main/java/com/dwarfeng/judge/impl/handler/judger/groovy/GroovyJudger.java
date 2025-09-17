package com.dwarfeng.judge.impl.handler.judger.groovy;

import com.dwarfeng.judge.sdk.handler.judger.AbstractJudger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Groovy 判断器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Component("groovyJudgerRegistry.groovyJudger")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyJudger extends AbstractJudger {

    private final ApplicationContext ctx;

    private final Processor processor;

    public GroovyJudger(ApplicationContext ctx, Processor processor) {
        this.ctx = ctx;
        this.processor = processor;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(GroovyExecutor.class, processor);
    }

    @Override
    public String toString() {
        return "GroovyJudger{" +
                "ctx=" + ctx +
                ", processor=" + processor +
                '}';
    }
}
