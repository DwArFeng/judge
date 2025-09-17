package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgementInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgementInspectResult;
import com.dwarfeng.judge.stack.bean.dto.JudgementRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgementUpsertInfo;
import com.dwarfeng.judge.stack.bean.entity.Judgement;
import com.dwarfeng.judge.stack.bean.key.JudgementKey;
import com.dwarfeng.judge.stack.handler.JudgementOperateHandler;
import com.dwarfeng.judge.stack.service.JudgementMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Objects;

@Component
public class JudgementOperateHandlerImpl implements JudgementOperateHandler {

    private final JudgementMaintainService judgementMaintainService;

    private final HandlerValidator handlerValidator;

    public JudgementOperateHandlerImpl(
            JudgementMaintainService judgementMaintainService,
            KeyGenerator<LongIdKey> longIdKeyGenerator,
            HandlerValidator handlerValidator
    ) {
        this.judgementMaintainService = judgementMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Nullable
    @Override
    public JudgementInspectResult inspect(JudgementInspectInfo info) throws HandlerException {
        try {
            return inspect0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private JudgementInspectResult inspect0(JudgementInspectInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();
        String dataStringId = info.getDataStringId();

        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);

        // 构造判断结果主键。
        JudgementKey judgementKey = new JudgementKey(taskKey.getLongId(), dataStringId);

        // 获取判断结果。
        Judgement judgement = judgementMaintainService.getIfExists(judgementKey);

        // 如果判断结果不存在，则返回 null。
        if (Objects.isNull(judgement)) {
            return null;
        }

        // 构造判断结果查看结果并返回。
        return new JudgementInspectResult(judgement.getValue(), judgement.getMessage());
    }

    @Override
    public void upsert(JudgementUpsertInfo info) throws HandlerException {
        try {
            upsert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void upsert0(JudgementUpsertInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();
        String dataStringId = info.getDataStringId();
        double value = info.getValue();
        String message = info.getMessage();

        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);
        // 确认判断值合法。
        handlerValidator.makeSureJudgementValueValid(value);

        // 构造判断结果。
        Judgement judgement = new Judgement(new JudgementKey(taskKey.getLongId(), dataStringId), value, message);

        // 插入或更新判断结果。
        judgementMaintainService.insertOrUpdate(judgement);
    }

    @Override
    public void remove(JudgementRemoveInfo info) throws HandlerException {
        try {
            remove0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void remove0(JudgementRemoveInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();
        String dataStringId = info.getDataStringId();

        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);
        // 确认判断结果存在。
        JudgementKey judgementKey = new JudgementKey(taskKey.getLongId(), dataStringId);
        handlerValidator.makeSureJudgementExists(judgementKey);

        // 删除判断结果。
        judgementMaintainService.delete(judgementKey);
    }
}
