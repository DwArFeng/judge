package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.AlarmModalUpdateInfo;
import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.judge.stack.handler.AlarmModalOperateHandler;
import com.dwarfeng.judge.stack.handler.PushHandler;
import com.dwarfeng.judge.stack.service.AlarmModalMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AlarmModalOperateHandlerImpl implements AlarmModalOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmModalOperateHandlerImpl.class);

    private final AlarmModalMaintainService alarmModalMaintainService;

    private final PushHandler pushHandler;

    private final HandlerValidator handlerValidator;

    public AlarmModalOperateHandlerImpl(
            AlarmModalMaintainService alarmModalMaintainService,
            PushHandler pushHandler,
            HandlerValidator handlerValidator
    ) {
        this.alarmModalMaintainService = alarmModalMaintainService;
        this.pushHandler = pushHandler;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public void update(AlarmModalUpdateInfo info) throws HandlerException {
        try {
            update0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void update0(AlarmModalUpdateInfo info) throws Exception {
        // 展开参数。
        LongIdKey sectionKey = info.getSectionKey();
        String alarmLevel = info.getAlarmLevel();
        Date happenedDate = info.getHappenedDate();
        boolean alarming = info.isAlarming();
        String alarmMessage = info.getAlarmMessage();

        // 确认部件存在。
        handlerValidator.makeSureSectionExists(sectionKey);
        // 确认 happenedDate 更新合法。
        handlerValidator.makeSureAlarmModalUpdateHappenedDateValid(sectionKey, happenedDate);

        // 构造报警模态。
        AlarmModal alarmModal = new AlarmModal(sectionKey, alarmLevel, happenedDate, alarming, alarmMessage);

        // 调用维护服务插入或更新。
        alarmModalMaintainService.insertOrUpdate(alarmModal);

        // 消息推送。
        try {
            pushHandler.alarmModalUpdated(alarmModal);
        } catch (Exception e) {
            LOGGER.warn("推送报警模态更新消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
