package com.dwarfeng.judge.impl.handler.sinker.kafka.nati;

import com.dwarfeng.judge.sdk.handler.sinker.AbstractSinker;
import com.dwarfeng.judge.stack.handler.SinkerSession;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 本地 kafka 下沉器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NativeKafkaSinker extends AbstractSinker {

    private final ApplicationContext ctx;

    private final NativeKafkaSinkerConfig config;

    public NativeKafkaSinker(ApplicationContext ctx, NativeKafkaSinkerConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected SinkerSession doNewSession() {
        return ctx.getBean(NativeKafkaSinkerSession.class, ctx, config);
    }
}
