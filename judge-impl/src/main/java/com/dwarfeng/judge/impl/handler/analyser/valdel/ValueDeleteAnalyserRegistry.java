package com.dwarfeng.judge.impl.handler.analyser.valdel;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.handler.analyser.AbstractAnalyserRegistry;
import com.dwarfeng.judge.stack.exception.AnalyserException;
import com.dwarfeng.judge.stack.exception.AnalyserMakeException;
import com.dwarfeng.judge.stack.handler.Analyser;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 值删除分析器注册。
 *
 * <p>
 * 该注册器负责解析值删除分析器参数，并向 Spring 容器申请分析器实例。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
public class ValueDeleteAnalyserRegistry extends AbstractAnalyserRegistry {

    public static final String ANALYSER_TYPE = "value_delete_analyser";

    private final ApplicationContext ctx;

    public ValueDeleteAnalyserRegistry(ApplicationContext ctx) {
        super(ANALYSER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "值删除分析器";
    }

    @Override
    public String provideDescription() {
        return "按配置删除当前任务下指定 dataStringId 对应的 Analysis。";
    }

    @Override
    public String provideExampleParam() {
        ValueDeleteAnalyserConfig exampleConfig = new ValueDeleteAnalyserConfig("temp");
        return JSON.toJSONString(exampleConfig, true);
    }

    @Override
    public Analyser makeAnalyser(String type, String param) throws AnalyserException {
        try {
            ValueDeleteAnalyserConfig config = JSON.parseObject(param, ValueDeleteAnalyserConfig.class);
            if (config == null) {
                throw new IllegalArgumentException("值删除分析器配置不能为空");
            }
            return ctx.getBean(ValueDeleteAnalyser.class, ctx, config);
        } catch (Exception e) {
            throw new AnalyserMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "ValueDeleteAnalyserRegistry{" +
                "ctx=" + ctx +
                ", analyserType='" + analyserType + '\'' +
                '}';
    }
}
