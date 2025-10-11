package com.dwarfeng.judge.impl.handler.adapter.groovy;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.judge.sdk.handler.adapter.AbstractAdapterRegistry;
import com.dwarfeng.judge.stack.exception.AdapterException;
import com.dwarfeng.judge.stack.exception.AdapterMakeException;
import com.dwarfeng.judge.stack.handler.Adapter;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 模拟适配器注册。
 *
 * @author wangyc
 * @since 2.3.0
 */
@Component
public class GroovyAdapterRegistry extends AbstractAdapterRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyAdapterRegistry.class);

    public static final String ADAPTER_TYPE = "groovy";

    private final ApplicationContext ctx;

    public GroovyAdapterRegistry(ApplicationContext ctx) {
        super(ADAPTER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String adaptLabel() {
        return "Groovy 适配器";
    }

    @Override
    public String adaptDescription() {
        return "通过自定义的 groovy 脚本进行数据适配。";
    }

    @Override
    public String adaptExampleParam() {
        try {
            Resource resource = ctx.getResource("classpath:groovy/ExampleAdapterProcessor.groovy");
            String example;
            try (InputStream sin = resource.getInputStream();
                 StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn("读取文件 classpath:groovy/ExampleAdapterProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Adapter makeAdapter(String type, String param) throws AdapterException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过Groovy脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(param);
            Processor processor = (Processor) aClass.newInstance();
            // 生成并返回适配器。
            return ctx.getBean(GroovyAdapter.class, processor);
        } catch (Exception e) {
            throw new AdapterMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "GroovyAdapterRegistry{" +
                "AdapterType='" + AdapterType + '\'' +
                '}';
    }
}
