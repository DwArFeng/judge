package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AnalyserSupport;
import com.dwarfeng.judge.stack.service.AnalyserSupportMaintainService;
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
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class JudgerSupportMaintainServiceImplTest {

    @Autowired
    private AnalyserSupportMaintainService service;

    private final List<AnalyserSupport> analyserSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            AnalyserSupport analyserSupport = new AnalyserSupport(
                    new StringIdKey("analyser-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            analyserSupports.add(analyserSupport);
        }
    }

    @After
    public void tearDown() {
        analyserSupports.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AnalyserSupport analyserSupport : analyserSupports) {
                analyserSupport.setKey(service.insertOrUpdate(analyserSupport));
                AnalyserSupport testAnalyserSupport = service.get(analyserSupport.getKey());
                assertEquals(BeanUtils.describe(analyserSupport), BeanUtils.describe(testAnalyserSupport));
                testAnalyserSupport = service.get(analyserSupport.getKey());
                assertEquals(BeanUtils.describe(analyserSupport), BeanUtils.describe(testAnalyserSupport));
            }
        } finally {
            for (AnalyserSupport analyserSupport : analyserSupports) {
                if (Objects.isNull(analyserSupport.getKey())) {
                    continue;
                }
                service.deleteIfExists(analyserSupport.getKey());
            }
        }
    }
}
