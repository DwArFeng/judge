package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.VisualizerSupport;
import com.dwarfeng.judge.stack.service.VisualizerSupportMaintainService;
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
public class VisualizerSupportMaintainServiceImplTest {

    @Autowired
    private VisualizerSupportMaintainService service;

    private final List<VisualizerSupport> visualizerSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            VisualizerSupport visualizerSupport = new VisualizerSupport(
                    new StringIdKey("visualizer-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            visualizerSupports.add(visualizerSupport);
        }
    }

    @After
    public void tearDown() {
        visualizerSupports.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (VisualizerSupport visualizerSupport : visualizerSupports) {
                visualizerSupport.setKey(service.insertOrUpdate(visualizerSupport));
                VisualizerSupport testVisualizerSupport = service.get(visualizerSupport.getKey());
                assertEquals(BeanUtils.describe(visualizerSupport), BeanUtils.describe(testVisualizerSupport));
                testVisualizerSupport = service.get(visualizerSupport.getKey());
                assertEquals(BeanUtils.describe(visualizerSupport), BeanUtils.describe(testVisualizerSupport));
            }
        } finally {
            for (VisualizerSupport visualizerSupport : visualizerSupports) {
                if (Objects.isNull(visualizerSupport.getKey())) {
                    continue;
                }
                service.deleteIfExists(visualizerSupport.getKey());
            }
        }
    }
}
