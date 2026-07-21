package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.judge.stack.service.AlarmModalMaintainService;
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
public class AlarmModalMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private AlarmModalMaintainService alarmModalMaintainService;

    private Section section;
    private List<AlarmModal> alarmModals;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        alarmModals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AlarmModal alarmModal = new AlarmModal(
                    null, true, new Date(), "message", "type", 0.5, "verdictMessage"
            );
            alarmModals.add(alarmModal);
        }
    }

    @After
    public void tearDown() {
        section = null;
        alarmModals.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (int i = 0; i < alarmModals.size(); i++) {
                AlarmModal alarmModal = alarmModals.get(i);
                alarmModal.setKey(new AlarmKey(section.getKey().getLongId(), "test.alarm.modal." + i));
                alarmModalMaintainService.insertOrUpdate(alarmModal);
                assertTrue(alarmModalMaintainService.exists(alarmModal.getKey()));
                AlarmModal testAlarmModal = alarmModalMaintainService.get(alarmModal.getKey());
                assertEquals(BeanUtils.describe(alarmModal), BeanUtils.describe(testAlarmModal));
                alarmModal.setMessage("message.updated");
                alarmModalMaintainService.update(alarmModal);
                testAlarmModal = alarmModalMaintainService.get(alarmModal.getKey());
                assertEquals(BeanUtils.describe(alarmModal), BeanUtils.describe(testAlarmModal));
                alarmModalMaintainService.deleteIfExists(alarmModal.getKey());
                assertFalse(alarmModalMaintainService.exists(alarmModal.getKey()));
            }
        } finally {
            for (AlarmModal alarmModal : alarmModals) {
                if (Objects.isNull(alarmModal.getKey())) {
                    continue;
                }
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
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (int i = 0; i < alarmModals.size(); i++) {
                AlarmModal alarmModal = alarmModals.get(i);
                alarmModal.setKey(new AlarmKey(section.getKey().getLongId(), "test.alarm.modal.cascade." + i));
                alarmModalMaintainService.insertOrUpdate(alarmModal);
            }

            assertEquals(
                    alarmModals.size(),
                    alarmModalMaintainService.lookupAsList(
                            AlarmModalMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    alarmModalMaintainService.lookupAsList(
                            AlarmModalMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );
            for (AlarmModal alarmModal : alarmModals) {
                assertFalse(alarmModalMaintainService.exists(alarmModal.getKey()));
            }
        } finally {
            for (AlarmModal alarmModal : alarmModals) {
                if (Objects.isNull(alarmModal.getKey())) {
                    continue;
                }
                alarmModalMaintainService.deleteIfExists(alarmModal.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
