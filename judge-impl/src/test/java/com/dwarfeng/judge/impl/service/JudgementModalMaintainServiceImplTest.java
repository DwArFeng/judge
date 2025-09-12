package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.JudgementModal;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.service.JudgementModalMaintainService;
import com.dwarfeng.judge.stack.service.JudgerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class JudgementModalMaintainServiceImplTest {

    private static final Long SECTION_LONG_ID = 12450L;
    private static final Long JUDGER_INFO_LONG_ID = 12450L;

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private JudgerInfoMaintainService judgerInfoMaintainService;
    @Autowired
    private JudgementModalMaintainService judgementModalMaintainService;

    private Section section;
    private JudgerInfo judgerInfo;
    private JudgementModal judgementModal;

    @Before
    public void setUp() {
        section = new Section(new LongIdKey(SECTION_LONG_ID), "name", true, "remark");
        judgerInfo = new JudgerInfo(new LongIdKey(JUDGER_INFO_LONG_ID), null, 12450, true, "type", "param", "remark");
        judgementModal = new JudgementModal(
                new LongIdKey(SECTION_LONG_ID), new LongIdKey(JUDGER_INFO_LONG_ID), new Date(), 12.450, "message"
        );
    }

    @After
    public void tearDown() {
        section = null;
        judgerInfo = null;
        judgementModal = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            sectionMaintainService.insertOrUpdate(section);
            judgerInfoMaintainService.insertOrUpdate(judgerInfo);
            judgementModalMaintainService.insertOrUpdate(judgementModal);
            JudgementModal testJudgementModal = judgementModalMaintainService.get(judgementModal.getKey());
            assertEquals(BeanUtils.describe(judgementModal), BeanUtils.describe(testJudgementModal));
            testJudgementModal = judgementModalMaintainService.get(judgementModal.getKey());
            assertEquals(BeanUtils.describe(judgementModal), BeanUtils.describe(testJudgementModal));
        } finally {
            if (Objects.nonNull(judgementModal.getKey())) {
                judgementModalMaintainService.deleteIfExists(judgementModal.getKey());
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
            sectionMaintainService.insertOrUpdate(section);
            judgerInfoMaintainService.insertOrUpdate(judgerInfo);
            judgementModalMaintainService.insertOrUpdate(judgementModal);

            assertTrue(judgementModalMaintainService.exists(judgementModal.getKey()));

            sectionMaintainService.deleteIfExists(section.getKey());

            assertFalse(judgementModalMaintainService.exists(judgementModal.getKey()));
        } finally {
            if (Objects.nonNull(judgementModal.getKey())) {
                judgementModalMaintainService.deleteIfExists(judgementModal.getKey());
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
            sectionMaintainService.insertOrUpdate(section);
            judgerInfoMaintainService.insertOrUpdate(judgerInfo);
            judgementModalMaintainService.insertOrUpdate(judgementModal);

            assertEquals(
                    1,
                    judgementModalMaintainService.lookupAsList(
                            JudgementModalMaintainService.CHILD_FOR_JUDGER_INFO, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    judgementModalMaintainService.lookupAsList(
                            JudgementModalMaintainService.CHILD_FOR_JUDGER_INFO, new Object[]{section.getKey()}
                    ).size()
            );

            assertFalse(judgementModalMaintainService.exists(judgementModal.getKey()));
        } finally {
            if (Objects.nonNull(judgementModal.getKey())) {
                judgementModalMaintainService.deleteIfExists(judgementModal.getKey());
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
