package com.dwarfeng.judge.impl.handler.analyser.vallbatset;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.handler.analyser.AbstractAnalyserRegistry;
import com.dwarfeng.judge.stack.exception.AnalyserException;
import com.dwarfeng.judge.stack.exception.AnalyserMakeException;
import com.dwarfeng.judge.stack.handler.Analyser;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 值查询批量设置分析器注册。
 *
 * <p>
 * 该注册器负责解析值查询批量设置分析器参数，并向 Spring 容器申请分析器实例。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
public class ValueLookupBatchSetAnalyserRegistry extends AbstractAnalyserRegistry {

    public static final String ANALYSER_TYPE = "value_lookup_batch_set_analyser";

    private final ApplicationContext ctx;

    public ValueLookupBatchSetAnalyserRegistry(ApplicationContext ctx) {
        super(ANALYSER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "值查询批量设置分析器";
    }

    @Override
    public String provideDescription() {
        return "执行一次 lookup 查询，并将查询结果按 outputs 配置批量转换后写入多个 Analysis。";
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("ExtractMethodRecommender")
    @Override
    public String provideExampleParam() {
        ValueLookupBatchSetAnalyserConfig.LookupObjectConfig lookupObjectConstant =
                new ValueLookupBatchSetAnalyserConfig.LookupObjectConfig(
                        "constant", "string", "point-001", null
                );
        ValueLookupBatchSetAnalyserConfig.LookupObjectConfig lookupObjectAnalysis =
                new ValueLookupBatchSetAnalyserConfig.LookupObjectConfig(
                        "analysis", null, null, "start_time"
                );
        ValueLookupBatchSetAnalyserConfig.LookupConfig lookupConfig =
                new ValueLookupBatchSetAnalyserConfig.LookupConfig(
                        1001L, null, null, "latest",
                        Arrays.asList(lookupObjectConstant, lookupObjectAnalysis)
                );
        ValueLookupBatchSetAnalyserConfig.LookupResultConfig temperatureResult =
                new ValueLookupBatchSetAnalyserConfig.LookupResultConfig("datas", 0, "temperature");
        ValueLookupBatchSetAnalyserConfig.LookupResultConfig humidityResult =
                new ValueLookupBatchSetAnalyserConfig.LookupResultConfig("datas", 0, "humidity");
        ValueLookupBatchSetAnalyserConfig.LookupResultConfig totalResult =
                new ValueLookupBatchSetAnalyserConfig.LookupResultConfig("meta", null, "total");
        ValueLookupBatchSetAnalyserConfig.OutputConfig temperatureOutput =
                new ValueLookupBatchSetAnalyserConfig.OutputConfig(
                        "temperature.current", "double", temperatureResult
                );
        ValueLookupBatchSetAnalyserConfig.OutputConfig humidityOutput =
                new ValueLookupBatchSetAnalyserConfig.OutputConfig(
                        "humidity.current", "double", humidityResult
                );
        ValueLookupBatchSetAnalyserConfig.OutputConfig totalOutput =
                new ValueLookupBatchSetAnalyserConfig.OutputConfig(
                        "lookup.total", "long", totalResult
                );
        ValueLookupBatchSetAnalyserConfig exampleConfig = new ValueLookupBatchSetAnalyserConfig(
                lookupConfig,
                Arrays.asList(temperatureOutput, humidityOutput, totalOutput)
        );
        return JSON.toJSONString(exampleConfig, true);
    }

    @Override
    public Analyser makeAnalyser(String type, String param) throws AnalyserException {
        try {
            ValueLookupBatchSetAnalyserConfig config = JSON.parseObject(
                    param, ValueLookupBatchSetAnalyserConfig.class
            );
            if (config == null) {
                throw new IllegalArgumentException("值查询批量设置分析器配置不能为空");
            }
            return ctx.getBean(ValueLookupBatchSetAnalyser.class, ctx, config);
        } catch (Exception e) {
            throw new AnalyserMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "ValueLookupBatchSetAnalyserRegistry{" +
                "ctx=" + ctx +
                ", analyserType='" + analyserType + '\'' +
                '}';
    }
}
