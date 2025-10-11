package com.dwarfeng.judge.impl.handler.adapter.groovy;

import com.dwarfeng.judge.sdk.handler.adapter.AbstractAdapter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 模拟适配器。
 *
 * @author wangyc
 * @since 2.3.0
 */
@Component("groovyAdapterRegistry.groovyAdapter")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyAdapter extends AbstractAdapter {

    private final com.dwarfeng.judge.impl.handler.adapter.groovy.Processor processor;

    public GroovyAdapter(Processor processor) {
        this.processor = processor;
    }

    @Override
    protected Object[] doAdapt(Object[] args) throws Exception {
        return processor.adapt(args);
    }

    @Override
    public String toString() {
        return "GroovyAdapter{" +
                "processor=" + processor +
                '}';
    }
}
