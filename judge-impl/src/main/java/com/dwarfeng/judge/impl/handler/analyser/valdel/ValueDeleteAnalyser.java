package com.dwarfeng.judge.impl.handler.analyser.valdel;

import com.dwarfeng.judge.sdk.handler.analyser.AbstractAnalyser;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 值删除分析器。
 *
 * <p>
 * 该分析器按配置删除当前任务下指定 <code>Analysis</code>，不支持跨任务删除或动态来源解析。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("valueDeleteAnalyserRegistry.valueDeleteAnalyser")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ValueDeleteAnalyser extends AbstractAnalyser {

    private final ApplicationContext ctx;

    private final ValueDeleteAnalyserConfig config;

    public ValueDeleteAnalyser(ApplicationContext ctx, ValueDeleteAnalyserConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(ValueDeleteExecutor.class, config);
    }

    @Override
    public String toString() {
        return "ValueDeleteAnalyser{" +
                "ctx=" + ctx +
                ", config=" + config +
                '}';
    }
}
