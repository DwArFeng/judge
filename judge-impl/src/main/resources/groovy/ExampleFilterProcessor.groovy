package groovy

import com.dwarfeng.judge.impl.handler.filter.groovy.Processor
import com.dwarfeng.judge.stack.bean.dto.LookupResult
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Groovy Example。
 *
 * @author wangyc
 * @since 2.3.0
 */
@SuppressWarnings(['GrPackage', 'unused'])
class ExampleFilterProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleFilterProcessor.class)

    @Override
    LookupResult filter(LookupResult lookupResult) throws Exception {
        LOGGER.debug("数据过滤...")
        return lookupResult
    }
}
