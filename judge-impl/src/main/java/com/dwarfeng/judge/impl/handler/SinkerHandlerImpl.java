package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.handler.SinkerMaker;
import com.dwarfeng.judge.stack.exception.SinkerException;
import com.dwarfeng.judge.stack.exception.UnsupportedSinkerTypeException;
import com.dwarfeng.judge.stack.handler.Sinker;
import com.dwarfeng.judge.stack.handler.SinkerHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class SinkerHandlerImpl implements SinkerHandler {

    private final List<SinkerMaker> sinkerMakers;

    public SinkerHandlerImpl(List<SinkerMaker> sinkerMakers) {
        this.sinkerMakers = Optional.ofNullable(sinkerMakers).orElse(Collections.emptyList());
    }

    @Override
    @BehaviorAnalyse
    public Sinker make(String type, String param) throws HandlerException {
        try {
            // 生成下沉器。
            SinkerMaker sinkerMaker = sinkerMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedSinkerTypeException(type));
            return sinkerMaker.makeSinker(type, param);
        } catch (SinkerException e) {
            throw e;
        } catch (Exception e) {
            throw new SinkerException(e);
        }
    }
}
