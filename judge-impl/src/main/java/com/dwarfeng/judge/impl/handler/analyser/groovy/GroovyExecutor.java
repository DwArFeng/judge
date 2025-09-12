package com.dwarfeng.judge.impl.handler.analyser.groovy;

import com.dwarfeng.judge.sdk.handler.analyser.AbstractExecutor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Groovy 分析器执行器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Component("groovyAnalyserRegistry.groovyExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyExecutor extends AbstractExecutor {

    private final Processor processor;

    public GroovyExecutor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public void analyse() throws Exception {
        processor.analyse(context);
    }

    @Override
    public String toString() {
        return "GroovyExecutor{" +
                "processor=" + processor +
                ", context=" + context +
                '}';
    }
}
