package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.FilterInfo;
import com.dwarfeng.judge.stack.service.FilterInfoMaintainService;
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
public class FilterInfoMaintainServiceImplTest {

    @Autowired
    private FilterInfoMaintainService filterInfoMaintainService;
    private List<FilterInfo> filterInfos;

    @Before
    public void setUp() {
        filterInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FilterInfo filterInfo = new FilterInfo(null, true, "type", "param", "remark");
            filterInfos.add(filterInfo);
        }
    }

    @Test
    public void testForCrud() throws Exception {
        try {

            for (FilterInfo filterInfo : filterInfos) {
                filterInfo.setKey(filterInfoMaintainService.insertOrUpdate(filterInfo));

                FilterInfo testFilterInfo = filterInfoMaintainService.get(filterInfo.getKey());
                assertEquals(BeanUtils.describe(filterInfo), BeanUtils.describe(testFilterInfo));
            }
        } finally {
            for (FilterInfo filterInfo : filterInfos) {
                if (Objects.isNull(filterInfo.getKey())) {
                    continue;
                }
                filterInfoMaintainService.deleteIfExists(filterInfo.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        filterInfos.clear();
    }
}
