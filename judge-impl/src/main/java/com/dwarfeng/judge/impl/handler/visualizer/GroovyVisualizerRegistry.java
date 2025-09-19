package com.dwarfeng.judge.impl.handler.visualizer;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.judge.sdk.handler.visualizer.AbstractVisualizerRegistry;
import com.dwarfeng.judge.stack.exception.VisualizerException;
import com.dwarfeng.judge.stack.exception.VisualizerMakeException;
import com.dwarfeng.judge.stack.handler.Visualizer;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Groovy 可视化器注册。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
@Component
public class GroovyVisualizerRegistry extends AbstractVisualizerRegistry {

    public static final String VISUALIZER_TYPE = "groovy_visualizer";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyVisualizerRegistry.class);

    private final ApplicationContext ctx;

    public GroovyVisualizerRegistry(ApplicationContext ctx) {
        super(VISUALIZER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "Groovy 可视化器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的 groovy 脚本进行数据可视化。";
    }

    @Override
    public String provideExampleParam() {
        try {
            Resource resource = ctx.getResource("classpath:groovy/ExampleVisualizerProcessor.groovy");
            String example;
            try (InputStream sin = resource.getInputStream();
                 StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn("读取文件 classpath:groovy/ExampleVisualizerProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Visualizer makeVisualizer(String type, String param) throws VisualizerException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过Groovy脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(param);
            Processor processor = (Processor) aClass.newInstance();
            // 生成并返回可视化器。
            return ctx.getBean(GroovyVisualizer.class, ctx, processor);
        } catch (Exception e) {
            throw new VisualizerMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "GroovyVisualizerRegistry{" +
                "ctx=" + ctx +
                ", visualizerType='" + visualizerType + '\'' +
                '}';
    }
}
