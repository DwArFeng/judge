package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmTypeIndicator;
import com.dwarfeng.judge.stack.service.AlarmTypeIndicatorMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AlarmTypeIndicatorMaintainServiceImplTest {

    @Autowired
    private AlarmTypeIndicatorMaintainService alarmTypeIndicatorMaintainService;

    private List<AlarmTypeIndicator> alarmTypeIndicators;

    @Before
    public void setUp() {
        alarmTypeIndicators = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AlarmTypeIndicator alarmTypeIndicator = new AlarmTypeIndicator(
                    new StringIdKey("test.alarm.type.indicator." + i), "label", "remark"
            );
            alarmTypeIndicators.add(alarmTypeIndicator);
        }
    }

    @After
    public void tearDown() {
        alarmTypeIndicators.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AlarmTypeIndicator alarmTypeIndicator : alarmTypeIndicators) {
                alarmTypeIndicatorMaintainService.insertOrUpdate(alarmTypeIndicator);
                assertTrue(alarmTypeIndicatorMaintainService.exists(alarmTypeIndicator.getKey()));
                AlarmTypeIndicator testAlarmTypeIndicator = alarmTypeIndicatorMaintainService.get(
                        alarmTypeIndicator.getKey()
                );
                assertEquals(BeanUtils.describe(alarmTypeIndicator), BeanUtils.describe(testAlarmTypeIndicator));
                alarmTypeIndicator.setRemark("remark.updated");
                alarmTypeIndicatorMaintainService.update(alarmTypeIndicator);
                testAlarmTypeIndicator = alarmTypeIndicatorMaintainService.get(alarmTypeIndicator.getKey());
                assertEquals(BeanUtils.describe(alarmTypeIndicator), BeanUtils.describe(testAlarmTypeIndicator));
                alarmTypeIndicatorMaintainService.deleteIfExists(alarmTypeIndicator.getKey());
                assertFalse(alarmTypeIndicatorMaintainService.exists(alarmTypeIndicator.getKey()));
            }
        } finally {
            for (AlarmTypeIndicator alarmTypeIndicator : alarmTypeIndicators) {
                if (Objects.isNull(alarmTypeIndicator.getKey())) {
                    continue;
                }
                alarmTypeIndicatorMaintainService.deleteIfExists(alarmTypeIndicator.getKey());
            }
        }
    }
}
