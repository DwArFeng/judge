package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.entity.Task;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.judge.stack.service.TaskMaintainService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class TaskMaintainServiceImplTest {

    @Autowired
    private SectionMaintainService sectionMaintainService;
    @Autowired
    private TaskMaintainService taskMaintainService;

    private Section section;
    private List<Task> tasks;

    @Before
    public void setUp() {
        section = new Section(null, "name", true, "remark");
        tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Task task = new Task(
                    null, null, 12450, new Date(), new Date(), new Date(), 12450L, new Date(), new Date(), new Date(),
                    new Date(), "anchorMessage"
            );
            tasks.add(task);
        }
    }

    @After
    public void tearDown() {
        section = null;
        tasks.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            section.setKey(sectionMaintainService.insertOrUpdate(section));
            for (Task task : tasks) {
                task.setSectionKey(section.getKey());
                task.setKey(taskMaintainService.insertOrUpdate(task));
                Task testTask = taskMaintainService.get(task.getKey());
                assertEquals(BeanUtils.describe(task), BeanUtils.describe(testTask));
                testTask = taskMaintainService.get(task.getKey());
                assertEquals(BeanUtils.describe(task), BeanUtils.describe(testTask));
            }
        } finally {
            for (Task task : tasks) {
                if (Objects.isNull(task.getKey())) {
                    continue;
                }
                taskMaintainService.deleteIfExists(task.getKey());
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
            for (Task task : tasks) {
                task.setSectionKey(section.getKey());
                task.setKey(taskMaintainService.insertOrUpdate(task));
            }

            assertEquals(
                    tasks.size(),
                    taskMaintainService.lookupAsList(
                            TaskMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            sectionMaintainService.deleteIfExists(section.getKey());

            assertEquals(
                    0,
                    taskMaintainService.lookupAsList(
                            TaskMaintainService.CHILD_FOR_SECTION, new Object[]{section.getKey()}
                    ).size()
            );

            for (Task task : tasks) {
                assertFalse(taskMaintainService.exists(task.getKey()));
            }
        } finally {
            for (Task task : tasks) {
                if (Objects.isNull(task.getKey())) {
                    continue;
                }
                taskMaintainService.deleteIfExists(task.getKey());
            }
            if (Objects.nonNull(section.getKey())) {
                sectionMaintainService.deleteIfExists(section.getKey());
            }
        }
    }
}
