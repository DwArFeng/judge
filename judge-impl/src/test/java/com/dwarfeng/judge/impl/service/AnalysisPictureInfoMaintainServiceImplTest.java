package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AnalysisPictureInfo;
import com.dwarfeng.judge.stack.service.AnalysisPictureInfoMaintainService;
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
public class AnalysisPictureInfoMaintainServiceImplTest {

    @Autowired
    private AnalysisPictureInfoMaintainService service;

    private final List<AnalysisPictureInfo> analysisPictureInfos = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            AnalysisPictureInfo analysisPictureInfo = new AnalysisPictureInfo(
                    null, "originName" + i, Long.valueOf("10"), "exampleParam" + i
            );
            analysisPictureInfos.add(analysisPictureInfo);
        }
    }

    @After
    public void tearDown() {
        analysisPictureInfos.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AnalysisPictureInfo analysisPictureInfo : analysisPictureInfos) {
                analysisPictureInfo.setKey(service.insert(analysisPictureInfo));
                service.update(analysisPictureInfo);
                AnalysisPictureInfo testAnalysisPictureInfo = service.get(analysisPictureInfo.getKey());
                assertEquals(BeanUtils.describe(analysisPictureInfo), BeanUtils.describe(testAnalysisPictureInfo));
            }
        } finally {
            for (AnalysisPictureInfo analysisPictureInfo : analysisPictureInfos) {
                if (Objects.isNull(analysisPictureInfo.getKey())) {
                    continue;
                }
                service.deleteIfExists(analysisPictureInfo.getKey());
            }
        }
    }
}
