package com.dwarfeng.judge.impl.handler.analyser.valllstset;

import com.dwarfeng.judge.sdk.handler.analyser.AbstractAnalyser;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 值查询列表设置分析器。
 *
 * <p>
 * 该分析器执行一次 lookup 查询，从 <code>LookupResult.datas</code> 的所有行中提取字段列表，
 * 按 <code>outputs</code> 配置序列化后写入字符串或文件类型 <code>Analysis</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("valueLookupListSetAnalyserRegistry.valueLookupListSetAnalyser")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ValueLookupListSetAnalyser extends AbstractAnalyser {

    private final ApplicationContext ctx;

    private final ValueLookupListSetAnalyserConfig config;

    public ValueLookupListSetAnalyser(ApplicationContext ctx, ValueLookupListSetAnalyserConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected Executor doNewExecutor() {
        return ctx.getBean(ValueLookupListSetExecutor.class, config);
    }

    @Override
    public String toString() {
        return "ValueLookupListSetAnalyser{" +
                "ctx=" + ctx +
                ", config=" + config +
                '}';
    }
}
