package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.judge.stack.handler.Provider;
import com.dwarfeng.judge.stack.handler.ProviderSession;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 提供器会话 QoS 服务。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface ProviderSessionQosService extends Service {

    /**
     * 判断指定的提供器会话是否存在。
     *
     * @param providerInfoKey 指定的提供器信息主键。
     * @return 指定的提供器会话是否存在。
     * @throws ServiceException 服务异常。
     */
    boolean exists(LongIdKey providerInfoKey) throws ServiceException;

    /**
     * 获取指定提供器的提供器会话描述。
     *
     * <p>
     * 如果指定的提供器存在，但提供器会话不存在，则尝试基于提供器信息进行创建；
     * 如果提供器信息不存在，则返回 <code>null</code>。
     *
     * @param providerInfoKey 指定的提供器信息主键。
     * @return 指定的提供器会话描述。
     * @throws ServiceException 服务异常。
     */
    ProviderSessionDescription get(LongIdKey providerInfoKey) throws ServiceException;

    /**
     * 关闭并清除所有的提供器会话。
     *
     * <p>
     * 该方法会关闭所有当前持有的提供器会话，并清除所有的提供器会话。
     *
     * @throws ServiceException 服务异常。
     */
    void closeAndClearHolding() throws ServiceException;

    /**
     * 提供器会话描述。
     *
     * <p>
     * 该类用于描述一个提供器会话，包括提供器信息和提供器会话本身。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    final class ProviderSessionDescription {

        private final ProviderInfo providerInfo;
        private final Provider provider;
        private final ProviderSession providerSession;

        public ProviderSessionDescription(ProviderInfo providerInfo, Provider provider, ProviderSession providerSession) {
            this.providerInfo = providerInfo;
            this.provider = provider;
            this.providerSession = providerSession;
        }

        public ProviderInfo getProviderInfo() {
            return providerInfo;
        }

        public Provider getProvider() {
            return provider;
        }

        public ProviderSession getProviderSession() {
            return providerSession;
        }

        @Override
        public String toString() {
            return "ProviderSessionDescription{" +
                    "providerInfo=" + providerInfo +
                    ", provider=" + provider +
                    ", providerSession=" + providerSession +
                    '}';
        }
    }
}
