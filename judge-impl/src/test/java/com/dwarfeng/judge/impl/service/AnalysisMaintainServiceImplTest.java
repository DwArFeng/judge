package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Analysis;
import com.dwarfeng.judge.stack.bean.entity.Task;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.judge.stack.service.AnalysisMaintainService;
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
public class AnalysisMaintainServiceImplTest {

    private static final Long TASK_LONG_ID = 12450L;
    private static final String DATA_STRING_ID = "test";

    @Autowired
    private TaskMaintainService taskMaintainService;
    @Autowired
    private AnalysisMaintainService analysisMaintainService;

    private Task task;
    private Analysis analysis;

    @Before
    public void setUp() {
        task = new Task(
                new LongIdKey(TASK_LONG_ID), null, 12450, new Date(), new Date(), new Date(), 12450L,
                new Date(), new Date(), new Date(), new Date(), "anchorMessage"
        );
        analysis = new Analysis(
                new AnalysisKey(TASK_LONG_ID, DATA_STRING_ID),
                12450, "stringValue", 12450L, 12.450, true, new Date(), 12450L, 12450L, 12450L, 12450L
        );
    }

    @After
    public void tearDown() {
        task = null;
        analysis = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            taskMaintainService.insertOrUpdate(task);
            analysisMaintainService.insertOrUpdate(analysis);
            Analysis testAnalysis = analysisMaintainService.get(analysis.getKey());
            assertEquals(BeanUtils.describe(analysis), BeanUtils.describe(testAnalysis));
            testAnalysis = analysisMaintainService.get(analysis.getKey());
            assertEquals(BeanUtils.describe(analysis), BeanUtils.describe(testAnalysis));
        } finally {
            if (Objects.nonNull(analysis.getKey())) {
                analysisMaintainService.deleteIfExists(analysis.getKey());
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
            analysisMaintainService.insertOrUpdate(analysis);

            assertEquals(
                    1,
                    analysisMaintainService.lookupAsList(
                            AnalysisMaintainService.CHILD_FOR_TASK, new Object[]{task.getKey()}
                    ).size()
            );

            taskMaintainService.deleteIfExists(task.getKey());

            assertEquals(
                    0,
                    analysisMaintainService.lookupAsList(
                            AnalysisMaintainService.CHILD_FOR_TASK, new Object[]{task.getKey()}
                    ).size()
            );

            assertFalse(analysisMaintainService.exists(analysis.getKey()));
        } finally {
            if (Objects.nonNull(analysis.getKey())) {
                analysisMaintainService.deleteIfExists(analysis.getKey());
            }
            if (Objects.nonNull(task.getKey())) {
                taskMaintainService.deleteIfExists(task.getKey());
            }
        }
    }
}
