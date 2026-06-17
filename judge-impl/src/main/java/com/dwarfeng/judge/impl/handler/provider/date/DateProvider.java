package com.dwarfeng.judge.impl.handler.provider.date;

import com.dwarfeng.judge.sdk.handler.provider.AbstractProvider;
import com.dwarfeng.judge.stack.handler.ProviderSession;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 日期提供器。
 *
 * <p>
 * 无状态提供器，为每次会话创建独立的 {@link DateProviderSession}。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DateProvider extends AbstractProvider {

    private final ApplicationContext ctx;

    public DateProvider(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    protected ProviderSession doNewSession() {
        return ctx.getBean(DateProviderSession.class);
    }

    @Override
    public String toString() {
        return "DateProvider{" +
                "ctx=" + ctx +
                '}';
    }
}
