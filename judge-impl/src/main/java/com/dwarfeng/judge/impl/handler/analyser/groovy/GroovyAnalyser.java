package com.dwarfeng.judge.impl.handler.analyser.groovy;

import com.dwarfeng.judge.sdk.handler.analyser.AbstractAnalyser;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Groovy 分析器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Component("groovyAnalyserRegistry.groovyAnalyser")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyAnalyser extends AbstractAnalyser {

    private final ApplicationContext ctx;

    private final Processor processor;

    public GroovyAnalyser(ApplicationContext ctx, Processor processor) {
        this.ctx = ctx;
        this.processor = processor;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(GroovyExecutor.class, processor);
    }

    @Override
    public String toString() {
        return "GroovyAnalyser{" +
                "ctx=" + ctx +
                ", processor=" + processor +
                '}';
    }
}
