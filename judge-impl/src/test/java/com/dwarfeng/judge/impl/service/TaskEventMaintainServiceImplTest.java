package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Task;
import com.dwarfeng.judge.stack.bean.entity.TaskEvent;
import com.dwarfeng.judge.stack.service.TaskEventMaintainService;
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
public class TaskEventMaintainServiceImplTest {

    @Autowired
    private TaskMaintainService taskMaintainService;
    @Autowired
    private TaskEventMaintainService taskEventMaintainService;

    private Task task;
    private List<TaskEvent> taskEvents;

    @Before
    public void setUp() {
        task = new Task(
                null, null, 12450, new Date(), new Date(), new Date(), 12450L, new Date(), new Date(), new Date(),
                new Date(), "anchorMessage"
        );
        taskEvents = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TaskEvent taskEvent = new TaskEvent(null, null, new Date(), "message");
            taskEvents.add(taskEvent);
        }
    }

    @After
    public void tearDown() {
        task = null;
        taskEvents.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            task.setKey(taskMaintainService.insertOrUpdate(task));
            for (TaskEvent taskEvent : taskEvents) {
                taskEvent.setTaskKey(task.getKey());
                taskEvent.setKey(taskEventMaintainService.insertOrUpdate(taskEvent));
                TaskEvent testTaskEvent = taskEventMaintainService.get(taskEvent.getKey());
                assertEquals(BeanUtils.describe(taskEvent), BeanUtils.describe(testTaskEvent));
                testTaskEvent = taskEventMaintainService.get(taskEvent.getKey());
                assertEquals(BeanUtils.describe(taskEvent), BeanUtils.describe(testTaskEvent));
            }
        } finally {
            for (TaskEvent taskEvent : taskEvents) {
                if (Objects.isNull(taskEvent.getKey())) {
                    continue;
                }
                taskEventMaintainService.deleteIfExists(taskEvent.getKey());
            }
            if (Objects.nonNull(task.getKey())) {
                taskMaintainService.deleteIfExists(task.getKey());
            }
        }
    }

    @Test
    public void testForTaskCascade() throws Exception {
        try {
            task.setKey(taskMaintainService.insertOrUpdate(task));
            for (TaskEvent taskEvent : taskEvents) {
                taskEvent.setTaskKey(task.getKey());
                taskEvent.setKey(taskEventMaintainService.insertOrUpdate(taskEvent));
            }

            assertEquals(
                    taskEvents.size(),
                    taskEventMaintainService.lookupAsList(
                            TaskEventMaintainService.CHILD_FOR_TASK, new Object[]{task.getKey()}
                    ).size()
            );

            taskMaintainService.deleteIfExists(task.getKey());

            assertEquals(
                    0,
                    taskEventMaintainService.lookupAsList(
                            TaskEventMaintainService.CHILD_FOR_TASK, new Object[]{task.getKey()}
                    ).size()
            );

            for (TaskEvent taskEvent : taskEvents) {
                assertFalse(taskEventMaintainService.exists(taskEvent.getKey()));
            }
        } finally {
            for (TaskEvent taskEvent : taskEvents) {
                if (Objects.isNull(taskEvent.getKey())) {
                    continue;
                }
                taskEventMaintainService.deleteIfExists(taskEvent.getKey());
            }
            if (Objects.nonNull(task.getKey())) {
                taskMaintainService.deleteIfExists(task.getKey());
            }
        }
    }
}
