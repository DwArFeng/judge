package groovy

import com.dwarfeng.judge.impl.handler.visualizer.Processor
import com.dwarfeng.judge.stack.bean.dto.TaskEventCreateInfo
import com.dwarfeng.judge.stack.bean.dto.TaskUpdateModalInfo
import com.dwarfeng.judge.stack.bean.dto.VisualizeDataUpsertInfo
import com.dwarfeng.judge.stack.handler.Visualizer
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey

/**
 * 调用上下文的部分方法，以提供示例。
 *
 * <p>
 * 可视化方法的行为如下：
 * <ol>
 *     <li>更新任务模态，anchorMessage：groovy。</li>
 *     <li>插入任务事件，message：groovy。</li>
 *     <li>插入可视化数据，透视键：groovy，内容：UUID。</li>
 * </ol>
 *
 * @author Dwarfeng
 * @since 2.2.0
 */
@SuppressWarnings(['GrPackage', 'unused'])
class ExampleVisualizerProcessor implements Processor {

    @Override
    void analyse(Visualizer.Context context) throws Exception {
        // 获取任务键。
        LongIdKey taskKey = context.getTaskKey()

        // 更新任务模态，anchorMessage：groovy。
        context.updateTaskModal(new TaskUpdateModalInfo(taskKey, "groovy"))

        // 插入任务事件，message：groovy。
        context.createTaskEvent(new TaskEventCreateInfo(taskKey, "groovy"))

        // 插入可视化数据，透视键：groovy，内容：UUID。
        context.upsertVisualizeData(new VisualizeDataUpsertInfo(
                taskKey, "groovy.string", UUID.randomUUID().toString()
        ))
    }
}
