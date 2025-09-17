package com.dwarfeng.judge.impl.handler.judger.groovy;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.judge.sdk.handler.judger.AbstractJudgerRegistry;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.exception.JudgerMakeException;
import com.dwarfeng.judge.stack.handler.Judger;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Groovy 判断器注册。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Component
public class GroovyJudgerRegistry extends AbstractJudgerRegistry {

    public static final String JUDGER_TYPE = "groovy_judger";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyJudgerRegistry.class);

    private final ApplicationContext ctx;

    public GroovyJudgerRegistry(ApplicationContext ctx) {
        super(JUDGER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "Groovy 判断器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的 groovy 脚本进行数据判断。";
    }

    @Override
    public String provideExampleParam() {
        try {
            Resource resource = ctx.getResource("classpath:groovy/ExampleJudgerProcessor.groovy");
            String example;
            try (InputStream sin = resource.getInputStream();
                 StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn("读取文件 classpath:groovy/ExampleJudgerProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Judger makeJudger(String type, String param) throws JudgerException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过Groovy脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(param);
            Processor processor = (Processor) aClass.newInstance();
            // 生成并返回判断器。
            return ctx.getBean(GroovyJudger.class, ctx, processor);
        } catch (Exception e) {
            throw new JudgerMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "GroovyJudgerRegistry{" +
                "ctx=" + ctx +
                ", judgerType='" + judgerType + '\'' +
                '}';
    }
}
