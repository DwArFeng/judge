package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePack;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePackItemInfo;
import com.dwarfeng.judge.stack.service.AnalysisPicturePackItemInfoMaintainService;
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
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AnalysisPicturePackItemInfoMaintainServiceImplTest {

    @Autowired
    private AnalysisPicturePackMaintainService analysisPicturePackMaintainService;
    @Autowired
    private AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService;

    private AnalysisPicturePack analysisPicturePack;
    private List<AnalysisPicturePackItemInfo> analysisPicturePackItemInfos;

    @Before
    public void setUp() {
        analysisPicturePack = new AnalysisPicturePack(null, 5, "remark");
        analysisPicturePackItemInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AnalysisPicturePackItemInfo item = new AnalysisPicturePackItemInfo(null, null, i,
                    "originName", Long.valueOf("10"), "remark");
            analysisPicturePackItemInfos.add(item);
        }
    }

    @After
    public void tearDown() {
        analysisPicturePack = null;
        analysisPicturePackItemInfos.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            analysisPicturePack.setKey(analysisPicturePackMaintainService.insertOrUpdate(analysisPicturePack));
            for (AnalysisPicturePackItemInfo item : analysisPicturePackItemInfos) {
                item.setPackKey(analysisPicturePack.getKey());
                item.setKey(analysisPicturePackItemInfoMaintainService.insertOrUpdate(item));
                AnalysisPicturePackItemInfo testAnalysisPicturePackItemInfo =
                        analysisPicturePackItemInfoMaintainService.get(item.getKey());
                assertEquals(BeanUtils.describe(item), BeanUtils.describe(testAnalysisPicturePackItemInfo));
            }
        } finally {
            for (AnalysisPicturePackItemInfo item : analysisPicturePackItemInfos) {
                if (Objects.isNull(item.getKey())) {
                    continue;
                }
                analysisPicturePackItemInfoMaintainService.deleteIfExists(item.getKey());
            }
            if (Objects.nonNull(analysisPicturePack.getKey())) {
                analysisPicturePackMaintainService.deleteIfExists(analysisPicturePack.getKey());
            }
        }
    }

    @Test
    public void testForAnalysisPicturePackCascade() throws Exception {
        try {
            analysisPicturePack.setKey(analysisPicturePackMaintainService.insertOrUpdate(analysisPicturePack));
            for (AnalysisPicturePackItemInfo item : analysisPicturePackItemInfos) {
                item.setPackKey(analysisPicturePack.getKey());
                item.setKey(analysisPicturePackItemInfoMaintainService.insertOrUpdate(item));
            }

            assertEquals(
                    analysisPicturePackItemInfos.size(),
                    analysisPicturePackItemInfoMaintainService.lookupAsList(
                            AnalysisPicturePackItemInfoMaintainService.CHILD_FOR_PACK,
                            new Object[]{analysisPicturePack.getKey()}
                    ).size()
            );

            analysisPicturePackMaintainService.deleteIfExists(analysisPicturePack.getKey());

            assertEquals(
                    0,
                    analysisPicturePackItemInfoMaintainService.lookupAsList(
                            AnalysisPicturePackItemInfoMaintainService.CHILD_FOR_PACK,
                            new Object[]{analysisPicturePack.getKey()}
                    ).size()
            );

            for (AnalysisPicturePackItemInfo item : analysisPicturePackItemInfos) {
                assertFalse(analysisPicturePackItemInfoMaintainService.exists(item.getKey()));
            }
        } finally {
            for (AnalysisPicturePackItemInfo item : analysisPicturePackItemInfos) {
                if (Objects.isNull(item.getKey())) {
                    continue;
                }
                analysisPicturePackItemInfoMaintainService.deleteIfExists(item.getKey());
            }
            if (Objects.nonNull(analysisPicturePack.getKey())) {
                analysisPicturePackMaintainService.deleteIfExists(analysisPicturePack.getKey());
            }
        }
    }
}
