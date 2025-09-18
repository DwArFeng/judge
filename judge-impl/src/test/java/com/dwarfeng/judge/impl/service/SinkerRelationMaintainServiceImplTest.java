package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SinkerRelationMaintainService;
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
public class SinkerRelationMaintainServiceImplTest {

    @Autowired
    private SinkerRelationMaintainService sinkerRelationMaintainService;
    private List<SinkerRelation> sinkerRelations;

    @Autowired
    private SinkerInfoMaintainService sinkerInfoMaintainService;
    private SinkerInfo sinkerInfo;

    @Autowired
    private SectionMaintainService sectionMaintainService;
    private Section section;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        sinkerInfo = new SinkerInfo(null, true, "12450", "12450", "12450");
        sinkerRelations = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SinkerRelation sinkerRelation = new SinkerRelation(
                    new SinkerRelationKey(null, null), true, "12450"
            );
            sinkerRelations.add(sinkerRelation);
        }
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            sinkerInfo.setKey(sinkerInfoMaintainService.insertOrUpdate(sinkerInfo));
            for (SinkerRelation sinkerRelation : sinkerRelations) {
                sinkerRelation.getKey().setSinkerLongId(sinkerInfo.getKey().getLongId());
                sinkerRelation.getKey().setSectionLongId(section.getKey().getLongId());
                sinkerRelationMaintainService.insertOrUpdate(sinkerRelation);
                SinkerRelation testSinkerRelation = sinkerRelationMaintainService.get(sinkerRelation.getKey());
                assertEquals(BeanUtils.describe(sinkerRelation), BeanUtils.describe(testSinkerRelation));
            }
        } finally {
            for (SinkerRelation sinkerRelation : sinkerRelations) {
                if (Objects.isNull(sinkerRelation.getKey())) {
                    continue;
                }
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation.getKey());
            }
            if (Objects.nonNull(sinkerInfo.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }

    @Test
    public void testForSinkerInfoCascade() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            sinkerInfo.setKey(sinkerInfoMaintainService.insertOrUpdate(sinkerInfo));
            for (SinkerRelation sinkerRelation : sinkerRelations) {
                sinkerRelation.getKey().setSinkerLongId(sinkerInfo.getKey().getLongId());
                sinkerRelation.getKey().setSectionLongId(section.getKey().getLongId());
                sinkerRelationMaintainService.insertOrUpdate(sinkerRelation);
            }

            assertEquals(
                    1,
                    sinkerRelationMaintainService.lookupAsList(
                            SinkerRelationMaintainService.CHILD_FOR_SINKER_INFO, new Object[]{sinkerInfo.getKey()}
                    ).size()
            );

            sinkerInfoMaintainService.deleteIfExists(sinkerInfo.getKey());

            assertEquals(
                    0,
                    sinkerRelationMaintainService.lookupAsList(
                            SinkerRelationMaintainService.CHILD_FOR_SINKER_INFO, new Object[]{sinkerInfo.getKey()}
                    ).size()
            );

            for (SinkerRelation sinkerRelation : sinkerRelations) {
                assertFalse(sinkerRelationMaintainService.exists(sinkerRelation.getKey()));
            }
        } finally {
            for (SinkerRelation sinkerRelation : sinkerRelations) {
                if (Objects.isNull(sinkerRelation.getKey())) {
                    continue;
                }
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation.getKey());
            }
            if (Objects.nonNull(sinkerInfo.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo.getKey());
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
            sinkerInfo.setKey(sinkerInfoMaintainService.insertOrUpdate(sinkerInfo));
            for (SinkerRelation sinkerRelation : sinkerRelations) {
                sinkerRelation.getKey().setSinkerLongId(sinkerInfo.getKey().getLongId());
                sinkerRelation.getKey().setSectionLongId(section.getKey().getLongId());
                sinkerRelationMaintainService.insertOrUpdate(sinkerRelation);
            }

            assertEquals(
                    1,
                    sinkerRelationMaintainService.lookupAsList(
                            SinkerRelationMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    sinkerRelationMaintainService.lookupAsList(
                            SinkerRelationMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            for (SinkerRelation sinkerRelation : sinkerRelations) {
                assertFalse(sinkerRelationMaintainService.exists(sinkerRelation.getKey()));
            }
        } finally {
            for (SinkerRelation sinkerRelation : sinkerRelations) {
                if (Objects.isNull(sinkerRelation.getKey())) {
                    continue;
                }
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation.getKey());
            }
            if (Objects.nonNull(sinkerInfo.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        sinkerRelations.clear();
    }
}
