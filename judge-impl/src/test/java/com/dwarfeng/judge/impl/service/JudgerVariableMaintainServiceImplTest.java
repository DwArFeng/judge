package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerVariable;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.judge.stack.service.JudgerInfoMaintainService;
import com.dwarfeng.judge.stack.service.JudgerVariableMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class JudgerVariableMaintainServiceImplTest {

    private static final Long JUDGER_INFO_LONG_ID = 12450L;
    private static final String VARIABLE_STRING_ID = "test";

    @Autowired
    private JudgerInfoMaintainService judgerInfoMaintainService;
    @Autowired
    private JudgerVariableMaintainService judgerVariableMaintainService;

    private JudgerInfo judgerInfo;
    private JudgerVariable judgerVariable;

    @Before
    public void setUp() {
        judgerInfo = new JudgerInfo(
                new LongIdKey(JUDGER_INFO_LONG_ID), null, 12450, true, "type", "param", "remark"
        );
        judgerVariable = new JudgerVariable(
                new JudgerVariableKey(JUDGER_INFO_LONG_ID, VARIABLE_STRING_ID), "value"
        );
    }

    @After
    public void tearDown() {
        judgerInfo = null;
        judgerVariable = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            judgerInfoMaintainService.insertOrUpdate(judgerInfo);
            judgerVariableMaintainService.insertOrUpdate(judgerVariable);
            JudgerVariable testJudgerVariable = judgerVariableMaintainService.get(judgerVariable.getKey());
            assertEquals(BeanUtils.describe(judgerVariable), BeanUtils.describe(testJudgerVariable));
            testJudgerVariable = judgerVariableMaintainService.get(judgerVariable.getKey());
            assertEquals(BeanUtils.describe(judgerVariable), BeanUtils.describe(testJudgerVariable));
        } finally {
            if (Objects.nonNull(judgerVariable.getKey())) {
                judgerVariableMaintainService.deleteIfExists(judgerVariable.getKey());
            }
            if (Objects.nonNull(judgerInfo.getKey())) {
                judgerInfoMaintainService.deleteIfExists(judgerInfo.getKey());
            }
        }
    }

    @Test
    public void testForJudgerInfoCascade() throws Exception {
        try {
            judgerInfoMaintainService.insertOrUpdate(judgerInfo);
            judgerVariableMaintainService.insertOrUpdate(judgerVariable);

            assertEquals(
                    1,
                    judgerVariableMaintainService.lookupAsList(
                            JudgerVariableMaintainService.CHILD_FOR_JUDGER_INFO,
                            new Object[]{judgerInfo.getKey()}
                    ).size()
            );

            judgerInfoMaintainService.deleteIfExists(judgerInfo.getKey());

            assertEquals(
                    0,
                    judgerVariableMaintainService.lookupAsList(
                            JudgerVariableMaintainService.CHILD_FOR_JUDGER_INFO,
                            new Object[]{judgerInfo.getKey()}
                    ).size()
            );

            assertFalse(judgerVariableMaintainService.exists(judgerVariable.getKey()));
        } finally {
            if (Objects.nonNull(judgerVariable.getKey())) {
                judgerVariableMaintainService.deleteIfExists(judgerVariable.getKey());
            }
            if (Objects.nonNull(judgerInfo.getKey())) {
                judgerInfoMaintainService.deleteIfExists(judgerInfo.getKey());
            }
        }
    }
}
