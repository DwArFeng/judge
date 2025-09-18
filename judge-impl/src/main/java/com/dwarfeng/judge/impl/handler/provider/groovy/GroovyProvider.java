package com.dwarfeng.judge.impl.handler.provider.groovy;

import com.dwarfeng.judge.sdk.handler.provider.AbstractProvider;
import com.dwarfeng.judge.stack.handler.ProviderSession;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 模拟提供器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyProvider extends AbstractProvider {

    private final ApplicationContext ctx;

    private final Processor processor;

    public GroovyProvider(ApplicationContext ctx, Processor processor) {
        this.ctx = ctx;
        this.processor = processor;
    }

    @Override
    protected ProviderSession doNewSession() {
        return ctx.getBean(GroovyProviderSession.class, processor);
    }

    @Override
    public String toString() {
        return "GroovyProvider{" +
                "ctx=" + ctx +
                ", processor=" + processor +
                '}';
    }
}
