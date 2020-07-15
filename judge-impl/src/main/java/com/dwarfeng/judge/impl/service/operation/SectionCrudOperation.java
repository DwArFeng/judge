package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.cache.DriverInfoCache;
import com.dwarfeng.judge.stack.cache.JudgerInfoCache;
import com.dwarfeng.judge.stack.cache.SectionCache;
import com.dwarfeng.judge.stack.dao.DriverInfoDao;
import com.dwarfeng.judge.stack.dao.JudgerInfoDao;
import com.dwarfeng.judge.stack.dao.SectionDao;
import com.dwarfeng.judge.stack.service.DriverInfoMaintainService;
import com.dwarfeng.judge.stack.service.JudgerInfoMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SectionCrudOperation implements BatchCrudOperation<LongIdKey, Section> {

    @Autowired
    private SectionDao sectionDao;
    @Autowired
    private DriverInfoDao driverInfoDao;
    @Autowired
    private JudgerInfoDao judgerInfoDao;

    @Autowired
    private SectionCache sectionCache;
    @Autowired
    private DriverInfoCache driverInfoCache;
    @Autowired
    private JudgerInfoCache judgerInfoCache;

    @Value("${cache.timeout.entity.section}")
    private long sectionTimeout;

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
        //删除与部件相关的子项。
        {
            //查找部件拥有的过驱动器与判断器。
            List<LongIdKey> driverInfoKeys = driverInfoDao.lookup(DriverInfoMaintainService.CHILD_FOR_SECTION, new Object[]{key})
                    .stream().map(DriverInfo::getKey).collect(Collectors.toList());
            List<LongIdKey> judgerInfoKeys = judgerInfoDao.lookup(JudgerInfoMaintainService.CHILD_FOR_SECTION, new Object[]{key})
                    .stream().map(JudgerInfo::getKey).collect(Collectors.toList());

            //删除点位拥有的驱动器与判断器。
            driverInfoDao.batchDelete(driverInfoKeys);
            driverInfoCache.batchDelete(driverInfoKeys);
            judgerInfoDao.batchDelete(judgerInfoKeys);
            judgerInfoCache.batchDelete(judgerInfoKeys);
        }

        sectionDao.delete(key);
        sectionCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return sectionCache.allExists(keys) || sectionDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return sectionCache.nonExists(keys) && sectionCache.nonExists(keys);
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
