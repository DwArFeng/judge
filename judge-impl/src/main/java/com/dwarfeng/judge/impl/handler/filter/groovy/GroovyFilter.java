package com.dwarfeng.judge.impl.handler.filter.groovy;

import com.dwarfeng.judge.sdk.handler.filter.AbstractFilter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 模拟过滤器。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.3.0
 */
@Component("groovyFilterRegistry.groovyFilter")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyFilter extends AbstractFilter {

    private final Processor processor;

    public GroovyFilter(Processor processor) {
        this.processor = processor;
    }

    @Override
    protected FilterResult doFilter(FilterInfo info) throws Exception {
        return processor.filter(info);
    }

    @Override
    public String toString() {
        return "GroovyFilter{" +
                "processor=" + processor +
                '}';
    }
}
