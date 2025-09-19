package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.DataLookupInfo;
import com.dwarfeng.judge.stack.bean.dto.DataLookupResult;
import com.dwarfeng.judge.stack.handler.ProvideHandler;
import com.dwarfeng.judge.stack.handler.ProviderSession;
import com.dwarfeng.judge.stack.handler.ProviderSessionHoldHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

@Component
public class ProvideHandlerImpl implements ProvideHandler {

    private final ProviderSessionHoldHandler providerSessionHoldHandler;

    public ProvideHandlerImpl(ProviderSessionHoldHandler providerSessionHoldHandler) {
        this.providerSessionHoldHandler = providerSessionHoldHandler;
    }

    @Override
    @BehaviorAnalyse
    public DataLookupResult lookupData(DataLookupInfo info) throws HandlerException {
        try {
            return provide0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private DataLookupResult provide0(DataLookupInfo info) throws Exception {
        // 展开参数。
        LongIdKey providerInfoKey = info.getProviderInfoKey();
        String preset = info.getPreset();
        Object[] objs = info.getObjs();

        // 获取会话。
        ProviderSession providerSession = providerSessionHoldHandler.get(providerInfoKey);

        // 调用会话的方法，构造结果并返回。
        return new DataLookupResult(providerSession.lookupData(preset, objs));
    }
}
