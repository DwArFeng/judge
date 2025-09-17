package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Judgement;
import com.dwarfeng.judge.stack.bean.entity.Task;
import com.dwarfeng.judge.stack.bean.key.JudgementKey;
import com.dwarfeng.judge.stack.service.JudgementMaintainService;
import com.dwarfeng.judge.stack.service.TaskMaintainService;
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
public class JudgementMaintainServiceImplTest {

    private static final Long TASK_LONG_ID = 12450L;
    private static final String DATA_STRING_ID = "test";

    @Autowired
    private TaskMaintainService taskMaintainService;
    @Autowired
    private JudgementMaintainService judgementMaintainService;

    private Task task;
    private Judgement judgement;

    @Before
    public void setUp() {
        task = new Task(
                new LongIdKey(TASK_LONG_ID), null, 12450, new Date(), new Date(), new Date(), 12450L,
                new Date(), new Date(), new Date(), new Date(), "anchorMessage"
        );
        judgement = new Judgement(new JudgementKey(TASK_LONG_ID, DATA_STRING_ID), 12.450, "message");
    }

    @After
    public void tearDown() {
        task = null;
        judgement = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            taskMaintainService.insertOrUpdate(task);
            judgementMaintainService.insertOrUpdate(judgement);
            Judgement testJudgement = judgementMaintainService.get(judgement.getKey());
            assertEquals(BeanUtils.describe(judgement), BeanUtils.describe(testJudgement));
            testJudgement = judgementMaintainService.get(judgement.getKey());
            assertEquals(BeanUtils.describe(judgement), BeanUtils.describe(testJudgement));
        } finally {
            if (Objects.nonNull(judgement.getKey())) {
                judgementMaintainService.deleteIfExists(judgement.getKey());
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
            judgementMaintainService.insertOrUpdate(judgement);

            assertEquals(
                    1,
                    judgementMaintainService.lookupAsList(
                            JudgementMaintainService.CHILD_FOR_TASK, new Object[]{task.getKey()}
                    ).size()
            );

            taskMaintainService.deleteIfExists(task.getKey());

            assertEquals(
                    0,
                    judgementMaintainService.lookupAsList(
                            JudgementMaintainService.CHILD_FOR_TASK, new Object[]{task.getKey()}
                    ).size()
            );

            assertFalse(judgementMaintainService.exists(judgement.getKey()));
        } finally {
            if (Objects.nonNull(judgement.getKey())) {
                judgementMaintainService.deleteIfExists(judgement.getKey());
            }
            if (Objects.nonNull(task.getKey())) {
                taskMaintainService.deleteIfExists(task.getKey());
            }
        }
    }
}
