package com.dwarfeng.judge.impl.handler.sinker.mock;

import com.dwarfeng.judge.sdk.handler.sinker.AbstractSinker;
import com.dwarfeng.judge.stack.handler.SinkerSession;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 模拟下沉器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MockSinker extends AbstractSinker {

    private final ApplicationContext ctx;

    public MockSinker(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    protected SinkerSession doNewSession() {
        return ctx.getBean(MockSinkerSession.class);
    }

    @Override
    public String toString() {
        return "MockSinker{" +
                "ctx=" + ctx +
                '}';
    }
}
