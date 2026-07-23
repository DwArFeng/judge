package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.AlarmModalUpdateInfo;
import com.dwarfeng.judge.stack.bean.entity.Alarm;
import com.dwarfeng.judge.stack.bean.entity.AlarmHistory;
import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.judge.stack.handler.AlarmModalOperateHandler;
import com.dwarfeng.judge.stack.service.AlarmHistoryMaintainService;
import com.dwarfeng.judge.stack.service.AlarmMaintainService;
import com.dwarfeng.judge.stack.service.AlarmModalMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Component
public class AlarmModalOperateHandlerImpl implements AlarmModalOperateHandler {

    private final AlarmModalMaintainService alarmModalMaintainService;
    private final AlarmMaintainService alarmMaintainService;
    private final AlarmHistoryMaintainService alarmHistoryMaintainService;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final HandlerValidator handlerValidator;

    public AlarmModalOperateHandlerImpl(
            AlarmModalMaintainService alarmModalMaintainService,
            AlarmMaintainService alarmMaintainService,
            AlarmHistoryMaintainService alarmHistoryMaintainService,
            KeyGenerator<LongIdKey> keyGenerator,
            HandlerValidator handlerValidator
    ) {
        this.alarmModalMaintainService = alarmModalMaintainService;
        this.alarmMaintainService = alarmMaintainService;
        this.alarmHistoryMaintainService = alarmHistoryMaintainService;
        this.keyGenerator = keyGenerator;
        this.handlerValidator = handlerValidator;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void updateModal(AlarmModalUpdateInfo info) throws HandlerException {
        try {
            updateModal0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void updateModal0(AlarmModalUpdateInfo info) throws Exception {
        // 展开参数。
        LongIdKey sectionKey = info.getSectionKey();
        String dataId = info.getDataId();
        boolean alarming = info.isAlarming();
        Date happenedDate = info.getHappenedDate();
        String message = info.getMessage();
        String type = info.getType();
        double verdictValue = info.getVerdictValue();
        String verdictMessage = info.getVerdictMessage();

        // 确认更新信息合法。
        handlerValidator.makeSureAlarmModalUpdateInfoValid(info);

        // 构造报警主键与最新模态。
        AlarmKey alarmKey = new AlarmKey(sectionKey.getLongId(), dataId);
        AlarmModal latestModal = new AlarmModal(
                alarmKey, alarming, happenedDate, message, type, verdictValue, verdictMessage
        );

        // 获取旧模态与当前报警。
        Alarm oldAlarm = alarmMaintainService.getIfExists(alarmKey);

        // 插入或更新报警模态。
        alarmModalMaintainService.insertOrUpdate(latestModal);

        // 维护当前报警与报警历史。
        // 如果当前在报警。
        if (alarming) {
            // 首次进入报警状态。
            if (Objects.isNull(oldAlarm)) {
                // 插入或更新当前报警。
                alarmMaintainService.insertOrUpdate(
                        new Alarm(alarmKey, happenedDate, message, type, verdictValue, verdictMessage)
                );
            }
            // 非手册进入报警状态，且新旧报警模态类型不一样时。
            else if (!Objects.equals(oldAlarm.getType(), type)) {
                // 生成并插入报警历史。
                generateAndInsertAlarmHistory(oldAlarm, sectionKey, dataId, happenedDate);
                // 插入或更新当前报警。
                alarmMaintainService.insertOrUpdate(
                        new Alarm(alarmKey, happenedDate, message, type, verdictValue, verdictMessage)
                );
            }
        }
        // 如果当前不在报警，且存在旧报警。
        else if (Objects.nonNull(oldAlarm)) {
            // 生成并插入报警历史。
            generateAndInsertAlarmHistory(oldAlarm, sectionKey, dataId, happenedDate);
            // 删除当前的报警。
            alarmMaintainService.delete(alarmKey);
        }
    }

    private void generateAndInsertAlarmHistory(
            Alarm oldAlarm, LongIdKey sectionKey, String dataId, Date happenedDate
    ) throws Exception {
        // 计算报警持续时长。
        long duration = happenedDate.getTime() - oldAlarm.getHappenedDate().getTime();
        // 构造报警历史并插入。
        AlarmHistory alarmHistory = new AlarmHistory(
                keyGenerator.generate(),
                sectionKey,
                dataId,
                oldAlarm.getHappenedDate(),
                happenedDate,
                duration,
                oldAlarm.getMessage(),
                oldAlarm.getType(),
                oldAlarm.getVerdictValue(),
                oldAlarm.getVerdictMessage()
        );
        // 调用维护服务插入报警历史。
        alarmHistoryMaintainService.insert(alarmHistory);
    }
}
