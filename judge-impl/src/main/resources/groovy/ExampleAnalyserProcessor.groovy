package groovy

import com.dwarfeng.judge.impl.handler.analyser.groovy.Processor
import com.dwarfeng.judge.sdk.util.Constants
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableUpsertInfo
import com.dwarfeng.judge.stack.bean.dto.AnalysisUpsertInfo
import com.dwarfeng.judge.stack.bean.dto.TaskEventCreateInfo
import com.dwarfeng.judge.stack.bean.dto.TaskUpdateModalInfo
import com.dwarfeng.judge.stack.handler.Analyser
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey

/**
 * 调用上下文的部分方法，以提供示例。
 *
 * <p>
 * 分析方法的行为如下：
 * <ol>
 *     <li>向分析器变量中写入值，键：groovy，值：UUID。</li>
 *     <li>更新任务模态，anchorMessage：groovy。</li>
 *     <li>插入任务事件，message：groovy。</li>
 *     <li>插入分析结果，键：groovy.string，值：UUID。</li>
 *     <li>插入分析结果，键：groovy.long，值：随机长整型。</li>
 *     <li>插入分析结果，键：groovy.double，值：随机双精度浮点型。</li>
 *     <li>插入分析结果，键：groovy.boolean，值：随机布尔型。</li>
 *     <li>插入分析结果，键：groovy.date，值：当前时间。</li>
 * </ol>
 *
 * @author Dwarfeng
 * @since 2.0.0-beta
 */
@SuppressWarnings(['GrPackage', 'unused'])
class ExampleAnalyserProcessor implements Processor {

    @Override
    void analyse(Analyser.Context context) throws Exception {
        // 获取分析器信息键。
        LongIdKey analyserInfoKey = context.getAnalyserInfoKey()

        // 向分析器变量中写入值，键：groovy，值：UUID。
        context.upsertAnalyserVariable(
                new AnalyserVariableUpsertInfo(analyserInfoKey, "groovy", UUID.randomUUID().toString())
        )

        // 获取任务键。
        LongIdKey taskKey = context.getTaskKey()

        // 更新任务模态，anchorMessage：groovy。
        context.updateTaskModal(new TaskUpdateModalInfo(taskKey, "groovy"))

        // 插入任务事件，message：groovy。
        context.createTaskEvent(new TaskEventCreateInfo(taskKey, "groovy"))

        // 插入分析结果，键：groovy.string，值：UUID。
        context.upsertAnalysis(new AnalysisUpsertInfo(
                taskKey, "groovy.string", Constants.ANALYSIS_TYPE_STRING, UUID.randomUUID().toString()
        ))

        // 插入分析结果，键：groovy.long，值：随机长整型。
        context.upsertAnalysis(new AnalysisUpsertInfo(
                taskKey, "groovy.long", Constants.ANALYSIS_TYPE_LONG, new Random().nextLong()
        ))

        // 插入分析结果，键：groovy.double，值：随机双精度浮点型。
        context.upsertAnalysis(new AnalysisUpsertInfo(
                taskKey, "groovy.double", Constants.ANALYSIS_TYPE_DOUBLE, new Random().nextDouble()
        ))

        // 插入分析结果，键：groovy.boolean，值：随机布尔型。
        context.upsertAnalysis(new AnalysisUpsertInfo(
                taskKey, "groovy.boolean", Constants.ANALYSIS_TYPE_BOOLEAN, new Random().nextBoolean()
        ))

        // 插入分析结果，键：groovy.date，值：当前时间。
        context.upsertAnalysis(new AnalysisUpsertInfo(
                taskKey, "groovy.date", Constants.ANALYSIS_TYPE_DATE, new Date()
        ))
    }
}
