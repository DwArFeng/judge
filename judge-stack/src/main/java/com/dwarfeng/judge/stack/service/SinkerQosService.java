package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.handler.Sinker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 下沉器 QoS 服务。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerQosService extends Service {

    /**
     * 判断指定的下沉器是否存在。
     *
     * @param sinkerInfoKey 指定的下沉器信息主键。
     * @return 指定的下沉器是否存在。
     * @throws ServiceException 服务异常。
     */
    boolean exists(LongIdKey sinkerInfoKey) throws ServiceException;

    /**
     * 获取指定下沉器的下沉器描述。
     *
     * @param sinkerInfoKey 指定下沉器的主键。
     * @return 指定下沉器的下沉器描述。
     * @throws ServiceException 服务异常。
     */
    SinkerDescription get(LongIdKey sinkerInfoKey) throws ServiceException;

    /**
     * 清除下沉器本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;

    /**
     * 下沉器描述。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    final class SinkerDescription {

        private final SinkerInfo sinkerInfo;
        private final Sinker sinker;

        public SinkerDescription(SinkerInfo sinkerInfo, Sinker sinker) {
            this.sinkerInfo = sinkerInfo;
            this.sinker = sinker;
        }

        public SinkerInfo getSinkerInfo() {
            return sinkerInfo;
        }

        public Sinker getSinker() {
            return sinker;
        }

        @Override
        public String toString() {
            return "SinkerDescription{" +
                    "sinkerInfo=" + sinkerInfo +
                    ", sinker=" + sinker +
                    '}';
        }
    }
}
