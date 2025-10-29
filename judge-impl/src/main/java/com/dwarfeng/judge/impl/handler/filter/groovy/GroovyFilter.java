package com.dwarfeng.judge.impl.handler.filter.groovy;

import com.dwarfeng.judge.sdk.handler.filter.AbstractFilter;
import com.dwarfeng.judge.stack.bean.dto.LookupResult;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 模拟过滤器。
 *
 * @author wangyc
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
    protected LookupResult doFilt(LookupResult lookupResult) throws Exception {
        return processor.filt(lookupResult);
    }

    @Override
    public String toString() {
        return "GroovyFilter{" +
                "processor=" + processor +
                '}';
    }
}
