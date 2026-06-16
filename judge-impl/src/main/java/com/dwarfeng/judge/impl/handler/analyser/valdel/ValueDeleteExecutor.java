package com.dwarfeng.judge.impl.handler.analyser.valdel;

import com.dwarfeng.judge.sdk.handler.analyser.AbstractExecutor;
import com.dwarfeng.judge.stack.bean.dto.AnalysisRemoveInfo;
import com.dwarfeng.judge.stack.exception.AnalyserExecutionException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 值删除分析器执行器。
 *
 * <p>
 * 执行器读取 <code>delete_analysis_data_id</code>，构造 <code>AnalysisRemoveInfo</code>，
 * 并通过分析器上下文删除当前任务下对应的 <code>Analysis</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("valueDeleteAnalyserRegistry.valueDeleteExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ValueDeleteExecutor extends AbstractExecutor {

    private final ValueDeleteAnalyserConfig config;

    public ValueDeleteExecutor(ValueDeleteAnalyserConfig config) {
        this.config = config;
    }

    @Override
    public void analyse() throws Exception {
        checkRequired("delete_analysis_data_id", config.getDeleteAnalysisDataId());
        context.removeAnalysis(new AnalysisRemoveInfo(
                context.getTaskKey(), config.getDeleteAnalysisDataId()
        ));
    }

    /**
     * 校验必填字符串配置项。
     *
     * @param fieldName 配置项名称，用于异常定位。
     * @param value     配置值。
     * @throws AnalyserExecutionException 配置缺失、空字符串或全空白字符串时抛出。
     */
    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("SameParameterValue")
    private static void checkRequired(String fieldName, String value) throws AnalyserExecutionException {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new AnalyserExecutionException("值删除分析器配置项 " + fieldName + " 不能为空");
        }
    }

    @Override
    public String toString() {
        return "ValueDeleteExecutor{" +
                "config=" + config +
                ", context=" + context +
                '}';
    }
}
