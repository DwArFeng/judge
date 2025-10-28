package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.FilterSupport;
import com.dwarfeng.judge.stack.service.FilterSupportMaintainService;
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
public class FilterSupportMaintainServiceImplTest {

    @Autowired
    private FilterSupportMaintainService filterSupportMaintainService;
    private List<FilterSupport> filterSupports;

    @Before
    public void setUp() {
        filterSupports = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FilterSupport filterSupport = new FilterSupport(
                    new StringIdKey("test-key" + (i + 1)), "label", "description", "exampleParam"
            );
            filterSupports.add(filterSupport);
        }

    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (FilterSupport filterSupport : filterSupports) {
                filterSupport.setKey(filterSupportMaintainService.insertOrUpdate(filterSupport));

                FilterSupport testFilterSupport = filterSupportMaintainService.get(filterSupport.getKey());
                assertEquals(BeanUtils.describe(filterSupport), BeanUtils.describe(testFilterSupport));
            }
        } finally {
            for (FilterSupport filterSupport : filterSupports) {
                if (Objects.isNull(filterSupport.getKey())) {
                    continue;
                }
                filterSupportMaintainService.deleteIfExists(filterSupport.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        filterSupports.clear();
    }
}
