package com.dwarfeng.judge.impl.handler.analyser.valset;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.handler.analyser.AbstractAnalyserRegistry;
import com.dwarfeng.judge.stack.exception.AnalyserException;
import com.dwarfeng.judge.stack.exception.AnalyserMakeException;
import com.dwarfeng.judge.stack.handler.Analyser;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 值设置分析器注册。
 *
 * <p>
 * 该注册器负责解析值设置分析器参数，并向 Spring 容器申请分析器实例。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
public class ValueSetAnalyserRegistry extends AbstractAnalyserRegistry {

    public static final String ANALYSER_TYPE = "value_set_analyser";

    private final ApplicationContext ctx;

    public ValueSetAnalyserRegistry(ApplicationContext ctx) {
        super(ANALYSER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "值设置分析器";
    }

    @Override
    public String provideDescription() {
        return "将常量、已有分析结果或 lookup 查询结果转换为指定分析值类型并写入目标 Analysis。";
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("ExtractMethodRecommender")
    @Override
    public String provideExampleParam() {
        ValueSetAnalyserConfig constantConfig = new ValueSetAnalyserConfig(
                "example.constant", "double", "constant",
                "double", "12.5", null, null, null
        );
        ValueSetAnalyserConfig analysisConfig = new ValueSetAnalyserConfig(
                "example.analysis", "string", "analysis",
                null, null, "example.source", null, null
        );
        ValueSetAnalyserConfig.LookupObjectConfig lookupObjectConstant = new ValueSetAnalyserConfig.LookupObjectConfig(
                "constant", "string", "point-001", null
        );
        ValueSetAnalyserConfig.LookupObjectConfig lookupObjectAnalysis = new ValueSetAnalyserConfig.LookupObjectConfig(
                "analysis", null, null, "start_time"
        );
        ValueSetAnalyserConfig.LookupConfig lookupConfig = new ValueSetAnalyserConfig.LookupConfig(
                1001L, null, null, "latest",
                Arrays.asList(lookupObjectConstant, lookupObjectAnalysis)
        );
        ValueSetAnalyserConfig.LookupResultConfig lookupResultConfig =
                new ValueSetAnalyserConfig.LookupResultConfig("datas", 0, "value");
        ValueSetAnalyserConfig lookupExampleConfig = new ValueSetAnalyserConfig(
                "target_value", "double", "lookup",
                null, null, null, lookupConfig, lookupResultConfig
        );
        return JSON.toJSONString(lookupExampleConfig, true) +
                "\n\n// constant 示例\n" +
                JSON.toJSONString(constantConfig, true) +
                "\n\n// analysis 示例\n" +
                JSON.toJSONString(analysisConfig, true);
    }

    @Override
    public Analyser makeAnalyser(String type, String param) throws AnalyserException {
        try {
            ValueSetAnalyserConfig config = JSON.parseObject(param, ValueSetAnalyserConfig.class);
            if (config == null) {
                throw new IllegalArgumentException("值设置分析器配置不能为空");
            }
            return ctx.getBean(ValueSetAnalyser.class, ctx, config);
        } catch (Exception e) {
            throw new AnalyserMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "ValueSetAnalyserRegistry{" +
                "ctx=" + ctx +
                ", analyserType='" + analyserType + '\'' +
                '}';
    }
}
