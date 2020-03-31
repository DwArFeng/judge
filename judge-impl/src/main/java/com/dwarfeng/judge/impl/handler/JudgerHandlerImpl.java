package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.exception.UnsupportedJudgerTypeException;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.JudgerHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JudgerHandlerImpl implements JudgerHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JudgerHandlerImpl.class);

    @Autowired
    private List<JudgerMaker> judgerMakers;

    @Override
    public Judger make(JudgerInfo judgerInfo) throws HandlerException {
        try {
            // 生成触发器。
            LOGGER.debug("通过判断器信息构建新的的判断器...");
            JudgerMaker judgerMaker = judgerMakers.stream().filter(maker -> maker.supportType(judgerInfo.getType()))
                    .findFirst().orElseThrow(() -> new UnsupportedJudgerTypeException(judgerInfo.getType()));
            Judger judger = judgerMaker.makeJudger(judgerInfo);
            LOGGER.debug("判断器构建成功!");
            LOGGER.debug("判断器: " + judger);
            return judger;
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
