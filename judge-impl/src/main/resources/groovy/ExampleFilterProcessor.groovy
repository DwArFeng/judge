package groovy

import com.dwarfeng.judge.impl.handler.filter.groovy.Processor
import com.dwarfeng.judge.stack.handler.Filter
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
class ExampleFilterProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleFilterProcessor.class)

    @Override
    Filter.FilterResult filter(Filter.FilterInfo info) throws Exception {
        LOGGER.debug("数据过滤...")
        return new Filter.FilterResult(info.getDatas(), info.getMeta())
    }
}
