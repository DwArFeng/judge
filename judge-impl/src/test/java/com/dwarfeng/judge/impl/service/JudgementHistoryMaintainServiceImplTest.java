package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.JudgementHistory;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.service.JudgementHistoryMaintainService;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class JudgementHistoryMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private JudgerInfoMaintainService judgerInfoMaintainService;
    @Autowired
    private JudgementHistoryMaintainService judgementHistoryMaintainService;

    private Section section;
    private JudgerInfo judgerInfo;
    private List<JudgementHistory> judgementHistories;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        judgerInfo = new JudgerInfo(null, null, 12450, true, "type", "param", "remark");
        judgementHistories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JudgementHistory judgementHistory = new JudgementHistory(
                    null, null, null, new Date(), 12.450, "message"
            );
            judgementHistories.add(judgementHistory);
        }
    }

    @After
    public void tearDown() {
        section = null;
        judgerInfo = null;
        judgementHistories.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            judgerInfo.setKey(judgerInfoMaintainService.insertOrUpdate(judgerInfo));
            for (JudgementHistory judgementHistory : judgementHistories) {
                judgementHistory.setSectionKey(section.getKey());
                judgementHistory.setJudgerInfoKey(judgerInfo.getKey());
                judgementHistory.setKey(judgementHistoryMaintainService.insertOrUpdate(judgementHistory));
                JudgementHistory testJudgementHistory = judgementHistoryMaintainService.get(judgementHistory.getKey());
                assertEquals(BeanUtils.describe(judgementHistory), BeanUtils.describe(testJudgementHistory));
                testJudgementHistory = judgementHistoryMaintainService.get(judgementHistory.getKey());
                assertEquals(BeanUtils.describe(judgementHistory), BeanUtils.describe(testJudgementHistory));
            }
        } finally {
            for (JudgementHistory judgementHistory : judgementHistories) {
                if (Objects.isNull(judgementHistory.getKey())) {
                    continue;
                }
                judgementHistoryMaintainService.deleteIfExists(judgementHistory.getKey());
            }
            if (Objects.nonNull(judgerInfo.getKey())) {
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
            judgerInfo.setKey(judgerInfoMaintainService.insertOrUpdate(judgerInfo));
            for (JudgementHistory judgementHistory : judgementHistories) {
                judgementHistory.setSectionKey(section.getKey());
                judgementHistory.setJudgerInfoKey(judgerInfo.getKey());
                judgementHistory.setKey(judgementHistoryMaintainService.insertOrUpdate(judgementHistory));
            }

            assertEquals(
                    judgementHistories.size(),
                    judgementHistoryMaintainService.lookupAsList(
                            JudgementHistoryMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    judgementHistoryMaintainService.lookupAsList(
                            JudgementHistoryMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            for (JudgementHistory judgementHistory : judgementHistories) {
                assertFalse(judgementHistoryMaintainService.exists(judgementHistory.getKey()));
            }
        } finally {
            for (JudgementHistory judgementHistory : judgementHistories) {
                if (Objects.isNull(judgementHistory.getKey())) {
                    continue;
                }
                judgementHistoryMaintainService.deleteIfExists(judgementHistory.getKey());
            }
            if (Objects.nonNull(judgerInfo.getKey())) {
                judgerInfoMaintainService.deleteIfExists(judgerInfo.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }

    @Test
    public void testForJudgerInfoCascade() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            judgerInfo.setKey(judgerInfoMaintainService.insertOrUpdate(judgerInfo));
            for (JudgementHistory judgementHistory : judgementHistories) {
                judgementHistory.setSectionKey(section.getKey());
                judgementHistory.setJudgerInfoKey(judgerInfo.getKey());
                judgementHistory.setKey(judgementHistoryMaintainService.insertOrUpdate(judgementHistory));
            }

            assertEquals(
                    judgementHistories.size(),
                    judgementHistoryMaintainService.lookupAsList(
                            JudgementHistoryMaintainService.CHILD_FOR_JUDGER_INFO, new Object[]{judgerInfo.getKey()}
                    ).size()
            );

            judgerInfoMaintainService.deleteIfExists(judgerInfo.getKey());

            assertEquals(
                    0,
                    judgementHistoryMaintainService.lookupAsList(
                            JudgementHistoryMaintainService.CHILD_FOR_JUDGER_INFO, new Object[]{judgerInfo.getKey()}
                    ).size()
            );

            for (JudgementHistory judgementHistory : judgementHistories) {
                assertTrue(judgementHistoryMaintainService.exists(judgementHistory.getKey()));
            }
        } finally {
            for (JudgementHistory judgementHistory : judgementHistories) {
                if (Objects.isNull(judgementHistory.getKey())) {
                    continue;
                }
                judgementHistoryMaintainService.deleteIfExists(judgementHistory.getKey());
            }
            if (Objects.nonNull(judgerInfo.getKey())) {
                judgerInfoMaintainService.deleteIfExists(judgerInfo.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
