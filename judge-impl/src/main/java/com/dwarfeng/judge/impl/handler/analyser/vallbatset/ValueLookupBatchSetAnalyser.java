package com.dwarfeng.judge.impl.handler.analyser.vallbatset;

import com.dwarfeng.judge.sdk.handler.analyser.AbstractAnalyser;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 值查询批量设置分析器。
 *
 * <p>
 * 该分析器执行一次 lookup 查询，并将查询结果按 <code>outputs</code> 配置批量写入多个 <code>Analysis</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("valueLookupBatchSetAnalyserRegistry.valueLookupBatchSetAnalyser")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ValueLookupBatchSetAnalyser extends AbstractAnalyser {

    private final ApplicationContext ctx;

    private final ValueLookupBatchSetAnalyserConfig config;

    public ValueLookupBatchSetAnalyser(ApplicationContext ctx, ValueLookupBatchSetAnalyserConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(ValueLookupBatchSetExecutor.class, config);
    }

    @Override
    public String toString() {
        return "ValueLookupBatchSetAnalyser{" +
                "ctx=" + ctx +
                ", config=" + config +
                '}';
    }
}
