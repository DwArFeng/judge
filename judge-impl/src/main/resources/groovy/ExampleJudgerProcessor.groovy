package groovy

import com.dwarfeng.judge.impl.handler.judger.groovy.Processor
import com.dwarfeng.judge.stack.bean.dto.JudgementUpsertInfo
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableUpsertInfo
import com.dwarfeng.judge.stack.bean.dto.TaskEventCreateInfo
import com.dwarfeng.judge.stack.bean.dto.TaskUpdateModalInfo
import com.dwarfeng.judge.stack.handler.Judger
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey

/**
 * 调用上下文的部分方法，以提供示例。
 *
 * <p>
 * 判断方法的行为如下：
 * <ol>
 *     <li>向判断器变量中写入值，键：groovy，值：UUID。</li>
 *     <li>更新任务模态，anchorMessage：groovy。</li>
 *     <li>插入任务事件，message：groovy。</li>
 *     <li>插入判断结果，键：groovy，判断值：介于 [0.0, 1.0] 之间的随机双精度浮点数，判断消息: 示例消息。</li>
 * </ol>
 *
 * @author Dwarfeng
 * @since 2.0.0-beta
 */
@SuppressWarnings(['GrPackage', 'unused'])
class ExampleJudgerProcessor implements Processor {

    @Override
    void judge(Judger.Context context) throws Exception {
        // 获取判断器信息键。
        LongIdKey judgerInfoKey = context.getJudgerInfoKey()

        // 向判断器变量中写入值，键：groovy，值：UUID。
        context.upsertJudgerVariable(
                new JudgerVariableUpsertInfo(judgerInfoKey, "groovy", UUID.randomUUID().toString())
        )

        // 获取任务键。
        LongIdKey taskKey = context.getTaskKey()

        // 更新任务模态，anchorMessage：groovy。
        context.updateTaskModal(new TaskUpdateModalInfo(taskKey, "groovy"))

        // 插入任务事件，message：groovy。
        context.createTaskEvent(new TaskEventCreateInfo(taskKey, "groovy"))

        // 插入判断结果，键：groovy，判断值：符合标准高斯分布的随机数，判断消息: 符合标准高斯分布的随机数。
        context.upsertJudgement(new JudgementUpsertInfo(
                taskKey, "groovy", new Random().nextDouble(), "示例消息"
        ))
    }
}
