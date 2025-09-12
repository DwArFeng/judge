package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.cache.*;
import com.dwarfeng.judge.stack.dao.*;
import com.dwarfeng.judge.stack.service.*;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SectionCrudOperation implements BatchCrudOperation<LongIdKey, Section> {

    private final SectionDao sectionDao;
    private final SectionCache sectionCache;

    private final JudgementModalDao judgementModalDao;
    private final JudgementModalCache judgementModalCache;

    private final AlarmModalDao alarmModalDao;
    private final AlarmModalCache alarmModalCache;

    private final DriverInfoDao driverInfoDao;
    private final DriverInfoCache driverInfoCache;

    private final AnalyserInfoCrudOperation analyserInfoCrudOperation;
    private final AnalyserInfoDao analyserInfoDao;

    private final JudgerInfoCrudOperation judgerInfoCrudOperation;
    private final JudgerInfoDao judgerInfoDao;

    private final TaskCrudOperation taskCrudOperation;
    private final TaskDao taskDao;

    private final JudgementHistoryDao judgementHistoryDao;
    private final JudgementHistoryCache judgementHistoryCache;

    private final AlarmSettingDao alarmSettingDao;
    private final AlarmSettingCache alarmSettingCache;

    private final AlarmHistoryDao alarmHistoryDao;
    private final AlarmHistoryCache alarmHistoryCache;

    @Value("${cache.timeout.entity.section}")
    private long sectionTimeout;

    public SectionCrudOperation(
            SectionDao sectionDao,
            SectionCache sectionCache,
            JudgementModalDao judgementModalDao,
            JudgementModalCache judgementModalCache,
            AlarmModalDao alarmModalDao,
            AlarmModalCache alarmModalCache,
            DriverInfoDao driverInfoDao,
            DriverInfoCache driverInfoCache,
            AnalyserInfoCrudOperation analyserInfoCrudOperation,
            AnalyserInfoDao analyserInfoDao,
            JudgerInfoCrudOperation judgerInfoCrudOperation,
            JudgerInfoDao judgerInfoDao,
            TaskCrudOperation taskCrudOperation,
            TaskDao taskDao,
            JudgementHistoryDao judgementHistoryDao,
            JudgementHistoryCache judgementHistoryCache,
            AlarmSettingDao alarmSettingDao,
            AlarmSettingCache alarmSettingCache,
            AlarmHistoryDao alarmHistoryDao,
            AlarmHistoryCache alarmHistoryCache
    ) {
        this.sectionDao = sectionDao;
        this.sectionCache = sectionCache;
        this.judgementModalDao = judgementModalDao;
        this.judgementModalCache = judgementModalCache;
        this.alarmModalDao = alarmModalDao;
        this.alarmModalCache = alarmModalCache;
        this.driverInfoDao = driverInfoDao;
        this.driverInfoCache = driverInfoCache;
        this.analyserInfoCrudOperation = analyserInfoCrudOperation;
        this.analyserInfoDao = analyserInfoDao;
        this.judgerInfoCrudOperation = judgerInfoCrudOperation;
        this.judgerInfoDao = judgerInfoDao;
        this.taskCrudOperation = taskCrudOperation;
        this.taskDao = taskDao;
        this.judgementHistoryDao = judgementHistoryDao;
        this.judgementHistoryCache = judgementHistoryCache;
        this.alarmSettingDao = alarmSettingDao;
        this.alarmSettingCache = alarmSettingCache;
        this.alarmHistoryDao = alarmHistoryDao;
        this.alarmHistoryCache = alarmHistoryCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return sectionCache.exists(key) || sectionDao.exists(key);
    }

    @Override
    public Section get(LongIdKey key) throws Exception {
        if (sectionCache.exists(key)) {
            return sectionCache.get(key);
        } else {
            if (!sectionDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Section section = sectionDao.get(key);
            sectionCache.push(section, sectionTimeout);
            return section;
        }
    }

    @Override
    public LongIdKey insert(Section section) throws Exception {
        sectionCache.push(section, sectionTimeout);
        return sectionDao.insert(section);
    }

    @Override
    public void update(Section section) throws Exception {
        sectionCache.push(section, sectionTimeout);
        sectionDao.update(section);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与 部件 相关的 判断结果模态。
        if (judgementModalDao.exists(key)) {
            judgementModalDao.delete(key);
            judgementModalCache.delete(key);
        }

        // 删除与 部件 相关的 报警模态。
        if (alarmModalDao.exists(key)) {
            alarmModalDao.delete(key);
            alarmModalCache.delete(key);
        }

        // 删除与 统计设置 相关的 驱动器信息。
        List<LongIdKey> driverInfoKeys = driverInfoDao.lookup(
                DriverInfoMaintainService.CHILD_FOR_SECTION, new Object[]{key}
        ).stream().map(DriverInfo::getKey).collect(Collectors.toList());
        driverInfoDao.batchDelete(driverInfoKeys);
        driverInfoCache.batchDelete(driverInfoKeys);

        // 删除与 统计设置 相关的 分析器信息。
        List<LongIdKey> analyserInfoKeys = analyserInfoDao.lookup(
                AnalyserInfoMaintainService.CHILD_FOR_SECTION, new Object[]{key}
        ).stream().map(AnalyserInfo::getKey).collect(Collectors.toList());
        analyserInfoCrudOperation.batchDelete(analyserInfoKeys);

        // 删除与 统计设置 相关的 判断器信息。
        List<LongIdKey> judgerInfoKeys = judgerInfoDao.lookup(
                JudgerInfoMaintainService.CHILD_FOR_SECTION, new Object[]{key}
        ).stream().map(JudgerInfo::getKey).collect(Collectors.toList());
        judgerInfoCrudOperation.batchDelete(judgerInfoKeys);

        // 删除与 统计设置 相关的 任务。
        List<LongIdKey> taskKeys = taskDao.lookup(
                TaskMaintainService.CHILD_FOR_SECTION, new Object[]{key}
        ).stream().map(Task::getKey).collect(Collectors.toList());
        taskCrudOperation.batchDelete(taskKeys);

        // 删除与 统计设置 相关的 判断结果历史。
        List<LongIdKey> judgementHistoryKeys = judgementHistoryDao.lookup(
                JudgementHistoryMaintainService.CHILD_FOR_SECTION, new Object[]{key}
        ).stream().map(JudgementHistory::getKey).collect(Collectors.toList());
        judgementHistoryDao.batchDelete(judgementHistoryKeys);
        judgementHistoryCache.batchDelete(judgementHistoryKeys);

        // 删除与 部件 相关的 报警设置。
        List<LongIdKey> alarmSettingKeys = alarmSettingDao.lookup(
                AlarmSettingMaintainService.CHILD_FOR_SECTION, new Object[]{key}
        ).stream().map(AlarmSetting::getKey).collect(Collectors.toList());
        alarmSettingDao.batchDelete(alarmSettingKeys);
        alarmSettingCache.batchDelete(alarmSettingKeys);

        // 删除与 部件 相关的 报警历史。
        List<LongIdKey> alarmHistoryKeys = alarmHistoryDao.lookup(
                AlarmHistoryMaintainService.CHILD_FOR_SECTION, new Object[]{key}
        ).stream().map(AlarmHistory::getKey).collect(Collectors.toList());
        alarmHistoryDao.batchDelete(alarmHistoryKeys);
        alarmHistoryCache.batchDelete(alarmHistoryKeys);

        // 删除 部件 自身。
        sectionDao.delete(key);
        sectionCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return sectionCache.allExists(keys) || sectionDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return sectionCache.nonExists(keys) && sectionDao.nonExists(keys);
    }

    @Override
    public List<Section> batchGet(List<LongIdKey> keys) throws Exception {
        if (sectionCache.allExists(keys)) {
            return sectionCache.batchGet(keys);
        } else {
            if (!sectionDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Section> sections = sectionDao.batchGet(keys);
            sectionCache.batchPush(sections, sectionTimeout);
            return sections;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Section> sections) throws Exception {
        sectionCache.batchPush(sections, sectionTimeout);
        return sectionDao.batchInsert(sections);
    }

    @Override
    public void batchUpdate(List<Section> sections) throws Exception {
        sectionCache.batchPush(sections, sectionTimeout);
        sectionDao.batchUpdate(sections);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
