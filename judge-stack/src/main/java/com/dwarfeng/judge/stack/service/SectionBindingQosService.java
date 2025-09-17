package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.struct.SectionBinding;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 部件器绑定 QoS 服务。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SectionBindingQosService extends Service {

    /**
     * 获取指定部件器信息对应的部件器绑定。
     *
     * @param sectionInfoKey 指定的部件器信息的键。
     * @return 指定部件器信息的部件器绑定，或者是 <code>null</code>。
     * @throws ServiceException 服务异常。
     */
    SectionBinding getSectionBinding(LongIdKey sectionInfoKey) throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * <p>
     * 注意：该方法会清除本地缓存中的所有部件器绑定信息。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
