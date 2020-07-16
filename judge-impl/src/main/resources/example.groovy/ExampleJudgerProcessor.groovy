import com.dwarfeng.judge.impl.handler.judger.GroovyJudgerMaker
import com.dwarfeng.judge.stack.bean.dto.JudgerResult
import com.dwarfeng.judge.stack.exception.JudgerException
import com.dwarfeng.judge.stack.handler.RepositoryHandler
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey

/**
 * 通过判启动判断时的系统时间对数据做出判断的脚本。
 * <p> 取判断启动的系统时间，取模1000，得出的数据除以1000，得到介于0.0和1.0之间的数。
 */
@SuppressWarnings("GrPackage")
class ExampleJudgerProcessor implements GroovyJudgerMaker.Processor {

    @Override
    JudgerResult judge(LongIdKey judgerInfoKey, RepositoryHandler repositoryHandler) throws JudgerException {
        def happenedDate = new Date()
        def value = (happenedDate.getTime() % 1000) / 1000
        return new JudgerResult(value, "通过计算得到的结果: " + value, Long.toString(happenedDate.getTime()))
    }
}
