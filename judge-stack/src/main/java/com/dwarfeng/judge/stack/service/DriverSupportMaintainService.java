package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.DriverSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.CrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 驱动器支持维护服务。
 *
 * @author DwArFeng
 * @since beta-1.1.0
 */
public interface DriverSupportMaintainService extends CrudService<StringIdKey, DriverSupport>,
        EntireLookupService<DriverSupport>, PresetLookupService<DriverSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";

    /**
     * 重置驱动器支持。
     *
     * @throws ServiceException 服务异常。
     */
    void reset() throws ServiceException;
}
