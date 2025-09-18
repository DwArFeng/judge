package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerSupport;
import com.dwarfeng.judge.stack.service.SinkerSupportMaintainService;
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
public class SinkerSupportMaintainServiceImplTest {

    @Autowired
    private SinkerSupportMaintainService sinkerSupportMaintainService;
    private List<SinkerSupport> sinkerSupports;

    @Before
    public void setUp() {
        sinkerSupports = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SinkerSupport sinkerSupport = new SinkerSupport(
                    new StringIdKey("sinker-support-" + (i + 1)), "12450", "12450", "12450"
            );
            sinkerSupports.add(sinkerSupport);
        }
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (SinkerSupport sinkerSupport : sinkerSupports) {
                sinkerSupport.setKey(sinkerSupportMaintainService.insertOrUpdate(sinkerSupport));
                SinkerSupport testSinkerSupport = sinkerSupportMaintainService.get(sinkerSupport.getKey());
                assertEquals(BeanUtils.describe(sinkerSupport), BeanUtils.describe(testSinkerSupport));
            }
        } finally {
            for (SinkerSupport sinkerSupport : sinkerSupports) {
                if (Objects.isNull(sinkerSupport.getKey())) {
                    continue;
                }
                sinkerSupportMaintainService.deleteIfExists(sinkerSupport.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        sinkerSupports.clear();
    }
}
