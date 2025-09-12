package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmLevelIndicator;
import com.dwarfeng.judge.stack.service.AlarmLevelIndicatorMaintainService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AlarmLevelIndicatorMaintainServiceImplTest {

    @Autowired
    private AlarmLevelIndicatorMaintainService alarmLevelIndicatorMaintainService;

    private List<AlarmLevelIndicator> alarmLevelIndicators;

    @Before
    public void setUp() {
        alarmLevelIndicators = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AlarmLevelIndicator alarmLevelIndicator = new AlarmLevelIndicator(
                    new StringIdKey("alarm_level_indicator_test" + i), "label", "remark"
            );
            alarmLevelIndicators.add(alarmLevelIndicator);
        }
    }

    @After
    public void tearDown() {
        alarmLevelIndicators.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AlarmLevelIndicator alarmLevelIndicator : alarmLevelIndicators) {
                alarmLevelIndicator.setKey(alarmLevelIndicatorMaintainService.insert(alarmLevelIndicator));

                AlarmLevelIndicator testAlarmLevelIndicator = alarmLevelIndicatorMaintainService.get(
                        alarmLevelIndicator.getKey());
                assertEquals(BeanUtils.describe(alarmLevelIndicator), BeanUtils.describe(testAlarmLevelIndicator));
                alarmLevelIndicatorMaintainService.update(alarmLevelIndicator);
                testAlarmLevelIndicator = alarmLevelIndicatorMaintainService.get(alarmLevelIndicator.getKey());
                assertEquals(BeanUtils.describe(alarmLevelIndicator), BeanUtils.describe(testAlarmLevelIndicator));
            }
        } finally {
            for (AlarmLevelIndicator alarmLevelIndicator : alarmLevelIndicators) {
                if (Objects.isNull(alarmLevelIndicator.getKey())) {
                    continue;
                }
                alarmLevelIndicatorMaintainService.deleteIfExists(alarmLevelIndicator.getKey());
            }
        }
    }
}
