package com.dwarfeng.judge.impl.handler.analyser.valllstset;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.handler.analyser.AbstractAnalyserRegistry;
import com.dwarfeng.judge.stack.exception.AnalyserException;
import com.dwarfeng.judge.stack.exception.AnalyserMakeException;
import com.dwarfeng.judge.stack.handler.Analyser;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 值查询列表设置分析器注册。
 *
 * <p>
 * 该注册器负责解析值查询列表设置分析器参数，并向 Spring 容器申请分析器实例。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
public class ValueLookupListSetAnalyserRegistry extends AbstractAnalyserRegistry {

    public static final String ANALYSER_TYPE = "value_lookup_list_set_analyser";

    private final ApplicationContext ctx;

    public ValueLookupListSetAnalyserRegistry(ApplicationContext ctx) {
        super(ANALYSER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "值查询列表设置分析器";
    }

    @Override
    public String provideDescription() {
        return "执行一次 lookup 查询，从 datas 中提取字段列表并序列化为字符串或文件类型 Analysis。";
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("ExtractMethodRecommender")
    @Override
    public String provideExampleParam() {
        ValueLookupListSetAnalyserConfig.LookupObjectConfig pointObject =
                new ValueLookupListSetAnalyserConfig.LookupObjectConfig(
                        "constant", "string", "point-001", null
                );
        ValueLookupListSetAnalyserConfig.LookupObjectConfig startTimeObject =
                new ValueLookupListSetAnalyserConfig.LookupObjectConfig(
                        "analysis", null, null, "query.start_time"
                );
        ValueLookupListSetAnalyserConfig.LookupObjectConfig endTimeObject =
                new ValueLookupListSetAnalyserConfig.LookupObjectConfig(
                        "analysis", null, null, "query.end_time"
                );
        ValueLookupListSetAnalyserConfig.LookupConfig lookupConfig =
                new ValueLookupListSetAnalyserConfig.LookupConfig(
                        1001L, null, null, "range_query",
                        Arrays.asList(pointObject, startTimeObject, endTimeObject)
                );
        ValueLookupListSetAnalyserConfig.CsvConfig humidityCsvConfig =
                new ValueLookupListSetAnalyserConfig.CsvConfig(true, "humidity", "UTF-8", "LF");
        ValueLookupListSetAnalyserConfig.OutputConfig temperatureOutput =
                new ValueLookupListSetAnalyserConfig.OutputConfig(
                        "temperature.list.json", "string", "temperature", "json_array", null, null
                );
        ValueLookupListSetAnalyserConfig.OutputConfig humidityOutput =
                new ValueLookupListSetAnalyserConfig.OutputConfig(
                        "humidity.list.csv", "file", "humidity", "csv", "humidity.csv", humidityCsvConfig
                );
        ValueLookupListSetAnalyserConfig.OutputConfig statusOutput =
                new ValueLookupListSetAnalyserConfig.OutputConfig(
                        "status.list.json", "file", "status", "json_array", "status.json", null
                );
        ValueLookupListSetAnalyserConfig exampleConfig = new ValueLookupListSetAnalyserConfig(
                lookupConfig,
                Arrays.asList(temperatureOutput, humidityOutput, statusOutput)
        );
        return JSON.toJSONString(exampleConfig, true);
    }

    @Override
    public Analyser makeAnalyser(String type, String param) throws AnalyserException {
        try {
            ValueLookupListSetAnalyserConfig config = JSON.parseObject(
                    param, ValueLookupListSetAnalyserConfig.class
            );
            if (config == null) {
                throw new IllegalArgumentException("值查询列表设置分析器配置不能为空");
            }
            return ctx.getBean(ValueLookupListSetAnalyser.class, ctx, config);
        } catch (Exception e) {
            throw new AnalyserMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "ValueLookupListSetAnalyserRegistry{" +
                "ctx=" + ctx +
                ", analyserType='" + analyserType + '\'' +
                '}';
    }
}
