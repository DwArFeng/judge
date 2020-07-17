package com.dwarfeng.judge.impl.handler.judger;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.judge.stack.bean.dto.JudgerResult;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.exception.JudgerMakeException;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.RepositoryHandler;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Groovy判断器注册。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
@Component
public class GroovyJudgerRegistry extends AbstractJudgerRegistry {

    public static final String JUDGER_TYPE = "groovy_judger";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyJudgerRegistry.class);

    @Autowired
    private ApplicationContext ctx;

    public GroovyJudgerRegistry() {
        super(JUDGER_TYPE);
    }

    @Override
    public String provideLabel() {
        return "Groovy判断器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的Groovy脚本对数据进行判断。";
    }

    @Override
    public String provideExampleContent() {
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
            LOGGER.warn("读取文件 classpath:groovy/ExampleFilterProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Judger makeJudger(JudgerInfo judgerInfo) throws JudgerException {
        try {
            // 通过Groovy脚本生成处理器。
            GroovyClassLoader classLoader = new GroovyClassLoader();
            Class<?> aClass = classLoader.parseClass(judgerInfo.getContent());
            Processor processor = (Processor) aClass.newInstance();
            // 构建过滤器对象。
            GroovyJudger judger = ctx.getBean(GroovyJudger.class);
            judger.setJudgerInfoKey(judgerInfo.getKey());
            judger.setProcessor(processor);
            return judger;
        } catch (Exception e) {
            throw new JudgerMakeException(e);
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class GroovyJudger implements Judger {

        private LongIdKey judgerInfoKey;
        private Processor processor;

        public GroovyJudger() {
        }

        @Override
        public JudgerResult judge(RepositoryHandler repositoryHandler) throws JudgerException {
            try {
                return processor.judge(judgerInfoKey, repositoryHandler);
            } catch (JudgerException e) {
                throw e;
            } catch (Exception e) {
                throw new JudgerException(e);
            }
        }

        public LongIdKey getJudgerInfoKey() {
            return judgerInfoKey;
        }

        public void setJudgerInfoKey(LongIdKey judgerInfoKey) {
            this.judgerInfoKey = judgerInfoKey;
        }

        public Processor getProcessor() {
            return processor;
        }

        public void setProcessor(Processor processor) {
            this.processor = processor;
        }

        @Override
        public String toString() {
            return "GroovyJudger{" +
                    "judgerInfoKey=" + judgerInfoKey +
                    ", processor=" + processor +
                    '}';
        }
    }

    /**
     * Groovy处理器。
     *
     * @author DwArFeng
     * @since 1.0.1
     */
    public interface Processor {

        /**
         * 对仓库处理器中的数据做出判断，并生成判断值。
         *
         * @param judgerInfoKey     判断器的主键。
         * @param repositoryHandler 指定的仓库处理器。
         * @return 判断值。
         * @throws JudgerException 判断异常。
         */
        JudgerResult judge(LongIdKey judgerInfoKey, RepositoryHandler repositoryHandler) throws JudgerException;
    }
}
