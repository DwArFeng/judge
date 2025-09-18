package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.handler.ProviderMaker;
import com.dwarfeng.judge.stack.exception.ProviderException;
import com.dwarfeng.judge.stack.exception.UnsupportedProviderTypeException;
import com.dwarfeng.judge.stack.handler.Provider;
import com.dwarfeng.judge.stack.handler.ProviderHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProviderHandlerImpl implements ProviderHandler {

    private final List<ProviderMaker> providerMakers;

    public ProviderHandlerImpl(List<ProviderMaker> providerMakers) {
        this.providerMakers = Optional.ofNullable(providerMakers).orElse(Collections.emptyList());
    }

    @Override
    @BehaviorAnalyse
    public Provider make(String type, String param) throws HandlerException {
        try {
            // 生成提供器。
            ProviderMaker providerMaker = providerMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedProviderTypeException(type));
            return providerMaker.makeProvider(type, param);
        } catch (ProviderException e) {
            throw e;
        } catch (Exception e) {
            throw new ProviderException(e);
        }
    }
}
