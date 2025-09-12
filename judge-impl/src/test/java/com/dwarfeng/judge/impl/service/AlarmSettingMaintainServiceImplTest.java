package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmSetting;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.service.AlarmSettingMaintainService;
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
public class AlarmSettingMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private AlarmSettingMaintainService alarmSettingMaintainService;

    private Section section;
    private List<AlarmSetting> alarmSettings;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        alarmSettings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AlarmSetting alarmSetting = new AlarmSetting(null, null, true, 12.450, "alarmMessage", "remark");
            alarmSettings.add(alarmSetting);
        }
    }

    @After
    public void tearDown() {
        section = null;
        alarmSettings.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (AlarmSetting alarmSetting : alarmSettings) {
                alarmSetting.setSectionKey(section.getKey());
                alarmSetting.setKey(alarmSettingMaintainService.insertOrUpdate(alarmSetting));
                AlarmSetting testAlarmSetting = alarmSettingMaintainService.get(alarmSetting.getKey());
                assertEquals(BeanUtils.describe(alarmSetting), BeanUtils.describe(testAlarmSetting));
                testAlarmSetting = alarmSettingMaintainService.get(alarmSetting.getKey());
                assertEquals(BeanUtils.describe(alarmSetting), BeanUtils.describe(testAlarmSetting));
            }
        } finally {
            for (AlarmSetting alarmSetting : alarmSettings) {
                if (Objects.isNull(alarmSetting.getKey())) {
                    continue;
                }
                alarmSettingMaintainService.deleteIfExists(alarmSetting.getKey());
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
            for (AlarmSetting alarmSetting : alarmSettings) {
                alarmSetting.setSectionKey(section.getKey());
                alarmSetting.setKey(alarmSettingMaintainService.insertOrUpdate(alarmSetting));
            }

            assertEquals(
                    alarmSettings.size(),
                    alarmSettingMaintainService.lookupAsList(
                            AlarmSettingMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    alarmSettingMaintainService.lookupAsList(
                            AlarmSettingMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            for (AlarmSetting alarmSetting : alarmSettings) {
                assertFalse(alarmSettingMaintainService.exists(alarmSetting.getKey()));
            }
        } finally {
            for (AlarmSetting alarmSetting : alarmSettings) {
                if (Objects.isNull(alarmSetting.getKey())) {
                    continue;
                }
                alarmSettingMaintainService.deleteIfExists(alarmSetting.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
