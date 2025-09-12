package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AnalysisFileInfo;
import com.dwarfeng.judge.stack.service.AnalysisFileInfoMaintainService;
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
public class AnalysisFileInfoMaintainServiceImplTest {

    @Autowired
    private AnalysisFileInfoMaintainService service;

    private final List<AnalysisFileInfo> analysisFileInfos = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            AnalysisFileInfo analysisFileInfo = new AnalysisFileInfo(
                    null, "originName" + i, Long.valueOf("10"), "exampleParam" + i
            );
            analysisFileInfos.add(analysisFileInfo);
        }
    }

    @After
    public void tearDown() {
        analysisFileInfos.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AnalysisFileInfo analysisFileInfo : analysisFileInfos) {
                analysisFileInfo.setKey(service.insert(analysisFileInfo));
                service.update(analysisFileInfo);
                AnalysisFileInfo testAnalysisFileInfo = service.get(analysisFileInfo.getKey());
                assertEquals(BeanUtils.describe(analysisFileInfo), BeanUtils.describe(testAnalysisFileInfo));
            }
        } finally {
            for (AnalysisFileInfo analysisFileInfo : analysisFileInfos) {
                if (Objects.isNull(analysisFileInfo.getKey())) {
                    continue;
                }
                service.deleteIfExists(analysisFileInfo.getKey());
            }
        }
    }
}
