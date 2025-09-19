package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Task;
import com.dwarfeng.judge.stack.bean.entity.VisualizeData;
import com.dwarfeng.judge.stack.bean.key.VisualizeDataKey;
import com.dwarfeng.judge.stack.service.TaskMaintainService;
import com.dwarfeng.judge.stack.service.VisualizeDataMaintainService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class VisualizeDataMaintainServiceImplTest {

    private static final Long TASK_LONG_ID = 12450L;
    private static final String PERSPECTIVE_STRING_ID = "test";

    @Autowired
    private TaskMaintainService taskMaintainService;
    @Autowired
    private VisualizeDataMaintainService visualizeDataMaintainService;

    private Task task;
    private VisualizeData visualizeData;

    @Before
    public void setUp() {
        task = new Task(
                new LongIdKey(TASK_LONG_ID), null, 12450, new Date(), new Date(), new Date(), 12450L, new Date(),
                new Date(), new Date(), new Date(), "anchorMessage"
        );
        visualizeData = new VisualizeData(
                new VisualizeDataKey(TASK_LONG_ID, PERSPECTIVE_STRING_ID), "content"
        );
    }

    @After
    public void tearDown() {
        task = null;
        visualizeData = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            taskMaintainService.insertOrUpdate(task);
            visualizeDataMaintainService.insertOrUpdate(visualizeData);
            VisualizeData testVisualizeData = visualizeDataMaintainService.get(visualizeData.getKey());
            assertEquals(BeanUtils.describe(visualizeData), BeanUtils.describe(testVisualizeData));
            testVisualizeData = visualizeDataMaintainService.get(visualizeData.getKey());
            assertEquals(BeanUtils.describe(visualizeData), BeanUtils.describe(testVisualizeData));
        } finally {
            if (Objects.nonNull(visualizeData.getKey())) {
                visualizeDataMaintainService.deleteIfExists(visualizeData.getKey());
            }
            if (Objects.nonNull(task.getKey())) {
                taskMaintainService.deleteIfExists(task.getKey());
            }
        }
    }

    @Test
    public void testForTaskCascade() throws Exception {
        try {
            taskMaintainService.insertOrUpdate(task);
            visualizeDataMaintainService.insertOrUpdate(visualizeData);

            assertEquals(
                    1,
                    visualizeDataMaintainService.lookupAsList(
                            VisualizeDataMaintainService.CHILD_FOR_TASK,
                            new Object[]{task.getKey()}
                    ).size()
            );

            taskMaintainService.deleteIfExists(task.getKey());

            assertEquals(
                    0,
                    visualizeDataMaintainService.lookupAsList(
                            VisualizeDataMaintainService.CHILD_FOR_TASK,
                            new Object[]{task.getKey()}
                    ).size()
            );

            assertFalse(visualizeDataMaintainService.exists(visualizeData.getKey()));
        } finally {
            if (Objects.nonNull(visualizeData.getKey())) {
                visualizeDataMaintainService.deleteIfExists(visualizeData.getKey());
            }
            if (Objects.nonNull(task.getKey())) {
                taskMaintainService.deleteIfExists(task.getKey());
            }
        }
    }
}
