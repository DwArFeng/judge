package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerMetaIndicator;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaIndicatorKey;
import com.dwarfeng.judge.stack.service.SinkerMetaIndicatorMaintainService;
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
public class SinkerMetaIndicatorMaintainServiceImplTest {

    private static final String SINKER_TYPE_ID = "test_sinker";

    @Autowired
    private SinkerMetaIndicatorMaintainService sinkerMetaIndicatorMaintainService;

    private List<SinkerMetaIndicator> sinkerMetaIndicators;

    @Before
    public void setUp() {
        sinkerMetaIndicators = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SinkerMetaIndicator sinkerMetaIndicator = new SinkerMetaIndicator(
                    new SinkerMetaIndicatorKey(SINKER_TYPE_ID, "test.sinker_meta_indicator." + (i + 1)),
                    "12450", "12450", "12450"
            );
            sinkerMetaIndicators.add(sinkerMetaIndicator);
        }
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (SinkerMetaIndicator sinkerMetaIndicator : sinkerMetaIndicators) {
                sinkerMetaIndicatorMaintainService.insertOrUpdate(sinkerMetaIndicator);
                SinkerMetaIndicator testSinkerMetaIndicator = sinkerMetaIndicatorMaintainService.get(
                        sinkerMetaIndicator.getKey()
                );
                assertEquals(BeanUtils.describe(sinkerMetaIndicator), BeanUtils.describe(testSinkerMetaIndicator));
                testSinkerMetaIndicator = sinkerMetaIndicatorMaintainService.get(sinkerMetaIndicator.getKey());
                assertEquals(BeanUtils.describe(sinkerMetaIndicator), BeanUtils.describe(testSinkerMetaIndicator));
            }
        } finally {
            for (SinkerMetaIndicator sinkerMetaIndicator : sinkerMetaIndicators) {
                if (Objects.isNull(sinkerMetaIndicator.getKey())) {
                    continue;
                }
                sinkerMetaIndicatorMaintainService.deleteIfExists(sinkerMetaIndicator.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        sinkerMetaIndicators.clear();
    }
}
