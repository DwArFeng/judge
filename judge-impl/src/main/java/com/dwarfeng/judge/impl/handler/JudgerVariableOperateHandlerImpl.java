package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableUpsertInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerVariable;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.judge.stack.handler.JudgerVariableOperateHandler;
import com.dwarfeng.judge.stack.service.JudgerVariableMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Objects;

@Component
public class JudgerVariableOperateHandlerImpl implements JudgerVariableOperateHandler {

    private final JudgerVariableMaintainService judgerVariableMaintainService;

    private final HandlerValidator handlerValidator;

    public JudgerVariableOperateHandlerImpl(
            JudgerVariableMaintainService judgerVariableMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.judgerVariableMaintainService = judgerVariableMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Nullable
    @Override
    public JudgerVariableInspectResult inspect(JudgerVariableInspectInfo info) throws HandlerException {
        try {
            return inspect0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private JudgerVariableInspectResult inspect0(JudgerVariableInspectInfo info) throws Exception {
        // 展开参数。
        LongIdKey judgerInfoKey = info.getJudgerInfoKey();
        String judgerVariableId = info.getJudgerVariableId();

        // 确认部件存在。
        handlerValidator.makeSureJudgerInfoExists(judgerInfoKey);

        // 调用维护服务获取判断器变量。
        JudgerVariableKey judgerVariableKey = new JudgerVariableKey(judgerInfoKey.getLongId(), judgerVariableId);
        JudgerVariable judgerVariable = judgerVariableMaintainService.getIfExists(judgerVariableKey);

        // 如果判断器变量不存在，则返回 null。
        if (Objects.isNull(judgerVariable)) {
            return null;
        }

        // 构建返回值并返回。
        String value = judgerVariable.getValue();
        return new JudgerVariableInspectResult(value);
    }

    @Override
    public void upsert(JudgerVariableUpsertInfo info) throws HandlerException {
        try {
            upsert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void upsert0(JudgerVariableUpsertInfo info) throws Exception {
        // 展开参数。
        LongIdKey judgerInfoKey = info.getJudgerInfoKey();
        String judgerVariableId = info.getJudgerVariableId();
        String value = info.getValue();

        // 确认部件存在。
        handlerValidator.makeSureJudgerInfoExists(judgerInfoKey);

        // 构建判断器变量。
        JudgerVariable judgerVariable = new JudgerVariable(
                new JudgerVariableKey(judgerInfoKey.getLongId(), judgerVariableId), value
        );

        // 调用维护服务插入/更新判断器变量。
        judgerVariableMaintainService.insertOrUpdate(judgerVariable);
    }

    @Override
    public void remove(JudgerVariableRemoveInfo info) throws HandlerException {
        try {
            remove0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void remove0(JudgerVariableRemoveInfo info) throws Exception {
        // 展开参数。
        LongIdKey judgerInfoKey = info.getJudgerInfoKey();
        String judgerVariableId = info.getJudgerVariableId();

        // 确认部件存在。
        handlerValidator.makeSureJudgerInfoExists(judgerInfoKey);
        // 确认判断器变量存在。
        JudgerVariableKey judgerVariableKey = new JudgerVariableKey(judgerInfoKey.getLongId(), judgerVariableId);
        handlerValidator.makeSureJudgerVariableExists(judgerVariableKey);

        // 调用维护服务删除判断器变量。
        judgerVariableMaintainService.delete(judgerVariableKey);
    }
}
