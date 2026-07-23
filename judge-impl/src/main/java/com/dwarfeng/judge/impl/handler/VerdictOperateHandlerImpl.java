package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionInfo;
import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionResult;
import com.dwarfeng.judge.stack.bean.dto.VerdictUpdateByTaskInfo;
import com.dwarfeng.judge.stack.bean.entity.Judgement;
import com.dwarfeng.judge.stack.bean.entity.Verdict;
import com.dwarfeng.judge.stack.bean.key.VerdictKey;
import com.dwarfeng.judge.stack.handler.VerdictOperateHandler;
import com.dwarfeng.judge.stack.service.JudgementMaintainService;
import com.dwarfeng.judge.stack.service.VerdictMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class VerdictOperateHandlerImpl implements VerdictOperateHandler {

    private final VerdictMaintainService verdictMaintainService;
    private final JudgementMaintainService judgementMaintainService;

    private final HandlerValidator handlerValidator;

    public VerdictOperateHandlerImpl(
            VerdictMaintainService verdictMaintainService,
            JudgementMaintainService judgementMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.verdictMaintainService = verdictMaintainService;
        this.judgementMaintainService = judgementMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public VerdictInspectBySectionResult inspectBySection(VerdictInspectBySectionInfo info) throws HandlerException {
        try {
            return inspectBySection0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private VerdictInspectBySectionResult inspectBySection0(VerdictInspectBySectionInfo info) throws Exception {
        // 展开参数。
        LongIdKey sectionKey = info.getSectionKey();

        // 确认部件存在。
        handlerValidator.makeSureSectionExists(sectionKey);
        // 查询该部件的全部裁决结果。
        List<Verdict> verdicts = verdictMaintainService.lookupAsList(
                VerdictMaintainService.CHILD_FOR_SECTION, new Object[]{sectionKey}
        );
        // 构造结果并返回。
        return new VerdictInspectBySectionResult(verdicts);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void updateByTask(VerdictUpdateByTaskInfo info) throws HandlerException {
        try {
            updateByTask0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void updateByTask0(VerdictUpdateByTaskInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();
        LongIdKey sectionKey = info.getSectionKey();

        // 确认更新信息合法。
        handlerValidator.makeSureVerdictUpdateByTaskInfoValid(info);

        // 查询任务下的全部判断结果。
        List<Judgement> judgements = judgementMaintainService.lookupAsList(
                JudgementMaintainService.CHILD_FOR_TASK, new Object[]{taskKey}
        );
        // 如果没有判断结果，则直接返回。
        if (judgements.isEmpty()) {
            return;
        }

        // 根据判断结果构造目标裁决结果。
        List<Verdict> targetVerdicts = new ArrayList<>(judgements.size());
        for (Judgement judgement : judgements) {
            String dataId = judgement.getKey().getDataStringId();
            VerdictKey verdictKey = new VerdictKey(sectionKey.getLongId(), dataId);
            targetVerdicts.add(new Verdict(verdictKey, judgement.getValue(), judgement.getMessage()));
        }

        // 批量插入或更新裁决结果。
        verdictMaintainService.batchInsertOrUpdate(targetVerdicts);
    }
}
