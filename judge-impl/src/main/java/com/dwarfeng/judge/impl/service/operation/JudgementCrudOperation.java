package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.Judgement;
import com.dwarfeng.judge.stack.bean.key.JudgementKey;
import com.dwarfeng.judge.stack.cache.JudgementCache;
import com.dwarfeng.judge.stack.dao.JudgementDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JudgementCrudOperation implements BatchCrudOperation<JudgementKey, Judgement> {

    private final JudgementDao judgementDao;
    private final JudgementCache judgementCache;

    @Value("${cache.timeout.entity.judgement}")
    private long judgementTimeout;

    public JudgementCrudOperation(
            JudgementDao judgementDao,
            JudgementCache judgementCache
    ) {
        this.judgementDao = judgementDao;
        this.judgementCache = judgementCache;
    }

    @Override
    public boolean exists(JudgementKey key) throws Exception {
        return judgementCache.exists(key) || judgementDao.exists(key);
    }

    @Override
    public Judgement get(JudgementKey key) throws Exception {
        if (judgementCache.exists(key)) {
            return judgementCache.get(key);
        } else {
            if (!judgementDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Judgement judgement = judgementDao.get(key);
            judgementCache.push(judgement, judgementTimeout);
            return judgement;
        }
    }

    @Override
    public JudgementKey insert(Judgement judgement) throws Exception {
        judgementCache.push(judgement, judgementTimeout);
        return judgementDao.insert(judgement);
    }

    @Override
    public void update(Judgement judgement) throws Exception {
        judgementCache.push(judgement, judgementTimeout);
        judgementDao.update(judgement);
    }

    @Override
    public void delete(JudgementKey key) throws Exception {
        // 删除 判断结果 自身。
        judgementDao.delete(key);
        judgementCache.delete(key);
    }

    @Override
    public boolean allExists(List<JudgementKey> keys) throws Exception {
        return judgementCache.allExists(keys) || judgementDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<JudgementKey> keys) throws Exception {
        return judgementCache.nonExists(keys) && judgementDao.nonExists(keys);
    }

    @Override
    public List<Judgement> batchGet(List<JudgementKey> keys) throws Exception {
        if (judgementCache.allExists(keys)) {
            return judgementCache.batchGet(keys);
        } else {
            if (!judgementDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Judgement> judgements = judgementDao.batchGet(keys);
            judgementCache.batchPush(judgements, judgementTimeout);
            return judgements;
        }
    }

    @Override
    public List<JudgementKey> batchInsert(List<Judgement> judgements) throws Exception {
        judgementCache.batchPush(judgements, judgementTimeout);
        return judgementDao.batchInsert(judgements);
    }

    @Override
    public void batchUpdate(List<Judgement> judgements) throws Exception {
        judgementCache.batchPush(judgements, judgementTimeout);
        judgementDao.batchUpdate(judgements);
    }

    @Override
    public void batchDelete(List<JudgementKey> keys) throws Exception {
        for (JudgementKey key : keys) {
            delete(key);
        }
    }
}
