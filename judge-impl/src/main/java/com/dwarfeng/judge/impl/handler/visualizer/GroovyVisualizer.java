package com.dwarfeng.judge.impl.handler.visualizer;

import com.dwarfeng.judge.sdk.handler.visualizer.AbstractVisualizer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Groovy 可视化器。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
@Component("groovyVisualizerRegistry.groovyVisualizer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyVisualizer extends AbstractVisualizer {

    private final ApplicationContext ctx;

    private final Processor processor;

    public GroovyVisualizer(ApplicationContext ctx, Processor processor) {
        this.ctx = ctx;
        this.processor = processor;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(GroovyExecutor.class, processor);
    }

    @Override
    public String toString() {
        return "GroovyVisualizer{" +
                "ctx=" + ctx +
                ", processor=" + processor +
                '}';
    }
}
