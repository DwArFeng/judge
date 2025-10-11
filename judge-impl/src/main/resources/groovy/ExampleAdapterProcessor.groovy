package groovy

import com.dwarfeng.judge.impl.handler.adapter.groovy.Processor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Groovy Example。
 *
 * @author wangyc
 * @since 2.3.0
 */
@SuppressWarnings(['GrPackage', 'unused'])
class ExampleAdapterProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleAdapterProcessor.class)

    @Override
    Object[] adapt(Object[] args) throws Exception {
        LOGGER.debug("数据适配...")
        return args
    }
}
