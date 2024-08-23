package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.exception.UnsupportedJudgerTypeException;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.JudgerHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class JudgerHandlerImpl implements JudgerHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JudgerHandlerImpl.class);

    private final List<JudgerMaker> judgerMakers;

    public JudgerHandlerImpl(List<JudgerMaker> judgerMakers) {
        this.judgerMakers = Optional.ofNullable(judgerMakers).orElse(Collections.emptyList());
    }

    @Override
    public Judger make(JudgerInfo judgerInfo) throws HandlerException {
        try {
            // 生成触发器。
            LOGGER.debug("通过判断器信息构建新的的判断器...");
            JudgerMaker judgerMaker = judgerMakers.stream().filter(maker -> maker.supportType(judgerInfo.getType()))
                    .findFirst().orElseThrow(() -> new UnsupportedJudgerTypeException(judgerInfo.getType()));
            Judger judger = judgerMaker.makeJudger(judgerInfo);
            LOGGER.debug("判断器构建成功!");
            LOGGER.debug("判断器: {}", judger);
            return judger;
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
