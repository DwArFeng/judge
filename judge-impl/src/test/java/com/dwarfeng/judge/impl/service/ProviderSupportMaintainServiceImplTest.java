package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.ProviderSupport;
import com.dwarfeng.judge.stack.service.ProviderSupportMaintainService;
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
public class ProviderSupportMaintainServiceImplTest {

    @Autowired
    private ProviderSupportMaintainService providerSupportMaintainService;
    private List<ProviderSupport> providerSupports;

    @Before
    public void setUp() {
        providerSupports = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ProviderSupport providerSupport = new ProviderSupport(
                    new StringIdKey("test-key" + (i + 1)), "label", "description", "exampleParam"
            );
            providerSupports.add(providerSupport);
        }

    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (ProviderSupport providerSupport : providerSupports) {
                providerSupport.setKey(providerSupportMaintainService.insertOrUpdate(providerSupport));

                ProviderSupport testProviderSupport = providerSupportMaintainService.get(providerSupport.getKey());
                assertEquals(BeanUtils.describe(providerSupport), BeanUtils.describe(testProviderSupport));
            }
        } finally {
            for (ProviderSupport providerSupport : providerSupports) {
                if (Objects.isNull(providerSupport.getKey())) {
                    continue;
                }
                providerSupportMaintainService.deleteIfExists(providerSupport.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        providerSupports.clear();
    }
}
