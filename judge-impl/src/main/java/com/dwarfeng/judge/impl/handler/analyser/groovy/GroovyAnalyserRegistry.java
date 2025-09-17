package com.dwarfeng.judge.impl.handler.analyser.groovy;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.judge.sdk.handler.analyser.AbstractAnalyserRegistry;
import com.dwarfeng.judge.stack.exception.AnalyserException;
import com.dwarfeng.judge.stack.exception.AnalyserMakeException;
import com.dwarfeng.judge.stack.handler.Analyser;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Groovy 分析器注册。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Component
public class GroovyAnalyserRegistry extends AbstractAnalyserRegistry {

    public static final String ANALYSER_TYPE = "groovy_analyser";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyAnalyserRegistry.class);

    private final ApplicationContext ctx;

    public GroovyAnalyserRegistry(ApplicationContext ctx) {
        super(ANALYSER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "Groovy 分析器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的 groovy 脚本进行数据分析。";
    }

    @Override
    public String provideExampleParam() {
        try {
            Resource resource = ctx.getResource("classpath:groovy/ExampleAnalyserProcessor.groovy");
            String example;
            try (InputStream sin = resource.getInputStream();
                 StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn("读取文件 classpath:groovy/ExampleAnalyserProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Analyser makeAnalyser(String type, String param) throws AnalyserException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过Groovy脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(param);
            Processor processor = (Processor) aClass.newInstance();
            // 生成并返回分析器。
            return ctx.getBean(GroovyAnalyser.class, ctx, processor);
        } catch (Exception e) {
            throw new AnalyserMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "GroovyAnalyserRegistry{" +
                "ctx=" + ctx +
                ", analyserType='" + analyserType + '\'' +
                '}';
    }
}
