package com.dwarfeng.judge.impl.handler.judger;

import com.dwarfeng.judge.impl.handler.JudgerMaker;
import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.judge.stack.exception.JudgerMakeException;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.RepositoryHandler;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import groovy.lang.GroovyClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Groovy判断器生成器。
 *
 * @author DwArFeng
 * @since beta-1.0.1
 */
@Component
public class GroovyJudgerMaker implements JudgerMaker {

    public static final String SUPPORT_TYPE = "groovy_judger";

    @Autowired
    private ApplicationContext ctx;

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
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
        public JudgedValue judge(RepositoryHandler repositoryHandler) throws JudgerException {
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
         * <pre>数据信息有如下要求
         * 1. 主键是对应的判断器信息的主键。
         * 2. 发生时间是该数据的生成时间。
         * 3. 数据值是介于0.0-1.0之间的浮点数。
         * </pre>
         *
         * @param judgerInfoKey     判断器的主键。
         * @param repositoryHandler 指定的仓库处理器。
         * @return 判断值。
         * @throws JudgerException 判断异常。
         */
        JudgedValue judge(LongIdKey judgerInfoKey, RepositoryHandler repositoryHandler) throws JudgerException;
    }
}
