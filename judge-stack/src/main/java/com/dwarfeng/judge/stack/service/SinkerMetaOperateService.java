package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.SinkerMetaCompleteInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaResetInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 下沉器元数据操作服务。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerMetaOperateService extends Service {

    /**
     * 下沉器元数据补全。
     *
     * <p>
     * 该方法会查找给定的补全信息中的下沉器信息主键对应的元数据指示器列表，<br>
     * 同时查找给定的补全信息中的部件主键与下沉器信息主键对应的元数据列表。<br>
     * 对于每个元数据指示器列表中的元数据指示器，如果元数据列表中不存在对应的元数据，则初始化元数据，
     * 其值为元数据指示器的默认值；<br>
     * 如果元数据列表中存在对应的元数据，则不进行处理，保留元数据的值。
     *
     * @param info 下沉器元数据补全信息。
     * @throws ServiceException 服务异常。
     */
    void complete(SinkerMetaCompleteInfo info) throws ServiceException;

    /**
     * 下沉器元数据重置。
     *
     * <p>
     * 该方法会查找给定的补全信息中的部件主键与下沉器信息主键对应的元数据列表，并全部删除。<br>
     * 随后，查找给定的补全信息中的下沉器信息主键对应的元数据指示器列表，并初始化对应的元数据。<br>
     * 初始化的元数据的值为对应的元数据指示器的默认值。
     *
     * @param info 下沉器元数据重置信息。
     * @throws ServiceException 服务异常。
     */
    void reset(SinkerMetaResetInfo info) throws ServiceException;
}
