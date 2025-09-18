package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.judge.stack.handler.Provider;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 提供器 QoS 服务。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface ProviderQosService extends Service {

    /**
     * 判断指定的提供器是否存在。
     *
     * @param providerInfoKey 指定的提供器信息主键。
     * @return 指定的提供器是否存在。
     * @throws ServiceException 服务异常。
     */
    boolean exists(LongIdKey providerInfoKey) throws ServiceException;

    /**
     * 获取指定提供器的提供器描述。
     *
     * @param providerInfoKey 指定提供器的主键。
     * @return 指定提供器的提供器描述。
     * @throws ServiceException 服务异常。
     */
    ProviderDescription get(LongIdKey providerInfoKey) throws ServiceException;

    /**
     * 清除提供器本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;

    /**
     * 提供器描述。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    final class ProviderDescription {

        private final ProviderInfo providerInfo;
        private final Provider provider;

        public ProviderDescription(ProviderInfo providerInfo, Provider provider) {
            this.providerInfo = providerInfo;
            this.provider = provider;
        }

        public ProviderInfo getProviderInfo() {
            return providerInfo;
        }

        public Provider getProvider() {
            return provider;
        }

        @Override
        public String toString() {
            return "ProviderDescription{" +
                    "providerInfo=" + providerInfo +
                    ", provider=" + provider +
                    '}';
        }
    }
}
