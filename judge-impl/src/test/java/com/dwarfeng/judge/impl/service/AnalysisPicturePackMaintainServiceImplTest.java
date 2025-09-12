package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePack;
import com.dwarfeng.judge.stack.service.AnalysisPicturePackMaintainService;
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
public class AnalysisPicturePackMaintainServiceImplTest {

    @Autowired
    private AnalysisPicturePackMaintainService analysisPicturePackMaintainService;

    private List<AnalysisPicturePack> analysisPicturePacks;

    @Before
    public void setUp() {
        analysisPicturePacks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AnalysisPicturePack analysisPicturePack = new AnalysisPicturePack(null, 12450, "remark");
            analysisPicturePacks.add(analysisPicturePack);
        }
    }

    @After
    public void tearDown() {
        analysisPicturePacks.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AnalysisPicturePack analysisPicturePack : analysisPicturePacks) {
                analysisPicturePack.setKey(analysisPicturePackMaintainService.insertOrUpdate(analysisPicturePack));
                AnalysisPicturePack testAnalysisPicturePack =
                        analysisPicturePackMaintainService.get(analysisPicturePack.getKey());
                assertEquals(BeanUtils.describe(analysisPicturePack), BeanUtils.describe(testAnalysisPicturePack));
            }
        } finally {
            for (AnalysisPicturePack analysisPicturePack : analysisPicturePacks) {
                if (Objects.isNull(analysisPicturePack.getKey())) {
                    continue;
                }
                analysisPicturePackMaintainService.deleteIfExists(analysisPicturePack.getKey());
            }
        }
    }
}
