package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.service.AlarmModalMaintainService;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AlarmModalMaintainServiceImplTest {

    private static final Long SECTION_LONG_ID = 12450L;

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private AlarmModalMaintainService alarmModalMaintainService;

    private Section section;
    private AlarmModal alarmModal;

    @Before
    public void setUp() {
        section = new Section(new LongIdKey(SECTION_LONG_ID), "name", true, "remark");
        alarmModal = new AlarmModal(new LongIdKey(SECTION_LONG_ID), new Date(), 12.450, true, "alarmMessage");
    }

    @After
    public void tearDown() {
        section = null;
        alarmModal = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            sectionMaintainService.insertOrUpdate(section);
            alarmModalMaintainService.insertOrUpdate(alarmModal);
            AlarmModal testAlarmModal = alarmModalMaintainService.get(alarmModal.getKey());
            assertEquals(BeanUtils.describe(alarmModal), BeanUtils.describe(testAlarmModal));
            testAlarmModal = alarmModalMaintainService.get(alarmModal.getKey());
            assertEquals(BeanUtils.describe(alarmModal), BeanUtils.describe(testAlarmModal));
        } finally {
            if (Objects.nonNull(alarmModal.getKey())) {
                alarmModalMaintainService.deleteIfExists(alarmModal.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }

    @Test
    public void testForSectionCascade() throws Exception {
        try {
            sectionMaintainService.insertOrUpdate(section);
            alarmModalMaintainService.insertOrUpdate(alarmModal);

            assertTrue(alarmModalMaintainService.exists(alarmModal.getKey()));

            sectionMaintainService.deleteIfExists(section.getKey());

            assertFalse(alarmModalMaintainService.exists(alarmModal.getKey()));
        } finally {
            if (Objects.nonNull(alarmModal.getKey())) {
                alarmModalMaintainService.deleteIfExists(alarmModal.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
