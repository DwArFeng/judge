package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Variable;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.judge.stack.cache.EnabledJudgerInfoCache;
import com.dwarfeng.judge.stack.cache.JudgerInfoCache;
import com.dwarfeng.judge.stack.cache.VariableCache;
import com.dwarfeng.judge.stack.dao.JudgerInfoDao;
import com.dwarfeng.judge.stack.dao.VariableDao;
import com.dwarfeng.judge.stack.service.VariableMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JudgerInfoCrudOperation implements BatchCrudOperation<LongIdKey, JudgerInfo> {

    @Autowired
    private JudgerInfoDao judgerInfoDao;
    @Autowired
    private VariableDao variableDao;

    @Autowired
    private JudgerInfoCache judgerInfoCache;
    @Autowired
    private VariableCache variableCache;

    @Autowired
    private EnabledJudgerInfoCache enabledJudgerInfoCache;

    @Value("${cache.timeout.entity.judger_info}")
    private long judgerInfoTimeout;

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
        if (Objects.nonNull(judgerInfo.getSectionKey())) {
            enabledJudgerInfoCache.delete(judgerInfo.getSectionKey());
        }

        judgerInfoCache.push(judgerInfo, judgerInfoTimeout);
        return judgerInfoDao.insert(judgerInfo);
    }

    @Override
    public void update(JudgerInfo judgerInfo) throws Exception {
        JudgerInfo oldJudgerInfo = get(judgerInfo.getKey());
        if (Objects.nonNull(oldJudgerInfo.getSectionKey())) {
            enabledJudgerInfoCache.delete(oldJudgerInfo.getSectionKey());
        }

        if (Objects.nonNull(judgerInfo.getSectionKey())) {
            enabledJudgerInfoCache.delete(judgerInfo.getSectionKey());
        }

        judgerInfoCache.push(judgerInfo, judgerInfoTimeout);
        judgerInfoDao.update(judgerInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        JudgerInfo oldJudgerInfo = get(key);
        if (Objects.nonNull(oldJudgerInfo.getSectionKey())) {
            enabledJudgerInfoCache.delete(oldJudgerInfo.getSectionKey());
        }

        // 删除变量。
        List<VariableKey> variableKeys = variableDao.lookup(VariableMaintainService.LONG_ID_EQUALS, new Object[]{key})
                .stream().map(Variable::getKey).collect(Collectors.toList());
        variableCache.batchDelete(variableKeys);
        variableDao.batchDelete(variableKeys);

        judgerInfoDao.delete(key);
        judgerInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return judgerInfoCache.allExists(keys) || judgerInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return judgerInfoCache.nonExists(keys) && judgerInfoCache.nonExists(keys);
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
        List<LongIdKey> keys = new ArrayList<>();
        for (JudgerInfo judgerInfo : judgerInfos) {
            keys.add(insert(judgerInfo));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<JudgerInfo> judgerInfos) throws Exception {
        for (JudgerInfo judgerInfo : judgerInfos) {
            update(judgerInfo);
        }
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
