package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AdapterInfo;
import com.dwarfeng.judge.stack.service.AdapterInfoMaintainService;
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
public class AdapterInfoMaintainServiceImplTest {

    @Autowired
    private AdapterInfoMaintainService adapterInfoMaintainService;
    private List<AdapterInfo> adapterInfos;

    @Before
    public void setUp() {
        adapterInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AdapterInfo adapterInfo = new AdapterInfo(null, true, "type", "param", "remark");
            adapterInfos.add(adapterInfo);
        }
    }

    @Test
    public void testForCrud() throws Exception {
        try {

            for (AdapterInfo adapterInfo : adapterInfos) {
                adapterInfo.setKey(adapterInfoMaintainService.insertOrUpdate(adapterInfo));

                AdapterInfo testAdapterInfo = adapterInfoMaintainService.get(adapterInfo.getKey());
                assertEquals(BeanUtils.describe(adapterInfo), BeanUtils.describe(testAdapterInfo));
            }
        } finally {
            for (AdapterInfo adapterInfo : adapterInfos) {
                if (Objects.isNull(adapterInfo.getKey())) {
                    continue;
                }
                adapterInfoMaintainService.deleteIfExists(adapterInfo.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        adapterInfos.clear();
    }
}
