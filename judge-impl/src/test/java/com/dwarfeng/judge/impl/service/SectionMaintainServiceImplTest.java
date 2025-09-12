package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Section;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class SectionMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;

    private List<Section> sections;

    @Before
    public void setUp() {
        sections = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Section section = new Section(null, "name", true, "remark");
            sections.add(section);
        }
    }

    @After
    public void tearDown() {
        sections.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (Section section : sections) {
                section.setKey(sectionMaintainService.insertOrUpdate(section));
                Section testSection = sectionMaintainService.get(section.getKey());
                assertEquals(BeanUtils.describe(section), BeanUtils.describe(testSection));
                testSection = sectionMaintainService.get(section.getKey());
                assertEquals(BeanUtils.describe(section), BeanUtils.describe(testSection));
            }
        } finally {
            for (Section section : sections) {
                if (Objects.isNull(section.getKey())) {
                    continue;
                }
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
