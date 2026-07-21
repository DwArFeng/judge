package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Alarm;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.judge.stack.service.AlarmMaintainService;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AlarmMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private AlarmMaintainService alarmMaintainService;

    private Section section;
    private List<Alarm> alarms;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        alarms = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Alarm alarm = new Alarm(null, new Date(), "message", "type", 0.5, "verdictMessage");
            alarms.add(alarm);
        }
    }

    @After
    public void tearDown() {
        section = null;
        alarms.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (int i = 0; i < alarms.size(); i++) {
                Alarm alarm = alarms.get(i);
                alarm.setKey(new AlarmKey(section.getKey().getLongId(), "test.alarm." + i));
                alarmMaintainService.insertOrUpdate(alarm);
                assertTrue(alarmMaintainService.exists(alarm.getKey()));
                Alarm testAlarm = alarmMaintainService.get(alarm.getKey());
                assertEquals(BeanUtils.describe(alarm), BeanUtils.describe(testAlarm));
                alarm.setMessage("message.updated");
                alarmMaintainService.update(alarm);
                testAlarm = alarmMaintainService.get(alarm.getKey());
                assertEquals(BeanUtils.describe(alarm), BeanUtils.describe(testAlarm));
                alarmMaintainService.deleteIfExists(alarm.getKey());
                assertFalse(alarmMaintainService.exists(alarm.getKey()));
            }
        } finally {
            for (Alarm alarm : alarms) {
                if (Objects.isNull(alarm.getKey())) {
                    continue;
                }
                alarmMaintainService.deleteIfExists(alarm.getKey());
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
            for (int i = 0; i < alarms.size(); i++) {
                Alarm alarm = alarms.get(i);
                alarm.setKey(new AlarmKey(section.getKey().getLongId(), "test.alarm.cascade." + i));
                alarmMaintainService.insertOrUpdate(alarm);
            }

            assertEquals(
                    alarms.size(),
                    alarmMaintainService.lookupAsList(
                            AlarmMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    alarmMaintainService.lookupAsList(
                            AlarmMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );
            for (Alarm alarm : alarms) {
                assertFalse(alarmMaintainService.exists(alarm.getKey()));
            }
        } finally {
            for (Alarm alarm : alarms) {
                if (Objects.isNull(alarm.getKey())) {
                    continue;
                }
                alarmMaintainService.deleteIfExists(alarm.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
