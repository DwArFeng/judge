package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.cache.EnabledDriverInfoCache;
import com.dwarfeng.judge.stack.service.DriverInfoMaintainService;
import com.dwarfeng.judge.stack.service.EnabledDriverInfoLookupService;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class EnabledDriverInfoLookupServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private DriverInfoMaintainService driverInfoMaintainService;
    @Autowired
    private EnabledDriverInfoLookupService enabledDriverInfoLookupService;
    @Autowired
    private EnabledDriverInfoCache enabledDriverInfoCache;

    private Section parentSection;
    private List<DriverInfo> driverInfos;

    @Before
    public void setUp() {
        parentSection = new Section(
                null,
                "parent-section",
                true,
                0,
                1,
                "test-section"
        );
        driverInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DriverInfo driverInfo = new DriverInfo(
                    null,
                    parentSection.getKey(),
                    true,
                    "filter-info-enabled-" + i,
                    "this is a test",
                    "test"
            );
            driverInfos.add(driverInfo);
        }
        for (int i = 0; i < 5; i++) {
            DriverInfo driverInfo = new DriverInfo(
                    null,
                    parentSection.getKey(),
                    false,
                    "filter-info-disabled-" + i,
                    "this is a test",
                    "test"
            );
            driverInfos.add(driverInfo);
        }
    }

    @After
    public void tearDown() {
        parentSection = null;
        driverInfos.clear();
    }

    @Test
    public void testDriverInfoCascade() throws ServiceException, CacheException {
        try {
            parentSection.setKey(sectionMaintainService.insertOrUpdate(parentSection));
            for (DriverInfo driverInfo : driverInfos) {
                driverInfo.setKey(driverInfoMaintainService.insertOrUpdate(driverInfo));
                driverInfo.setSectionKey(parentSection.getKey());
                driverInfoMaintainService.update(driverInfo);
            }
            assertEquals(5, driverInfoMaintainService.lookup(DriverInfoMaintainService.ENABLED_CHILD_FOR_SECTION, new Object[]{parentSection.getKey()}).getCount());
            assertEquals(5, enabledDriverInfoLookupService.getEnabledDriverInfos(parentSection.getKey()).size());
            assertEquals(5, enabledDriverInfoCache.get(parentSection.getKey()).size());
            DriverInfo driverInfo = driverInfos.get(0);
            driverInfoMaintainService.deleteIfExists(driverInfo.getKey());
            assertEquals(0, enabledDriverInfoCache.get(parentSection.getKey()).size());
            driverInfoMaintainService.insertOrUpdate(driverInfo);
            assertEquals(0, enabledDriverInfoCache.get(parentSection.getKey()).size());
            assertEquals(5, driverInfoMaintainService.lookup(DriverInfoMaintainService.ENABLED_CHILD_FOR_SECTION, new Object[]{parentSection.getKey()}).getCount());
            assertEquals(5, enabledDriverInfoLookupService.getEnabledDriverInfos(parentSection.getKey()).size());
            assertEquals(5, enabledDriverInfoCache.get(parentSection.getKey()).size());
        } finally {
            for (DriverInfo driverInfo : driverInfos) {
                driverInfoMaintainService.deleteIfExists(driverInfo.getKey());
            }
            sectionMaintainService.deleteIfExists(parentSection.getKey());
        }
    }

    @Test
    public void testSectionCascade() throws ServiceException, CacheException {
        try {
            parentSection.setKey(sectionMaintainService.insertOrUpdate(parentSection));
            for (DriverInfo driverInfo : driverInfos) {
                driverInfo.setKey(driverInfoMaintainService.insertOrUpdate(driverInfo));
                driverInfo.setSectionKey(parentSection.getKey());
                driverInfoMaintainService.update(driverInfo);
            }
            assertEquals(5, driverInfoMaintainService.lookup(DriverInfoMaintainService.ENABLED_CHILD_FOR_SECTION, new Object[]{parentSection.getKey()}).getCount());
            assertEquals(5, enabledDriverInfoLookupService.getEnabledDriverInfos(parentSection.getKey()).size());
            assertEquals(5, enabledDriverInfoCache.get(parentSection.getKey()).size());
            sectionMaintainService.deleteIfExists(parentSection.getKey());
            assertEquals(0, enabledDriverInfoCache.get(parentSection.getKey()).size());
            assertEquals(0, driverInfoMaintainService.lookup(DriverInfoMaintainService.ENABLED_CHILD_FOR_SECTION, new Object[]{parentSection.getKey()}).getCount());
            assertEquals(0, enabledDriverInfoLookupService.getEnabledDriverInfos(parentSection.getKey()).size());
        } finally {
            for (DriverInfo driverInfo : driverInfos) {
                driverInfoMaintainService.deleteIfExists(driverInfo.getKey());
            }
            sectionMaintainService.deleteIfExists(parentSection.getKey());
        }
    }
}
