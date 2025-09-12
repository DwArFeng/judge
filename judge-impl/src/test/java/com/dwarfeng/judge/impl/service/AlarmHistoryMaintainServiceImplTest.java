package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmHistory;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.service.AlarmHistoryMaintainService;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AlarmHistoryMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private AlarmHistoryMaintainService alarmHistoryMaintainService;

    private Section section;
    private List<AlarmHistory> alarmHistories;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        alarmHistories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AlarmHistory alarmHistory = new AlarmHistory(
                    null, null, "alarmLevel", new Date(), new Date(), 12450, "alarmMessage"
            );
            alarmHistories.add(alarmHistory);
        }
    }

    @After
    public void tearDown() {
        section = null;
        alarmHistories.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (AlarmHistory alarmHistory : alarmHistories) {
                alarmHistory.setSectionKey(section.getKey());
                alarmHistory.setKey(alarmHistoryMaintainService.insertOrUpdate(alarmHistory));
                AlarmHistory testAlarmHistory = alarmHistoryMaintainService.get(alarmHistory.getKey());
                assertEquals(BeanUtils.describe(alarmHistory), BeanUtils.describe(testAlarmHistory));
                testAlarmHistory = alarmHistoryMaintainService.get(alarmHistory.getKey());
                assertEquals(BeanUtils.describe(alarmHistory), BeanUtils.describe(testAlarmHistory));
            }
        } finally {
            for (AlarmHistory alarmHistory : alarmHistories) {
                if (Objects.isNull(alarmHistory.getKey())) {
                    continue;
                }
                alarmHistoryMaintainService.deleteIfExists(alarmHistory.getKey());
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
            for (AlarmHistory alarmHistory : alarmHistories) {
                alarmHistory.setSectionKey(section.getKey());
                alarmHistory.setKey(alarmHistoryMaintainService.insertOrUpdate(alarmHistory));
            }

            assertEquals(
                    alarmHistories.size(),
                    alarmHistoryMaintainService.lookupAsList(
                            AlarmHistoryMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    alarmHistoryMaintainService.lookupAsList(
                            AlarmHistoryMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            for (AlarmHistory alarmHistory : alarmHistories) {
                assertFalse(alarmHistoryMaintainService.exists(alarmHistory.getKey()));
            }
        } finally {
            for (AlarmHistory alarmHistory : alarmHistories) {
                if (Objects.isNull(alarmHistory.getKey())) {
                    continue;
                }
                alarmHistoryMaintainService.deleteIfExists(alarmHistory.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
