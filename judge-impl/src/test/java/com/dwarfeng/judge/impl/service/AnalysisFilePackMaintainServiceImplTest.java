package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePack;
import com.dwarfeng.judge.stack.service.AnalysisFilePackMaintainService;
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
public class AnalysisFilePackMaintainServiceImplTest {

    @Autowired
    private AnalysisFilePackMaintainService analysisFilePackMaintainService;

    private List<AnalysisFilePack> analysisFilePacks;

    @Before
    public void setUp() {
        analysisFilePacks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AnalysisFilePack analysisFilePack = new AnalysisFilePack(null, 12450, "remark");
            analysisFilePacks.add(analysisFilePack);
        }
    }

    @After
    public void tearDown() {
        analysisFilePacks.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AnalysisFilePack analysisFilePack : analysisFilePacks) {
                analysisFilePack.setKey(analysisFilePackMaintainService.insertOrUpdate(analysisFilePack));
                AnalysisFilePack testAnalysisFilePack = analysisFilePackMaintainService.get(analysisFilePack.getKey());
                assertEquals(BeanUtils.describe(analysisFilePack), BeanUtils.describe(testAnalysisFilePack));
            }
        } finally {
            for (AnalysisFilePack analysisFilePack : analysisFilePacks) {
                if (Objects.isNull(analysisFilePack.getKey())) {
                    continue;
                }
                analysisFilePackMaintainService.deleteIfExists(analysisFilePack.getKey());
            }
        }
    }
}
