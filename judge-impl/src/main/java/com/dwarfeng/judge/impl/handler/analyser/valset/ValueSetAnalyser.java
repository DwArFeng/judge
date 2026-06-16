package com.dwarfeng.judge.impl.handler.analyser.valset;

import com.dwarfeng.judge.sdk.handler.analyser.AbstractAnalyser;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 值设置分析器。
 *
 * <p>
 * 该分析器按配置将常量、已有分析结果或 lookup 查询结果写入指定 <code>Analysis</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("valueSetAnalyserRegistry.valueSetAnalyser")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ValueSetAnalyser extends AbstractAnalyser {

    private final ApplicationContext ctx;

    private final ValueSetAnalyserConfig config;

    public ValueSetAnalyser(ApplicationContext ctx, ValueSetAnalyserConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(ValueSetExecutor.class, config);
    }

    @Override
    public String toString() {
        return "ValueSetAnalyser{" +
                "ctx=" + ctx +
                ", config=" + config +
                '}';
    }
}
