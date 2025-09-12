package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AnalyserInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.service.AnalyserInfoMaintainService;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
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
public class AnalyserInfoMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private AnalyserInfoMaintainService analyserInfoMaintainService;

    private Section section;
    private List<AnalyserInfo> analyserInfos;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        analyserInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AnalyserInfo analyserInfo = new AnalyserInfo(null, null, 12450, true, "type", "param", "remark");
            analyserInfos.add(analyserInfo);
        }
    }

    @After
    public void tearDown() {
        section = null;
        analyserInfos.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (AnalyserInfo analyserInfo : analyserInfos) {
                analyserInfo.setSectionKey(section.getKey());
                analyserInfo.setKey(analyserInfoMaintainService.insertOrUpdate(analyserInfo));
                AnalyserInfo testAnalyserInfo = analyserInfoMaintainService.get(analyserInfo.getKey());
                assertEquals(BeanUtils.describe(analyserInfo), BeanUtils.describe(testAnalyserInfo));
                testAnalyserInfo = analyserInfoMaintainService.get(analyserInfo.getKey());
                assertEquals(BeanUtils.describe(analyserInfo), BeanUtils.describe(testAnalyserInfo));
            }
        } finally {
            for (AnalyserInfo analyserInfo : analyserInfos) {
                if (Objects.isNull(analyserInfo.getKey())) {
                    continue;
                }
                analyserInfoMaintainService.deleteIfExists(analyserInfo.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }

    @Test
    public void testForSectionCascade() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (AnalyserInfo analyserInfo : analyserInfos) {
                analyserInfo.setSectionKey(section.getKey());
                analyserInfo.setKey(analyserInfoMaintainService.insertOrUpdate(analyserInfo));
            }

            assertEquals(
                    analyserInfos.size(),
                    analyserInfoMaintainService.lookupAsList(
                            AnalyserInfoMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    analyserInfoMaintainService.lookupAsList(
                            AnalyserInfoMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            for (AnalyserInfo analyserInfo : analyserInfos) {
                assertFalse(analyserInfoMaintainService.exists(analyserInfo.getKey()));
            }
        } finally {
            for (AnalyserInfo analyserInfo : analyserInfos) {
                if (Objects.isNull(analyserInfo.getKey())) {
                    continue;
                }
                analyserInfoMaintainService.deleteIfExists(analyserInfo.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
