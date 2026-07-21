package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.entity.Verdict;
import com.dwarfeng.judge.stack.bean.key.VerdictKey;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.judge.stack.service.VerdictMaintainService;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class VerdictMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private VerdictMaintainService verdictMaintainService;

    private Section section;
    private List<Verdict> verdicts;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        verdicts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Verdict verdict = new Verdict(null, 0.5, "message");
            verdicts.add(verdict);
        }
    }

    @After
    public void tearDown() {
        section = null;
        verdicts.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (int i = 0; i < verdicts.size(); i++) {
                Verdict verdict = verdicts.get(i);
                verdict.setKey(new VerdictKey(section.getKey().getLongId(), "test.verdict." + i));
                verdictMaintainService.insertOrUpdate(verdict);
                assertTrue(verdictMaintainService.exists(verdict.getKey()));
                Verdict testVerdict = verdictMaintainService.get(verdict.getKey());
                assertEquals(BeanUtils.describe(verdict), BeanUtils.describe(testVerdict));
                verdict.setMessage("message.updated");
                verdictMaintainService.update(verdict);
                testVerdict = verdictMaintainService.get(verdict.getKey());
                assertEquals(BeanUtils.describe(verdict), BeanUtils.describe(testVerdict));
                verdictMaintainService.deleteIfExists(verdict.getKey());
                assertFalse(verdictMaintainService.exists(verdict.getKey()));
            }
        } finally {
            for (Verdict verdict : verdicts) {
                if (Objects.isNull(verdict.getKey())) {
                    continue;
                }
                verdictMaintainService.deleteIfExists(verdict.getKey());
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
            for (int i = 0; i < verdicts.size(); i++) {
                Verdict verdict = verdicts.get(i);
                verdict.setKey(new VerdictKey(section.getKey().getLongId(), "test.verdict.cascade." + i));
                verdictMaintainService.insertOrUpdate(verdict);
            }

            assertEquals(
                    verdicts.size(),
                    verdictMaintainService.lookupAsList(
                            VerdictMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    verdictMaintainService.lookupAsList(
                            VerdictMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );
            for (Verdict verdict : verdicts) {
                assertFalse(verdictMaintainService.exists(verdict.getKey()));
            }
        } finally {
            for (Verdict verdict : verdicts) {
                if (Objects.isNull(verdict.getKey())) {
                    continue;
                }
                verdictMaintainService.deleteIfExists(verdict.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
