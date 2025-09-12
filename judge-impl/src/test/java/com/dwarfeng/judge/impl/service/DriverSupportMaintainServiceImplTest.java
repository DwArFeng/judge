package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.DriverSupport;
import com.dwarfeng.judge.stack.service.DriverSupportMaintainService;
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
public class DriverSupportMaintainServiceImplTest {

    @Autowired
    private DriverSupportMaintainService service;

    private final List<DriverSupport> driverSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            DriverSupport driverSupport = new DriverSupport(
                    new StringIdKey("driver-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            driverSupports.add(driverSupport);
        }
    }

    @After
    public void tearDown() {
        driverSupports.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (DriverSupport driverSupport : driverSupports) {
                driverSupport.setKey(service.insertOrUpdate(driverSupport));
                DriverSupport testDriverSupport = service.get(driverSupport.getKey());
                assertEquals(BeanUtils.describe(driverSupport), BeanUtils.describe(testDriverSupport));
                testDriverSupport = service.get(driverSupport.getKey());
                assertEquals(BeanUtils.describe(driverSupport), BeanUtils.describe(testDriverSupport));
            }
        } finally {
            for (DriverSupport driverSupport : driverSupports) {
                if (Objects.isNull(driverSupport.getKey())) {
                    continue;
                }
                service.deleteIfExists(driverSupport.getKey());
            }
        }
    }
}
