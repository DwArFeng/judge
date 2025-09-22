package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.judge.stack.service.ProviderInfoMaintainService;
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
public class ProviderInfoMaintainServiceImplTest {

    @Autowired
    private ProviderInfoMaintainService providerInfoMaintainService;
    private List<ProviderInfo> providerInfos;

    @Before
    public void setUp() {
        providerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ProviderInfo providerInfo = new ProviderInfo(null, true, "type", "param", "remark");
            providerInfos.add(providerInfo);
        }
    }

    @Test
    public void testForCrud() throws Exception {
        try {

            for (ProviderInfo providerInfo : providerInfos) {
                providerInfo.setKey(providerInfoMaintainService.insertOrUpdate(providerInfo));

                ProviderInfo testProviderInfo = providerInfoMaintainService.get(providerInfo.getKey());
                assertEquals(BeanUtils.describe(providerInfo), BeanUtils.describe(testProviderInfo));
            }
        } finally {
            for (ProviderInfo providerInfo : providerInfos) {
                if (Objects.isNull(providerInfo.getKey())) {
                    continue;
                }
                providerInfoMaintainService.deleteIfExists(providerInfo.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        providerInfos.clear();
    }
}
