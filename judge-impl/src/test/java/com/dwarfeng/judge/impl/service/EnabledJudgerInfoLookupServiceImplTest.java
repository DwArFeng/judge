package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.cache.EnabledJudgerInfoCache;
import com.dwarfeng.judge.stack.service.EnabledJudgerInfoLookupService;
import com.dwarfeng.judge.stack.service.JudgerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class EnabledJudgerInfoLookupServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private JudgerInfoMaintainService judgerInfoMaintainService;
    @Autowired
    private EnabledJudgerInfoLookupService enabledJudgerInfoLookupService;
    @Autowired
    private EnabledJudgerInfoCache enabledJudgerInfoCache;

    private Section parentSection;
    private List<JudgerInfo> judgerInfos;

    @Before
    public void setUp() {
        parentSection = new Section(
                null,
                "parent-section",
                true,
                0,
                1,
                "test-section"
        );
        judgerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JudgerInfo judgerInfo = new JudgerInfo(
                    null,
                    parentSection.getKey(),
                    true,
                    "filter-info-enabled-" + i,
                    "this is a test",
                    "test"
            );
            judgerInfos.add(judgerInfo);
        }
        for (int i = 0; i < 5; i++) {
            JudgerInfo judgerInfo = new JudgerInfo(
                    null,
                    parentSection.getKey(),
                    false,
                    "filter-info-disabled-" + i,
                    "this is a test",
                    "test"
            );
            judgerInfos.add(judgerInfo);
        }
    }

    @After
    public void tearDown() {
        parentSection = null;
        judgerInfos.clear();
    }

    @Test
    public void test() throws ServiceException, CacheException {
        try {
            parentSection.setKey(sectionMaintainService.insertOrUpdate(parentSection));
            for (JudgerInfo judgerInfo : judgerInfos) {
                judgerInfo.setKey(judgerInfoMaintainService.insertOrUpdate(judgerInfo));
                judgerInfo.setSectionKey(parentSection.getKey());
                judgerInfoMaintainService.update(judgerInfo);
            }
            assertEquals(5, judgerInfoMaintainService.lookup(JudgerInfoMaintainService.ENABLED_CHILD_FOR_SECTION, new Object[]{parentSection.getKey()}).getCount());
            assertEquals(5, enabledJudgerInfoLookupService.getEnabledJudgerInfos(parentSection.getKey()).size());
            assertEquals(5, enabledJudgerInfoCache.get(parentSection.getKey()).size());
            JudgerInfo judgerInfo = judgerInfos.get(0);
            judgerInfoMaintainService.deleteIfExists(judgerInfo.getKey());
            assertEquals(0, enabledJudgerInfoCache.get(parentSection.getKey()).size());
            judgerInfoMaintainService.insertOrUpdate(judgerInfo);
            assertEquals(0, enabledJudgerInfoCache.get(parentSection.getKey()).size());
            assertEquals(5, judgerInfoMaintainService.lookup(JudgerInfoMaintainService.ENABLED_CHILD_FOR_SECTION, new Object[]{parentSection.getKey()}).getCount());
            assertEquals(5, enabledJudgerInfoLookupService.getEnabledJudgerInfos(parentSection.getKey()).size());
            assertEquals(5, enabledJudgerInfoCache.get(parentSection.getKey()).size());
        } finally {
            for (JudgerInfo judgerInfo : judgerInfos) {
                judgerInfoMaintainService.deleteIfExists(judgerInfo.getKey());
            }
            sectionMaintainService.deleteIfExists(parentSection.getKey());
        }
    }
}
