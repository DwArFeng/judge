package groovy

import com.dwarfeng.judge.impl.handler.judger.groovy.Processor
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableUpsertInfo
import com.dwarfeng.judge.stack.handler.Judger
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey

/**
 * 调用上下文的部分方法，以提供示例。
 *
 * <p>
 * 判断方法的行为如下：
 * <ol>
 *     <li>向判断器变量中写入值，键：groovy，值：UUID。</li>
 *     <li>返回判断结果，值使用符合标准高斯分布的随机数。</li>
 * </ol>
 *
 * @author Dwarfeng
 * @since 2.0.0
 */
@SuppressWarnings(['GrPackage', 'unused'])
class ExampleJudgerProcessor implements Processor {

    @Override
    Judger.JudgeResult judge(Judger.Context context) throws Exception {
        // 获取判断器信息键。
        LongIdKey judgerInfoKey = context.getJudgerInfoKey()

        // 向判断器变量中写入值，键：groovy，值：UUID。
        context.upsertJudgerVariable(
                new JudgerVariableUpsertInfo(judgerInfoKey, "groovy", UUID.randomUUID().toString())
        )

        // 返回判断结果，值使用符合标准高斯分布的随机数。
        return new Judger.JudgeResult(new Random().nextGaussian(), "合标准高斯分布的随机数")
    }
}
