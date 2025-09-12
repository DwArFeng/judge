package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.service.DriverInfoMaintainService;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class DriverInfoMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private DriverInfoMaintainService driverInfoMaintainService;

    private Section section;
    private List<DriverInfo> driverInfos;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        driverInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DriverInfo driverInfo = new DriverInfo(null, null, true, "type", "param", "remark");
            driverInfos.add(driverInfo);
        }
    }

    @After
    public void tearDown() {
        section = null;
        driverInfos.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (DriverInfo driverInfo : driverInfos) {
                driverInfo.setSectionKey(section.getKey());
                driverInfo.setKey(driverInfoMaintainService.insertOrUpdate(driverInfo));
                DriverInfo testDriverInfo = driverInfoMaintainService.get(driverInfo.getKey());
                assertEquals(BeanUtils.describe(driverInfo), BeanUtils.describe(testDriverInfo));
                testDriverInfo = driverInfoMaintainService.get(driverInfo.getKey());
                assertEquals(BeanUtils.describe(driverInfo), BeanUtils.describe(testDriverInfo));
            }
        } finally {
            for (DriverInfo driverInfo : driverInfos) {
                if (Objects.isNull(driverInfo.getKey())) {
                    continue;
                }
                driverInfoMaintainService.deleteIfExists(driverInfo.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }

    @Test
    public void testForSectionCascade() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (DriverInfo driverInfo : driverInfos) {
                driverInfo.setSectionKey(section.getKey());
                driverInfo.setKey(driverInfoMaintainService.insertOrUpdate(driverInfo));
            }

            assertEquals(
                    driverInfos.size(),
                    driverInfoMaintainService.lookupAsList(
                            DriverInfoMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    driverInfoMaintainService.lookupAsList(
                            DriverInfoMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            for (DriverInfo driverInfo : driverInfos) {
                assertFalse(driverInfoMaintainService.exists(driverInfo.getKey()));
            }
        } finally {
            for (DriverInfo driverInfo : driverInfos) {
                if (Objects.isNull(driverInfo.getKey())) {
                    continue;
                }
                driverInfoMaintainService.deleteIfExists(driverInfo.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
