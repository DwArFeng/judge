package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.handler.Sinker;
import com.dwarfeng.judge.stack.handler.SinkerSession;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 下沉器会话 QoS 服务。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerSessionQosService extends Service {

    /**
     * 判断指定的下沉器会话是否存在。
     *
     * @param sinkerInfoKey 指定的下沉器信息主键。
     * @return 指定的下沉器会话是否存在。
     * @throws ServiceException 服务异常。
     */
    boolean exists(LongIdKey sinkerInfoKey) throws ServiceException;

    /**
     * 获取指定下沉器的下沉器会话描述。
     *
     * <p>
     * 如果指定的下沉器存在，但下沉器会话不存在，则尝试基于下沉器信息进行创建；
     * 如果下沉器信息不存在，则返回 <code>null</code>。
     *
     * @param sinkerInfoKey 指定的下沉器信息主键。
     * @return 指定的下沉器会话描述。
     * @throws ServiceException 服务异常。
     */
    SinkerSessionDescription get(LongIdKey sinkerInfoKey) throws ServiceException;

    /**
     * 关闭并清除所有的下沉器会话。
     *
     * <p>
     * 该方法会关闭所有当前持有的下沉器会话，并清除所有的下沉器会话。
     *
     * @throws ServiceException 服务异常。
     */
    void closeAndClearHolding() throws ServiceException;

    /**
     * 下沉器会话描述。
     *
     * <p>
     * 该类用于描述一个下沉器会话，包括下沉器信息和下沉器会话本身。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    final class SinkerSessionDescription {

        private final SinkerInfo sinkerInfo;
        private final Sinker sinker;
        private final SinkerSession sinkerSession;

        public SinkerSessionDescription(SinkerInfo sinkerInfo, Sinker sinker, SinkerSession sinkerSession) {
            this.sinkerInfo = sinkerInfo;
            this.sinker = sinker;
            this.sinkerSession = sinkerSession;
        }

        public SinkerInfo getSinkerInfo() {
            return sinkerInfo;
        }

        public Sinker getSinker() {
            return sinker;
        }

        public SinkerSession getSinkerSession() {
            return sinkerSession;
        }

        @Override
        public String toString() {
            return "SinkerSessionDescription{" +
                    "sinkerInfo=" + sinkerInfo +
                    ", sinker=" + sinker +
                    ", sinkerSession=" + sinkerSession +
                    '}';
        }
    }
}
