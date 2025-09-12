package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AnalyserInfo;
import com.dwarfeng.judge.stack.bean.entity.AnalyserVariable;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.judge.stack.service.AnalyserInfoMaintainService;
import com.dwarfeng.judge.stack.service.AnalyserVariableMaintainService;
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
public class AnalyserVariableMaintainServiceImplTest {

    private static final Long ANALYSER_INFO_LONG_ID = 12450L;
    private static final String VARIABLE_STRING_ID = "test";

    @Autowired
    private AnalyserInfoMaintainService analyserInfoMaintainService;
    @Autowired
    private AnalyserVariableMaintainService analyserVariableMaintainService;

    private AnalyserInfo analyserInfo;
    private AnalyserVariable analyserVariable;

    @Before
    public void setUp() {
        analyserInfo = new AnalyserInfo(
                new LongIdKey(ANALYSER_INFO_LONG_ID), null, 12450, true, "type", "param", "remark"
        );
        analyserVariable = new AnalyserVariable(
                new AnalyserVariableKey(ANALYSER_INFO_LONG_ID, VARIABLE_STRING_ID), "value"
        );
    }

    @After
    public void tearDown() {
        analyserInfo = null;
        analyserVariable = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            analyserInfoMaintainService.insertOrUpdate(analyserInfo);
            analyserVariableMaintainService.insertOrUpdate(analyserVariable);
            AnalyserVariable testAnalyserVariable = analyserVariableMaintainService.get(analyserVariable.getKey());
            assertEquals(BeanUtils.describe(analyserVariable), BeanUtils.describe(testAnalyserVariable));
            testAnalyserVariable = analyserVariableMaintainService.get(analyserVariable.getKey());
            assertEquals(BeanUtils.describe(analyserVariable), BeanUtils.describe(testAnalyserVariable));
        } finally {
            if (Objects.nonNull(analyserVariable.getKey())) {
                analyserVariableMaintainService.deleteIfExists(analyserVariable.getKey());
            }
            if (Objects.nonNull(analyserInfo.getKey())) {
                analyserInfoMaintainService.deleteIfExists(analyserInfo.getKey());
            }
        }
    }

    @Test
    public void testForAnalyserInfoCascade() throws Exception {
        try {
            analyserInfoMaintainService.insertOrUpdate(analyserInfo);
            analyserVariableMaintainService.insertOrUpdate(analyserVariable);

            assertEquals(
                    1,
                    analyserVariableMaintainService.lookupAsList(
                            AnalyserVariableMaintainService.CHILD_FOR_ANALYSER_INFO,
                            new Object[]{analyserInfo.getKey()}
                    ).size()
            );

            analyserInfoMaintainService.deleteIfExists(analyserInfo.getKey());

            assertEquals(
                    0,
                    analyserVariableMaintainService.lookupAsList(
                            AnalyserVariableMaintainService.CHILD_FOR_ANALYSER_INFO,
                            new Object[]{analyserInfo.getKey()}
                    ).size()
            );

            assertFalse(analyserVariableMaintainService.exists(analyserVariable.getKey()));
        } finally {
            if (Objects.nonNull(analyserVariable.getKey())) {
                analyserVariableMaintainService.deleteIfExists(analyserVariable.getKey());
            }
            if (Objects.nonNull(analyserInfo.getKey())) {
                analyserInfoMaintainService.deleteIfExists(analyserInfo.getKey());
            }
        }
    }
}
