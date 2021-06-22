package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.Variable;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.judge.stack.service.VariableMaintainService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class VariableMaintainServiceImplTest {

    @Autowired
    private VariableMaintainService variableMaintainService;

    private List<Variable> variables;

    @Before
    public void setUp() {
        variables = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Variable variable = new Variable(
                    new VariableKey((long) i, "this-is-a-test"),
                    "variable-" + (i + 1),
                    "test-variable"
            );
            variables.add(variable);
        }
    }

    @After
    public void tearDown() {
        variables.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (Variable variable : variables) {
                variable.setKey(variableMaintainService.insertOrUpdate(variable));
                variableMaintainService.update(variable);
                Variable testVariable = variableMaintainService.get(variable.getKey());
                assertEquals(BeanUtils.describe(variable), BeanUtils.describe(testVariable));
            }
        } finally {
            for (Variable variable : variables) {
                variableMaintainService.deleteIfExists(variable.getKey());
            }
        }
    }
}
