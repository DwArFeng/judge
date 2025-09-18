package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.bean.entity.SinkerMeta;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaKey;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SinkerMetaMaintainService;
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
public class SinkerMetaMaintainServiceImplTest {

    @Autowired
    private SinkerMetaMaintainService sinkerMetaMaintainService;
    private List<SinkerMeta> sinkerMetas;

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
        sinkerMetas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SinkerMeta sinkerMeta = new SinkerMeta(
                    new SinkerMetaKey(null, null, "test.sinker_meta." + (i + 1)), "12450", "12450"
            );
            sinkerMetas.add(sinkerMeta);
        }
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            sinkerInfo.setKey(sinkerInfoMaintainService.insertOrUpdate(sinkerInfo));
            for (SinkerMeta sinkerMeta : sinkerMetas) {
                sinkerMeta.getKey().setSinkerLongId(sinkerInfo.getKey().getLongId());
                sinkerMeta.getKey().setSectionLongId(section.getKey().getLongId());
                sinkerMetaMaintainService.insertOrUpdate(sinkerMeta);
                SinkerMeta testSinkerMeta = sinkerMetaMaintainService.get(sinkerMeta.getKey());
                assertEquals(BeanUtils.describe(sinkerMeta), BeanUtils.describe(testSinkerMeta));
            }
        } finally {
            for (SinkerMeta sinkerMeta : sinkerMetas) {
                if (Objects.isNull(sinkerMeta.getKey())) {
                    continue;
                }
                sinkerMetaMaintainService.deleteIfExists(sinkerMeta.getKey());
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
            for (SinkerMeta sinkerMeta : sinkerMetas) {
                sinkerMeta.getKey().setSinkerLongId(sinkerInfo.getKey().getLongId());
                sinkerMeta.getKey().setSectionLongId(section.getKey().getLongId());
                sinkerMetaMaintainService.insertOrUpdate(sinkerMeta);
            }

            assertEquals(
                    sinkerMetas.size(),
                    sinkerMetaMaintainService.lookupAsList(
                            SinkerMetaMaintainService.CHILD_FOR_SINKER_INFO, new Object[]{sinkerInfo.getKey()}
                    ).size()
            );

            sinkerInfoMaintainService.deleteIfExists(sinkerInfo.getKey());

            assertEquals(
                    0,
                    sinkerMetaMaintainService.lookupAsList(
                            SinkerMetaMaintainService.CHILD_FOR_SINKER_INFO, new Object[]{sinkerInfo.getKey()}
                    ).size()
            );

            for (SinkerMeta sinkerMeta : sinkerMetas) {
                assertFalse(sinkerMetaMaintainService.exists(sinkerMeta.getKey()));
            }
        } finally {
            for (SinkerMeta sinkerMeta : sinkerMetas) {
                if (Objects.isNull(sinkerMeta.getKey())) {
                    continue;
                }
                sinkerMetaMaintainService.deleteIfExists(sinkerMeta.getKey());
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
            for (SinkerMeta sinkerMeta : sinkerMetas) {
                sinkerMeta.getKey().setSinkerLongId(sinkerInfo.getKey().getLongId());
                sinkerMeta.getKey().setSectionLongId(section.getKey().getLongId());
                sinkerMetaMaintainService.insertOrUpdate(sinkerMeta);
            }

            assertEquals(
                    sinkerMetas.size(),
                    sinkerMetaMaintainService.lookupAsList(
                            SinkerMetaMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    sinkerMetaMaintainService.lookupAsList(
                            SinkerMetaMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            for (SinkerMeta sinkerMeta : sinkerMetas) {
                assertFalse(sinkerMetaMaintainService.exists(sinkerMeta.getKey()));
            }
        } finally {
            for (SinkerMeta sinkerMeta : sinkerMetas) {
                if (Objects.isNull(sinkerMeta.getKey())) {
                    continue;
                }
                sinkerMetaMaintainService.deleteIfExists(sinkerMeta.getKey());
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
        sinkerInfo = null;
        section = null;
        sinkerMetas.clear();
    }
}
