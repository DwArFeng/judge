package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AdapterInfo;
import com.dwarfeng.judge.stack.handler.Adapter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 适配器 QoS 服务。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface AdapterQosService extends Service {

    /**
     * 判断指定的适配器是否存在。
     *
     * @param AdapterInfoKey 指定的适配器信息主键。
     * @return 指定的适配器是否存在。
     * @throws ServiceException 服务异常。
     */
    boolean exists(LongIdKey AdapterInfoKey) throws ServiceException;

    /**
     * 获取指定适配器的适配器描述。
     *
     * @param AdapterInfoKey 指定适配器的主键。
     * @return 指定适配器的适配器描述。
     * @throws ServiceException 服务异常。
     */
    AdapterDescription get(LongIdKey AdapterInfoKey) throws ServiceException;

    /**
     * 清除适配器本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;

    /**
     * 适配器描述。
     *
     * @author wangyc
     * @since 2.3.0
     */
    final class AdapterDescription {

        private final AdapterInfo adapterInfo;
        private final Adapter adapter;

        public AdapterDescription(AdapterInfo adapterInfo, Adapter adapter) {
            this.adapterInfo = adapterInfo;
            this.adapter = adapter;
        }

        public AdapterInfo getAdapterInfo() {
            return adapterInfo;
        }

        public Adapter getAdapter() {
            return adapter;
        }

        @Override
        public String toString() {
            return "AdapterDescription{" +
                    "adapterInfo=" + adapterInfo +
                    ", adapter=" + adapter +
                    '}';
        }
    }
}
