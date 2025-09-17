package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.SinkInfo;
import com.dwarfeng.judge.stack.handler.SectionBindingLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.SinkHandler;
import com.dwarfeng.judge.stack.handler.SinkerSession;
import com.dwarfeng.judge.stack.handler.SinkerSessionHoldHandler;
import com.dwarfeng.judge.stack.struct.SectionBinding;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SinkHandlerImpl implements SinkHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SinkHandlerImpl.class);

    private final SectionBindingLocalCacheHandler sectionBindingLocalCacheHandler;
    private final SinkerSessionHoldHandler sinkerSessionHoldHandler;

    public SinkHandlerImpl(
            SectionBindingLocalCacheHandler sectionBindingLocalCacheHandler,
            SinkerSessionHoldHandler sinkerSessionHoldHandler
    ) {
        this.sectionBindingLocalCacheHandler = sectionBindingLocalCacheHandler;
        this.sinkerSessionHoldHandler = sinkerSessionHoldHandler;
    }

    @Override
    @BehaviorAnalyse
    public void sink(SinkInfo info) throws HandlerException {
        try {
            sink0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void sink0(SinkInfo info) throws Exception {
        // 展开参数。
        LongIdKey sectionKey = info.getSectionKey();

        // 获取部件绑定。
        SectionBinding sectionBinding = sectionBindingLocalCacheHandler.get(sectionKey);

        // 对相关的每个下沉器，获取会话，调用相关的下沉方法。
        for (LongIdKey sinkerInfoKey : sectionBinding.getMap().keySet()) {
            sinkSingle(info, sinkerInfoKey);
        }
    }

    private void sinkSingle(SinkInfo sinkInfo, LongIdKey sinkerInfoKey) {
        try {
            // 获取会话。
            SinkerSession sinkerSession = sinkerSessionHoldHandler.get(sinkerInfoKey);
            // 调用会话的下沉方法。
            sinkerSession.sink(sinkInfo);
        } catch (Exception e) {
            String message = "下沉数据时发生异常, 抛弃 1 次数据下沉, sinkInfo: " + sinkInfo +
                    ", sinkerInfoKey: " + sinkerInfoKey + ", 异常信息如下: ";
            LOGGER.warn(message, e);
        }
    }
}
