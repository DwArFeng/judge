package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.service.JudgerInfoMaintainService;
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
public class JudgerInfoMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private JudgerInfoMaintainService judgerInfoMaintainService;

    private Section section;
    private List<JudgerInfo> judgerInfos;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        judgerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JudgerInfo judgerInfo = new JudgerInfo(null, null, 12450, true, "type", "param", "remark");
            judgerInfos.add(judgerInfo);
        }
    }

    @After
    public void tearDown() {
        section = null;
        judgerInfos.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (JudgerInfo judgerInfo : judgerInfos) {
                judgerInfo.setSectionKey(section.getKey());
                judgerInfo.setKey(judgerInfoMaintainService.insertOrUpdate(judgerInfo));
                JudgerInfo testJudgerInfo = judgerInfoMaintainService.get(judgerInfo.getKey());
                assertEquals(BeanUtils.describe(judgerInfo), BeanUtils.describe(testJudgerInfo));
                testJudgerInfo = judgerInfoMaintainService.get(judgerInfo.getKey());
                assertEquals(BeanUtils.describe(judgerInfo), BeanUtils.describe(testJudgerInfo));
            }
        } finally {
            for (JudgerInfo judgerInfo : judgerInfos) {
                if (Objects.isNull(judgerInfo.getKey())) {
                    continue;
                }
                judgerInfoMaintainService.deleteIfExists(judgerInfo.getKey());
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
            for (JudgerInfo judgerInfo : judgerInfos) {
                judgerInfo.setSectionKey(section.getKey());
                judgerInfo.setKey(judgerInfoMaintainService.insertOrUpdate(judgerInfo));
            }

            assertEquals(
                    judgerInfos.size(),
                    judgerInfoMaintainService.lookupAsList(
                            JudgerInfoMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    judgerInfoMaintainService.lookupAsList(
                            JudgerInfoMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            for (JudgerInfo judgerInfo : judgerInfos) {
                assertFalse(judgerInfoMaintainService.exists(judgerInfo.getKey()));
            }
        } finally {
            for (JudgerInfo judgerInfo : judgerInfos) {
                if (Objects.isNull(judgerInfo.getKey())) {
                    continue;
                }
                judgerInfoMaintainService.deleteIfExists(judgerInfo.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
