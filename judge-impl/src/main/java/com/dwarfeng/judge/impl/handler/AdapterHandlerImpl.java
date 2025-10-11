package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.handler.AdapterMaker;
import com.dwarfeng.judge.stack.exception.AdapterException;
import com.dwarfeng.judge.stack.exception.UnsupportedAdapterTypeException;
import com.dwarfeng.judge.stack.handler.Adapter;
import com.dwarfeng.judge.stack.handler.AdapterHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AdapterHandlerImpl implements AdapterHandler {

    private final List<AdapterMaker> adapterMakers;

    public AdapterHandlerImpl(List<AdapterMaker> adapterMakers) {
        this.adapterMakers = Optional.ofNullable(adapterMakers).orElse(Collections.emptyList());
    }

    @Override
    @BehaviorAnalyse
    public Adapter make(String type, String param) throws HandlerException {
        try {
            // 生成适配器。
            AdapterMaker adapterMaker = adapterMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedAdapterTypeException(type));
            return adapterMaker.makeAdapter(type, param);
        } catch (AdapterException e) {
            throw e;
        } catch (Exception e) {
            throw new AdapterException(e);
        }
    }
}
