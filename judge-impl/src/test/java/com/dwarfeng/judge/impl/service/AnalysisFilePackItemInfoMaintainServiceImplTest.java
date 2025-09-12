package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePack;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePackItemInfo;
import com.dwarfeng.judge.stack.service.AnalysisFilePackItemInfoMaintainService;
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
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AnalysisFilePackItemInfoMaintainServiceImplTest {

    @Autowired
    private AnalysisFilePackMaintainService analysisFilePackMaintainService;
    @Autowired
    private AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService;

    private AnalysisFilePack analysisFilePack;
    private List<AnalysisFilePackItemInfo> analysisFilePackItemInfos;

    @Before
    public void setUp() {
        analysisFilePack = new AnalysisFilePack(null, 5, "remark");
        analysisFilePackItemInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AnalysisFilePackItemInfo item = new AnalysisFilePackItemInfo(null, null, i,
                    "originName", Long.valueOf("10"), "remark");
            analysisFilePackItemInfos.add(item);
        }
    }

    @After
    public void tearDown() {
        analysisFilePack = null;
        analysisFilePackItemInfos.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            analysisFilePack.setKey(analysisFilePackMaintainService.insertOrUpdate(analysisFilePack));
            for (AnalysisFilePackItemInfo item : analysisFilePackItemInfos) {
                item.setPackKey(analysisFilePack.getKey());
                item.setKey(analysisFilePackItemInfoMaintainService.insertOrUpdate(item));
                AnalysisFilePackItemInfo testAnalysisFilePackItemInfo =
                        analysisFilePackItemInfoMaintainService.get(item.getKey());
                assertEquals(BeanUtils.describe(item), BeanUtils.describe(testAnalysisFilePackItemInfo));
            }
        } finally {
            for (AnalysisFilePackItemInfo item : analysisFilePackItemInfos) {
                if (Objects.isNull(item.getKey())) {
                    continue;
                }
                analysisFilePackItemInfoMaintainService.deleteIfExists(item.getKey());
            }
            if (Objects.nonNull(analysisFilePack.getKey())) {
                analysisFilePackMaintainService.deleteIfExists(analysisFilePack.getKey());
            }
        }
    }

    @Test
    public void testForAnalysisFilePackCascade() throws Exception {
        try {
            analysisFilePack.setKey(analysisFilePackMaintainService.insertOrUpdate(analysisFilePack));
            for (AnalysisFilePackItemInfo item : analysisFilePackItemInfos) {
                item.setPackKey(analysisFilePack.getKey());
                item.setKey(analysisFilePackItemInfoMaintainService.insertOrUpdate(item));
            }

            assertEquals(
                    analysisFilePackItemInfos.size(),
                    analysisFilePackItemInfoMaintainService.lookupAsList(
                            AnalysisFilePackItemInfoMaintainService.CHILD_FOR_PACK,
                            new Object[]{analysisFilePack.getKey()}
                    ).size()
            );

            analysisFilePackMaintainService.deleteIfExists(analysisFilePack.getKey());

            assertEquals(
                    0,
                    analysisFilePackItemInfoMaintainService.lookupAsList(
                            AnalysisFilePackItemInfoMaintainService.CHILD_FOR_PACK,
                            new Object[]{analysisFilePack.getKey()}
                    ).size()
            );

            for (AnalysisFilePackItemInfo item : analysisFilePackItemInfos) {
                assertFalse(analysisFilePackItemInfoMaintainService.exists(item.getKey()));
            }
        } finally {
            for (AnalysisFilePackItemInfo item : analysisFilePackItemInfos) {
                if (Objects.isNull(item.getKey())) {
                    continue;
                }
                analysisFilePackItemInfoMaintainService.deleteIfExists(item.getKey());
            }
            if (Objects.nonNull(analysisFilePack.getKey())) {
                analysisFilePackMaintainService.deleteIfExists(analysisFilePack.getKey());
            }
        }
    }
}
