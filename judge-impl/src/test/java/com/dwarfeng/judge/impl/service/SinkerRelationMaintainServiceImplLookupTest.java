package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SinkerRelationMaintainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class SinkerRelationMaintainServiceImplLookupTest {

    @Autowired
    private SinkerRelationMaintainService sinkerRelationMaintainService;
    @Autowired
    private SinkerInfoMaintainService sinkerInfoMaintainService;
    @Autowired
    private SectionMaintainService sectionMaintainService;

    @Test
    public void testChildForSectionEnabledSectionEnabled1() throws Exception {
        Section section = new Section(null, "name", true, "remark");

        SinkerInfo sinkerInfo1 = new SinkerInfo(null, true, "12450", "12450", "12450");
        SinkerInfo sinkerInfo2 = new SinkerInfo(null, true, "12450", "12450", "12450");
        SinkerInfo sinkerInfo3 = new SinkerInfo(null, true, "12450", "12450", "12450");
        SinkerInfo sinkerInfo4 = new SinkerInfo(null, true, "12450", "12450", "12450");

        SinkerRelation sinkerRelation1 = new SinkerRelation(null, true, "12450");
        SinkerRelation sinkerRelation2 = new SinkerRelation(null, true, "12450");
        SinkerRelation sinkerRelation3 = new SinkerRelation(null, false, "12450");
        SinkerRelation sinkerRelation4 = new SinkerRelation(null, false, "12450");

        try {
            // 数据构建。
            section.setKey(sectionMaintainService.insert(section));
            sinkerInfo1.setKey(sinkerInfoMaintainService.insert(sinkerInfo1));
            sinkerInfo2.setKey(sinkerInfoMaintainService.insert(sinkerInfo2));
            sinkerInfo3.setKey(sinkerInfoMaintainService.insert(sinkerInfo3));
            sinkerInfo4.setKey(sinkerInfoMaintainService.insert(sinkerInfo4));
            sinkerRelation1.setKey(
                    new SinkerRelationKey(section.getKey().getLongId(), sinkerInfo1.getKey().getLongId())
            );
            sinkerRelationMaintainService.insert(sinkerRelation1);
            sinkerRelation2.setKey(
                    new SinkerRelationKey(section.getKey().getLongId(), sinkerInfo2.getKey().getLongId())
            );
            sinkerRelationMaintainService.insert(sinkerRelation2);
            sinkerRelation3.setKey(
                    new SinkerRelationKey(section.getKey().getLongId(), sinkerInfo3.getKey().getLongId())
            );
            sinkerRelationMaintainService.insert(sinkerRelation3);
            sinkerRelation4.setKey(
                    new SinkerRelationKey(section.getKey().getLongId(), sinkerInfo4.getKey().getLongId())
            );
            sinkerRelationMaintainService.insert(sinkerRelation4);

            // 数据测试。
            List<SinkerRelationKey> sinkerRelationKeys = sinkerRelationMaintainService.lookupAsList(
                    SinkerRelationMaintainService.CHILD_FOR_SECTION_ENABLED_SECTION_ENABLED, new Object[]{section.getKey()}
            ).stream().map(SinkerRelation::getKey).collect(Collectors.toList());

            assertEquals(2, sinkerRelationKeys.size());
            assertTrue(sinkerRelationKeys.contains(sinkerRelation1.getKey()));
            assertTrue(sinkerRelationKeys.contains(sinkerRelation2.getKey()));
            assertFalse(sinkerRelationKeys.contains(sinkerRelation3.getKey()));
            assertFalse(sinkerRelationKeys.contains(sinkerRelation4.getKey()));
        } finally {
            if (Objects.nonNull(sinkerRelation4.getKey())) {
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation4.getKey());
            }
            if (Objects.nonNull(sinkerRelation3.getKey())) {
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation3.getKey());
            }
            if (Objects.nonNull(sinkerRelation2.getKey())) {
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation2.getKey());
            }
            if (Objects.nonNull(sinkerRelation1.getKey())) {
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation1.getKey());
            }

            if (Objects.nonNull(sinkerInfo4.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo4.getKey());
            }
            if (Objects.nonNull(sinkerInfo3.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo3.getKey());
            }
            if (Objects.nonNull(sinkerInfo2.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo2.getKey());
            }
            if (Objects.nonNull(sinkerInfo1.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo1.getKey());
            }

            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }

    @Test
    public void testChildForSectionEnabledSectionEnabled2() throws Exception {
        Section section = new Section(null, "name", false, "remark");

        SinkerInfo sinkerInfo1 = new SinkerInfo(null, true, "12450", "12450", "12450");
        SinkerInfo sinkerInfo2 = new SinkerInfo(null, true, "12450", "12450", "12450");
        SinkerInfo sinkerInfo3 = new SinkerInfo(null, true, "12450", "12450", "12450");
        SinkerInfo sinkerInfo4 = new SinkerInfo(null, true, "12450", "12450", "12450");

        SinkerRelation sinkerRelation1 = new SinkerRelation(null, true, "12450");
        SinkerRelation sinkerRelation2 = new SinkerRelation(null, true, "12450");
        SinkerRelation sinkerRelation3 = new SinkerRelation(null, false, "12450");
        SinkerRelation sinkerRelation4 = new SinkerRelation(null, false, "12450");

        try {
            // 数据构建。
            section.setKey(sectionMaintainService.insert(section));
            sinkerInfo1.setKey(sinkerInfoMaintainService.insert(sinkerInfo1));
            sinkerInfo2.setKey(sinkerInfoMaintainService.insert(sinkerInfo2));
            sinkerInfo3.setKey(sinkerInfoMaintainService.insert(sinkerInfo3));
            sinkerInfo4.setKey(sinkerInfoMaintainService.insert(sinkerInfo4));
            sinkerRelation1.setKey(
                    new SinkerRelationKey(section.getKey().getLongId(), sinkerInfo1.getKey().getLongId())
            );
            sinkerRelationMaintainService.insert(sinkerRelation1);
            sinkerRelation2.setKey(
                    new SinkerRelationKey(section.getKey().getLongId(), sinkerInfo2.getKey().getLongId())
            );
            sinkerRelationMaintainService.insert(sinkerRelation2);
            sinkerRelation3.setKey(
                    new SinkerRelationKey(section.getKey().getLongId(), sinkerInfo3.getKey().getLongId())
            );
            sinkerRelationMaintainService.insert(sinkerRelation3);
            sinkerRelation4.setKey(
                    new SinkerRelationKey(section.getKey().getLongId(), sinkerInfo4.getKey().getLongId())
            );
            sinkerRelationMaintainService.insert(sinkerRelation4);

            // 数据测试。
            List<SinkerRelationKey> sinkerRelationKeys = sinkerRelationMaintainService.lookupAsList(
                    SinkerRelationMaintainService.CHILD_FOR_SECTION_ENABLED_SECTION_ENABLED, new Object[]{section.getKey()}
            ).stream().map(SinkerRelation::getKey).collect(Collectors.toList());

            assertEquals(0, sinkerRelationKeys.size());
            assertFalse(sinkerRelationKeys.contains(sinkerRelation1.getKey()));
            assertFalse(sinkerRelationKeys.contains(sinkerRelation2.getKey()));
            assertFalse(sinkerRelationKeys.contains(sinkerRelation3.getKey()));
            assertFalse(sinkerRelationKeys.contains(sinkerRelation4.getKey()));
        } finally {
            if (Objects.nonNull(sinkerRelation4.getKey())) {
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation4.getKey());
            }
            if (Objects.nonNull(sinkerRelation3.getKey())) {
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation3.getKey());
            }
            if (Objects.nonNull(sinkerRelation2.getKey())) {
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation2.getKey());
            }
            if (Objects.nonNull(sinkerRelation1.getKey())) {
                sinkerRelationMaintainService.deleteIfExists(sinkerRelation1.getKey());
            }

            if (Objects.nonNull(sinkerInfo4.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo4.getKey());
            }
            if (Objects.nonNull(sinkerInfo3.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo3.getKey());
            }
            if (Objects.nonNull(sinkerInfo2.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo2.getKey());
            }
            if (Objects.nonNull(sinkerInfo1.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo1.getKey());
            }

            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
