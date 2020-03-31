package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.JudgerSupport;
import com.dwarfeng.judge.stack.service.JudgerSupportMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
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
public class JudgerSupportMaintainServiceImplTest {

    @Autowired
    private JudgerSupportMaintainService service;

    private List<JudgerSupport> judgerSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            JudgerSupport judgerSupport = new JudgerSupport(
                    new StringIdKey("judger-support-" + (i + 1)),
                    "label-" + (i + 1),
                    "这是测试用的JudgerSupport",
                    "1233211234567"
            );
            judgerSupports.add(judgerSupport);
        }
    }

    @After
    public void tearDown() {
        judgerSupports.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (JudgerSupport judgerSupport : judgerSupports) {
                judgerSupport.setKey(service.insert(judgerSupport));
                service.update(judgerSupport);
                JudgerSupport testJudgerSupport = service.get(judgerSupport.getKey());
                assertEquals(BeanUtils.describe(judgerSupport), BeanUtils.describe(testJudgerSupport));
            }
        } finally {
            for (JudgerSupport judgerSupport : judgerSupports) {
                service.delete(judgerSupport.getKey());
            }
        }
    }
}