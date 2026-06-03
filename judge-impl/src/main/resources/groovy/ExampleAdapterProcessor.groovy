package groovy

import com.dwarfeng.judge.impl.handler.adapter.groovy.Processor
import com.dwarfeng.judge.stack.handler.Adapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Groovy Example。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.3.0
 */
@SuppressWarnings(['GrPackage', 'unused'])
class ExampleAdapterProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleAdapterProcessor.class)

    @Override
    Adapter.AdaptResult adapt(Adapter.AdaptInfo info) throws Exception {
        LOGGER.debug("数据适配...")
        return new Adapter.AdaptResult(info.getPreset(), info.getObjs())
    }
}
