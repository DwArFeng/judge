package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.bean.entity.SinkerVariable;
import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SinkerVariableMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
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
public class SinkerVariableMaintainServiceImplTest {

    private static final Long SINKER_INFO_ID = 12450L;

    @Autowired
    private SinkerVariableMaintainService sinkerVariableMaintainService;
    private List<SinkerVariable> sinkerVariables;

    @Autowired
    private SinkerInfoMaintainService sinkerInfoMaintainService;
    private SinkerInfo sinkerInfo;

    @Before
    public void setUp() {
        sinkerInfo = new SinkerInfo(new LongIdKey(SINKER_INFO_ID), true, "12450", "12450", "12450");
        sinkerVariables = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SinkerVariable sinkerVariable = new SinkerVariable(
                    new SinkerVariableKey(SINKER_INFO_ID, "variableStringId." + i), "value"
            );
            sinkerVariables.add(sinkerVariable);
        }
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            sinkerInfoMaintainService.insertOrUpdate(sinkerInfo);
            for (SinkerVariable sinkerVariable : sinkerVariables) {
                sinkerVariableMaintainService.insertOrUpdate(sinkerVariable);
                SinkerVariable testSinkerVariable = sinkerVariableMaintainService.get(sinkerVariable.getKey());
                assertEquals(BeanUtils.describe(sinkerVariable), BeanUtils.describe(testSinkerVariable));
                sinkerVariableMaintainService.insertOrUpdate(sinkerVariable);
                testSinkerVariable = sinkerVariableMaintainService.get(sinkerVariable.getKey());
                assertEquals(BeanUtils.describe(sinkerVariable), BeanUtils.describe(testSinkerVariable));
            }
        } finally {
            for (SinkerVariable sinkerVariable : sinkerVariables) {
                if (Objects.isNull(sinkerVariable.getKey())) {
                    continue;
                }
                sinkerVariableMaintainService.deleteIfExists(sinkerVariable.getKey());
            }
            if (Objects.nonNull(sinkerInfo.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo.getKey());
            }
        }
    }

    @Test
    public void testForSinkerInfoCascade() throws Exception {
        try {
            sinkerInfoMaintainService.insertOrUpdate(sinkerInfo);
            for (SinkerVariable sinkerVariable : sinkerVariables) {
                sinkerVariableMaintainService.insertOrUpdate(sinkerVariable);
            }

            assertEquals(
                    sinkerVariables.size(),
                    sinkerVariableMaintainService.lookupAsList(
                            SinkerVariableMaintainService.CHILD_FOR_SINKER_INFO, new Object[]{sinkerInfo.getKey()}
                    ).size()
            );

            sinkerInfoMaintainService.deleteIfExists(sinkerInfo.getKey());

            assertEquals(
                    0,
                    sinkerVariableMaintainService.lookupAsList(
                            SinkerVariableMaintainService.CHILD_FOR_SINKER_INFO, new Object[]{sinkerInfo.getKey()}
                    ).size()
            );

            for (SinkerVariable sinkerVariable : sinkerVariables) {
                assertFalse(sinkerVariableMaintainService.exists(sinkerVariable.getKey()));
            }
        } finally {
            for (SinkerVariable sinkerVariable : sinkerVariables) {
                if (Objects.isNull(sinkerVariable.getKey())) {
                    continue;
                }
                sinkerVariableMaintainService.deleteIfExists(sinkerVariable.getKey());
            }
            if (Objects.nonNull(sinkerInfo.getKey())) {
                sinkerInfoMaintainService.deleteIfExists(sinkerInfo.getKey());
            }
        }
    }

    @After
    public void tearDown() {
        sinkerInfo = null;
        sinkerVariables.clear();
    }
}
