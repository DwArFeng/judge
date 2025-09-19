package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.VisualizeDataUpsertInfo;
import com.dwarfeng.judge.stack.bean.entity.VisualizeData;
import com.dwarfeng.judge.stack.bean.key.VisualizeDataKey;
import com.dwarfeng.judge.stack.handler.VisualizeDataOperateHandler;
import com.dwarfeng.judge.stack.service.VisualizeDataMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

@Component
public class VisualizeDataOperateHandlerImpl implements VisualizeDataOperateHandler {

    private final VisualizeDataMaintainService visualizeDataMaintainService;

    private final HandlerValidator handlerValidator;

    public VisualizeDataOperateHandlerImpl(
            VisualizeDataMaintainService visualizeDataMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.visualizeDataMaintainService = visualizeDataMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public void upsert(VisualizeDataUpsertInfo info) throws HandlerException {
        try {
            upsert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void upsert0(VisualizeDataUpsertInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();
        String perspectiveStringId = info.getPerspectiveStringId();
        String content = info.getContent();

        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);

        // 构造可视化数据实体。
        VisualizeData visualizeData = new VisualizeData(
                new VisualizeDataKey(taskKey.getLongId(), perspectiveStringId), content
        );

        // 调用维护服务插入/更新可视化数据。
        visualizeDataMaintainService.insertOrUpdate(visualizeData);
    }
}
