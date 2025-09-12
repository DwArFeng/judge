package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgementModalUpdateInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgementModal;
import com.dwarfeng.judge.stack.handler.JudgementModalOperateHandler;
import com.dwarfeng.judge.stack.handler.PushHandler;
import com.dwarfeng.judge.stack.service.JudgementModalMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JudgementModalOperateHandlerImpl implements JudgementModalOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JudgementModalOperateHandlerImpl.class);

    private final JudgementModalMaintainService judgementModalMaintainService;

    private final PushHandler pushHandler;

    private final HandlerValidator handlerValidator;

    public JudgementModalOperateHandlerImpl(
            JudgementModalMaintainService judgementModalMaintainService,
            PushHandler pushHandler,
            HandlerValidator handlerValidator
    ) {
        this.judgementModalMaintainService = judgementModalMaintainService;
        this.pushHandler = pushHandler;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public void update(JudgementModalUpdateInfo info) throws HandlerException {
        try {
            update0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void update0(JudgementModalUpdateInfo info) throws Exception {
        // 展开参数。
        LongIdKey sectionKey = info.getSectionKey();
        LongIdKey judgerInfoKey = info.getJudgerInfoKey();
        Date happenedDate = info.getHappenedDate();
        double value = info.getValue();
        String message = info.getMessage();

        // 确认判断器信息存在。
        handlerValidator.makeSureJudgerInfoExists(judgerInfoKey);
        // 确认部件存在。
        handlerValidator.makeSureSectionExists(sectionKey);
        // 确认 happenedDate 更新合法。
        handlerValidator.makeSureJudgementModalUpdateHappenedDateValid(sectionKey, happenedDate);
        // 确认 value 更新合法。
        handlerValidator.makeSureJudgementModalUpdateValueValid(value);

        // 构造判断结果模态。
        JudgementModal judgementModal = new JudgementModal(sectionKey, judgerInfoKey, happenedDate, value, message);

        // 调用维护服务插入或更新。
        judgementModalMaintainService.insertOrUpdate(judgementModal);

        // 消息推送。
        try {
            pushHandler.judgementModalUpdated(judgementModal);
        } catch (Exception e) {
            LOGGER.warn("推送判断结果模态更新消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
