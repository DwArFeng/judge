package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
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
public class SinkerInfoMaintainServiceImplTest {

    @Autowired
    private SinkerInfoMaintainService sinkerInfoMaintainService;
    private List<SinkerInfo> sinkerInfos;

    @Before
    public void setUp() {
        sinkerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SinkerInfo sinkerInfo = new SinkerInfo(
                    null, true, "12450", "12450", "12450"
            );
            sinkerInfos.add(sinkerInfo);
        }
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (SinkerInfo sinkerInfo : sinkerInfos) {
                sinkerInfo.setKey(sinkerInfoMaintainService.insertOrUpdate(sinkerInfo));
                SinkerInfo testSinkerInfo = sinkerInfoMaintainService.get(sinkerInfo.getKey());
                assertEquals(BeanUtils.describe(sinkerInfo), BeanUtils.describe(testSinkerInfo));
            }
        } finally {
            for (SinkerInfo sinkerInfo : sinkerInfos) {
                if (Objects.isNull(sinkerInfo.getKey())) {
                    continue;
                }
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        sinkerInfos.clear();
    }
}
