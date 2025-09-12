package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.JudgementHistory;
import com.dwarfeng.judge.stack.bean.entity.JudgementModal;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerVariable;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.judge.stack.cache.JudgementHistoryCache;
import com.dwarfeng.judge.stack.cache.JudgementModalCache;
import com.dwarfeng.judge.stack.cache.JudgerInfoCache;
import com.dwarfeng.judge.stack.cache.JudgerVariableCache;
import com.dwarfeng.judge.stack.dao.JudgementHistoryDao;
import com.dwarfeng.judge.stack.dao.JudgementModalDao;
import com.dwarfeng.judge.stack.dao.JudgerInfoDao;
import com.dwarfeng.judge.stack.dao.JudgerVariableDao;
import com.dwarfeng.judge.stack.service.JudgementHistoryMaintainService;
import com.dwarfeng.judge.stack.service.JudgementModalMaintainService;
import com.dwarfeng.judge.stack.service.JudgerVariableMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JudgerInfoCrudOperation implements BatchCrudOperation<LongIdKey, JudgerInfo> {

    private final JudgerInfoDao judgerInfoDao;
    private final JudgerInfoCache judgerInfoCache;

    private final JudgerVariableDao judgerVariableDao;
    private final JudgerVariableCache judgerVariableCache;

    private final JudgementModalDao judgementModalDao;
    private final JudgementModalCache judgementModalCache;

    private final JudgementHistoryDao judgementHistoryDao;
    private final JudgementHistoryCache judgementHistoryCache;

    @Value("${cache.timeout.entity.judger_info}")
    private long judgerInfoTimeout;

    public JudgerInfoCrudOperation(
            JudgerInfoDao judgerInfoDao,
            JudgerInfoCache judgerInfoCache,
            JudgerVariableDao judgerVariableDao,
            JudgerVariableCache judgerVariableCache,
            JudgementModalDao judgementModalDao,
            JudgementModalCache judgementModalCache,
            JudgementHistoryDao judgementHistoryDao,
            JudgementHistoryCache judgementHistoryCache
    ) {
        this.judgerInfoDao = judgerInfoDao;
        this.judgerInfoCache = judgerInfoCache;
        this.judgerVariableDao = judgerVariableDao;
        this.judgerVariableCache = judgerVariableCache;
        this.judgementModalDao = judgementModalDao;
        this.judgementModalCache = judgementModalCache;
        this.judgementHistoryDao = judgementHistoryDao;
        this.judgementHistoryCache = judgementHistoryCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return judgerInfoCache.exists(key) || judgerInfoDao.exists(key);
    }

    @Override
    public JudgerInfo get(LongIdKey key) throws Exception {
        if (judgerInfoCache.exists(key)) {
            return judgerInfoCache.get(key);
        } else {
            if (!judgerInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            JudgerInfo judgerInfo = judgerInfoDao.get(key);
            judgerInfoCache.push(judgerInfo, judgerInfoTimeout);
            return judgerInfo;
        }
    }

    @Override
    public LongIdKey insert(JudgerInfo judgerInfo) throws Exception {
        judgerInfoCache.push(judgerInfo, judgerInfoTimeout);
        return judgerInfoDao.insert(judgerInfo);
    }

    @Override
    public void update(JudgerInfo judgerInfo) throws Exception {
        judgerInfoCache.push(judgerInfo, judgerInfoTimeout);
        judgerInfoDao.update(judgerInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与 判断器信息 相关的 判断器变量。
        List<JudgerVariableKey> judgerVariableKeys = judgerVariableDao.lookup(
                JudgerVariableMaintainService.CHILD_FOR_JUDGER_INFO, new Object[]{key}
        ).stream().map(JudgerVariable::getKey).collect(Collectors.toList());
        judgerVariableDao.batchDelete(judgerVariableKeys);
        judgerVariableCache.batchDelete(judgerVariableKeys);

        // 解除与 判断器信息 相关的 判断结果模态 的关联。
        List<JudgementModal> judgementModals = judgementModalDao.lookup(
                JudgementModalMaintainService.CHILD_FOR_JUDGER_INFO, new Object[]{key}
        );
        judgementModals.forEach(judgementModal -> judgementModal.setJudgerInfoKey(null));
        judgementModalDao.batchUpdate(judgementModals);
        judgementModalCache.batchDelete(
                judgementModals.stream().map(JudgementModal::getKey).collect(Collectors.toList())
        );

        // 解除与 判断器信息 相关的 判断结果历史 的关联。
        List<JudgementHistory> judgementHistories = judgementHistoryDao.lookup(
                JudgementHistoryMaintainService.CHILD_FOR_JUDGER_INFO, new Object[]{key}
        );
        judgementHistories.forEach(judgementHistory -> judgementHistory.setJudgerInfoKey(null));
        judgementHistoryDao.batchUpdate(judgementHistories);
        judgementHistoryCache.batchDelete(
                judgementHistories.stream().map(JudgementHistory::getKey).collect(Collectors.toList())
        );

        // 删除 判断器信息 自身。
        judgerInfoDao.delete(key);
        judgerInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return judgerInfoCache.allExists(keys) || judgerInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return judgerInfoCache.nonExists(keys) && judgerInfoDao.nonExists(keys);
    }

    @Override
    public List<JudgerInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (judgerInfoCache.allExists(keys)) {
            return judgerInfoCache.batchGet(keys);
        } else {
            if (!judgerInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<JudgerInfo> judgerInfos = judgerInfoDao.batchGet(keys);
            judgerInfoCache.batchPush(judgerInfos, judgerInfoTimeout);
            return judgerInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<JudgerInfo> judgerInfos) throws Exception {
        judgerInfoCache.batchPush(judgerInfos, judgerInfoTimeout);
        return judgerInfoDao.batchInsert(judgerInfos);
    }

    @Override
    public void batchUpdate(List<JudgerInfo> judgerInfos) throws Exception {
        judgerInfoCache.batchPush(judgerInfos, judgerInfoTimeout);
        judgerInfoDao.batchUpdate(judgerInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
