package com.dwarfeng.judge.impl.handler.judger.groovy;

import com.dwarfeng.judge.sdk.handler.judger.AbstractExecutor;
import com.dwarfeng.judge.stack.handler.Judger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Groovy 判断器执行器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Component("groovyJudgerRegistry.groovyExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyExecutor extends AbstractExecutor {

    private final Processor processor;

    public GroovyExecutor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Judger.JudgeResult judge() throws Exception {
        return processor.judge(context);
    }

    @Override
    public String toString() {
        return "GroovyExecutor{" +
                "processor=" + processor +
                ", context=" + context +
                '}';
    }
}
