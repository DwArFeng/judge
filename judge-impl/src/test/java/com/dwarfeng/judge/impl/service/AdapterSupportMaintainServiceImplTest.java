package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AdapterSupport;
import com.dwarfeng.judge.stack.service.AdapterSupportMaintainService;
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
public class AdapterSupportMaintainServiceImplTest {

    @Autowired
    private AdapterSupportMaintainService adapterSupportMaintainService;
    private List<AdapterSupport> adapterSupports;

    @Before
    public void setUp() {
        adapterSupports = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AdapterSupport adapterSupport = new AdapterSupport(
                    new StringIdKey("test-key" + (i + 1)), "label", "description", "exampleParam"
            );
            adapterSupports.add(adapterSupport);
        }

    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AdapterSupport adapterSupport : adapterSupports) {
                adapterSupport.setKey(adapterSupportMaintainService.insertOrUpdate(adapterSupport));

                AdapterSupport testAdapterSupport = adapterSupportMaintainService.get(adapterSupport.getKey());
                assertEquals(BeanUtils.describe(adapterSupport), BeanUtils.describe(testAdapterSupport));
            }
        } finally {
            for (AdapterSupport adapterSupport : adapterSupports) {
                if (Objects.isNull(adapterSupport.getKey())) {
                    continue;
                }
                adapterSupportMaintainService.deleteIfExists(adapterSupport.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        adapterSupports.clear();
    }
}
