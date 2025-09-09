package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackClearInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackInsertInfo;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePack;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePackItemInfo;
import com.dwarfeng.judge.stack.handler.AnalysisFilePackOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisFilePackItemInfoMaintainService;
import com.dwarfeng.judge.stack.service.AnalysisFilePackMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnalysisFilePackOperateHandlerImpl implements AnalysisFilePackOperateHandler {

    private final AnalysisFilePackMaintainService analysisFilePackMaintainService;
    private final AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService;

    private final HandlerValidator handlerValidator;

    public AnalysisFilePackOperateHandlerImpl(
            AnalysisFilePackMaintainService analysisFilePackMaintainService,
            AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.analysisFilePackMaintainService = analysisFilePackMaintainService;
        this.analysisFilePackItemInfoMaintainService = analysisFilePackItemInfoMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public void insert(AnalysisFilePackInsertInfo info) throws HandlerException {
        try {
            insert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void insert0(AnalysisFilePackInsertInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisFilePackKey = info.getAnalysisFilePackKey();

        // 如果指定的分析结果文件包已经存在，则首先清除旧的分析结果文件包。
        if (analysisFilePackMaintainService.exists(analysisFilePackKey)) {
            internalClearAnalysisFilePack(analysisFilePackKey);
        }

        // 根据参数及业务逻辑生成 AnalysisFilePack 对象。
        AnalysisFilePack analysisFilePack = new AnalysisFilePack();
        analysisFilePack.setKey(analysisFilePackKey);
        analysisFilePack.setItemAnchorIndex(0);
        analysisFilePack.setRemark("通过操作处理器插入");

        // 插入或更新分析结果文件包。
        analysisFilePackMaintainService.insertOrUpdate(analysisFilePack);
    }

    @Override
    public void clear(AnalysisFilePackClearInfo info) throws HandlerException {
        try {
            clear0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void clear0(AnalysisFilePackClearInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisFilePackKey = info.getAnalysisFilePackKey();

        // 确认分析结果文件包是否存在。
        handlerValidator.makeSureAnalysisFilePackExists(analysisFilePackKey);

        // 调用内部清理方法。
        internalClearAnalysisFilePack(analysisFilePackKey);
    }

    private void internalClearAnalysisFilePack(LongIdKey analysisFilePackKey) throws Exception {
        // 查找分析结果文件包中的所有文件信息。
        List<LongIdKey> keysToDelete = analysisFilePackItemInfoMaintainService.lookupAsList(
                AnalysisFilePackItemInfoMaintainService.CHILD_FOR_PACK, new Object[]{analysisFilePackKey}
        ).stream().map(AnalysisFilePackItemInfo::getKey).collect(Collectors.toList());
        // 批量删除分析结果文件包中的所有文件信息。
        analysisFilePackItemInfoMaintainService.batchDelete(keysToDelete);

        // 重置分析结果文件包的锚点索引。
        AnalysisFilePack analysisFilePack = analysisFilePackMaintainService.get(analysisFilePackKey);
        analysisFilePack.setItemAnchorIndex(0);
        analysisFilePackMaintainService.update(analysisFilePack);
    }
}
