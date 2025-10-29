package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.FilterInfo;
import com.dwarfeng.judge.stack.handler.Filter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 过滤器 QoS 服务。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface FilterQosService extends Service {

    /**
     * 判断指定的过滤器是否存在。
     *
     * @param FilterInfoKey 指定的过滤器信息主键。
     * @return 指定的过滤器是否存在。
     * @throws ServiceException 服务异常。
     */
    boolean exists(LongIdKey FilterInfoKey) throws ServiceException;

    /**
     * 获取指定过滤器的过滤器描述。
     *
     * @param FilterInfoKey 指定过滤器的主键。
     * @return 指定过滤器的过滤器描述。
     * @throws ServiceException 服务异常。
     */
    FilterDescription get(LongIdKey FilterInfoKey) throws ServiceException;

    /**
     * 清除过滤器本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;

    /**
     * 过滤器描述。
     *
     * @author wangyc
     * @since 2.3.0
     */
    final class FilterDescription {

        private final FilterInfo filterInfo;
        private final Filter filter;

        public FilterDescription(FilterInfo filterInfo, Filter filter) {
            this.filterInfo = filterInfo;
            this.filter = filter;
        }

        public FilterInfo getFilterInfo() {
            return filterInfo;
        }

        public Filter getFilter() {
            return filter;
        }

        @Override
        public String toString() {
            return "FilterDescription{" +
                    "filterInfo=" + filterInfo +
                    ", filter=" + filter +
                    '}';
        }
    }
}
