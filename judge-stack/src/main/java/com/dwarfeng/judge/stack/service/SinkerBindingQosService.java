package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.struct.SinkerBinding;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 下沉器绑定 QoS 服务。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerBindingQosService extends Service {

    /**
     * 获取指定下沉器信息对应的下沉器绑定。
     *
     * @param sinkerInfoKey 指定的下沉器信息的键。
     * @return 指定下沉器信息的下沉器绑定，或者是 <code>null</code>。
     * @throws ServiceException 服务异常。
     */
    SinkerBinding getSinkerBinding(LongIdKey sinkerInfoKey) throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * <p>
     * 注意：该方法会清除本地缓存中的所有下沉器绑定信息。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
