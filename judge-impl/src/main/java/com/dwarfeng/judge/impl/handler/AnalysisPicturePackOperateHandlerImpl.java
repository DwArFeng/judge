package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.AnalysisPicturePackClearInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisPicturePackInsertInfo;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePack;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePackItemInfo;
import com.dwarfeng.judge.stack.handler.AnalysisPicturePackOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisPicturePackItemInfoMaintainService;
import com.dwarfeng.judge.stack.service.AnalysisPicturePackMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnalysisPicturePackOperateHandlerImpl implements AnalysisPicturePackOperateHandler {

    private final AnalysisPicturePackMaintainService analysisPicturePackMaintainService;
    private final AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService;

    private final HandlerValidator handlerValidator;

    public AnalysisPicturePackOperateHandlerImpl(
            AnalysisPicturePackMaintainService analysisPicturePackMaintainService,
            AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.analysisPicturePackMaintainService = analysisPicturePackMaintainService;
        this.analysisPicturePackItemInfoMaintainService = analysisPicturePackItemInfoMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public void insert(AnalysisPicturePackInsertInfo info) throws HandlerException {
        try {
            insert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void insert0(AnalysisPicturePackInsertInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisPicturePackKey = info.getAnalysisPicturePackKey();

        // 如果指定的分析结果图片包已经存在，则首先清除旧的分析结果图片包。
        if (analysisPicturePackMaintainService.exists(analysisPicturePackKey)) {
            internalClearAnalysisPicturePack(analysisPicturePackKey);
        }

        // 根据参数及业务逻辑生成 AnalysisPicturePack 对象。
        AnalysisPicturePack analysisPicturePack = new AnalysisPicturePack();
        analysisPicturePack.setKey(analysisPicturePackKey);
        analysisPicturePack.setItemAnchorIndex(0);
        analysisPicturePack.setRemark("通过操作处理器插入");

        // 插入或更新分析结果图片包。
        analysisPicturePackMaintainService.insertOrUpdate(analysisPicturePack);
    }

    @Override
    public void clear(AnalysisPicturePackClearInfo info) throws HandlerException {
        try {
            clear0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void clear0(AnalysisPicturePackClearInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisPicturePackKey = info.getAnalysisPicturePackKey();

        // 确认分析结果图片包是否存在。
        handlerValidator.makeSureAnalysisPicturePackExists(analysisPicturePackKey);

        // 调用内部清理方法。
        internalClearAnalysisPicturePack(analysisPicturePackKey);
    }

    private void internalClearAnalysisPicturePack(LongIdKey analysisPicturePackKey) throws Exception {
        // 查找分析结果图片包中的所有图片信息。
        List<LongIdKey> keysToDelete = analysisPicturePackItemInfoMaintainService.lookupAsList(
                AnalysisPicturePackItemInfoMaintainService.CHILD_FOR_PACK, new Object[]{analysisPicturePackKey}
        ).stream().map(AnalysisPicturePackItemInfo::getKey).collect(Collectors.toList());
        // 批量删除分析结果图片包中的所有图片信息。
        analysisPicturePackItemInfoMaintainService.batchDelete(keysToDelete);

        // 重置分析结果图片包的锚点索引。
        AnalysisPicturePack analysisPicturePack = analysisPicturePackMaintainService.get(analysisPicturePackKey);
        analysisPicturePack.setItemAnchorIndex(0);
        analysisPicturePackMaintainService.update(analysisPicturePack);
    }
}
