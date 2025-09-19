package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.judge.stack.service.VisualizerInfoMaintainService;
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
public class VisualizerInfoMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private VisualizerInfoMaintainService visualizerInfoMaintainService;

    private Section section;
    private List<VisualizerInfo> visualizerInfos;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        visualizerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VisualizerInfo visualizerInfo = new VisualizerInfo(null, null, 12450, true, "type", "param", "remark");
            visualizerInfos.add(visualizerInfo);
        }
    }

    @After
    public void tearDown() {
        section = null;
        visualizerInfos.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (VisualizerInfo visualizerInfo : visualizerInfos) {
                visualizerInfo.setSectionKey(section.getKey());
                visualizerInfo.setKey(visualizerInfoMaintainService.insertOrUpdate(visualizerInfo));
                VisualizerInfo testVisualizerInfo = visualizerInfoMaintainService.get(visualizerInfo.getKey());
                assertEquals(BeanUtils.describe(visualizerInfo), BeanUtils.describe(testVisualizerInfo));
                testVisualizerInfo = visualizerInfoMaintainService.get(visualizerInfo.getKey());
                assertEquals(BeanUtils.describe(visualizerInfo), BeanUtils.describe(testVisualizerInfo));
            }
        } finally {
            for (VisualizerInfo visualizerInfo : visualizerInfos) {
                if (Objects.isNull(visualizerInfo.getKey())) {
                    continue;
                }
                visualizerInfoMaintainService.deleteIfExists(visualizerInfo.getKey());
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
            for (VisualizerInfo visualizerInfo : visualizerInfos) {
                visualizerInfo.setSectionKey(section.getKey());
                visualizerInfo.setKey(visualizerInfoMaintainService.insertOrUpdate(visualizerInfo));
            }

            assertEquals(
                    visualizerInfos.size(),
                    visualizerInfoMaintainService.lookupAsList(
                            VisualizerInfoMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    visualizerInfoMaintainService.lookupAsList(
                            VisualizerInfoMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            for (VisualizerInfo visualizerInfo : visualizerInfos) {
                assertFalse(visualizerInfoMaintainService.exists(visualizerInfo.getKey()));
            }
        } finally {
            for (VisualizerInfo visualizerInfo : visualizerInfos) {
                if (Objects.isNull(visualizerInfo.getKey())) {
                    continue;
                }
                visualizerInfoMaintainService.deleteIfExists(visualizerInfo.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
