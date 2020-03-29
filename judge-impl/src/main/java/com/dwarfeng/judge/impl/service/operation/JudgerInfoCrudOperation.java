package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.cache.EnabledJudgerInfoCache;
import com.dwarfeng.judge.stack.cache.JudgerInfoCache;
import com.dwarfeng.judge.stack.dao.JudgerInfoDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.CrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JudgerInfoCrudOperation implements CrudOperation<LongIdKey, JudgerInfo> {

    @Autowired
    private JudgerInfoDao judgerInfoDao;

    @Autowired
    private JudgerInfoCache judgerInfoCache;
    @Autowired
    private EnabledJudgerInfoCache enabledJudgerInfoCache;

    @Value("${cache.timeout.entity.judger_info}")
    private long driveTimeout;

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
            judgerInfoCache.push(judgerInfo, driveTimeout);
            return judgerInfo;
        }
    }

    @Override
    public LongIdKey insert(JudgerInfo judgerInfo) throws Exception {
        if (Objects.nonNull(judgerInfo.getSectionKey())) {
            enabledJudgerInfoCache.delete(judgerInfo.getSectionKey());
        }

        judgerInfoCache.push(judgerInfo, driveTimeout);
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

        judgerInfoCache.push(judgerInfo, driveTimeout);
        judgerInfoDao.update(judgerInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        JudgerInfo oldJudgerInfo = get(key);
        if (Objects.nonNull(oldJudgerInfo.getSectionKey())) {
            enabledJudgerInfoCache.delete(oldJudgerInfo.getSectionKey());
        }

        judgerInfoDao.delete(key);
        judgerInfoCache.delete(key);
    }
}
