package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.SinkerMetaCompleteInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaInspectResult;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaResetInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 下沉器元数据操作处理器。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerMetaOperateHandler extends Handler {

    /**
     * 下沉器元数据查询。
     *
     * @param info 下沉器元数据查询信息。
     * @return 下沉器元数据查询结果。
     * @throws HandlerException 处理器异常。
     */
    SinkerMetaInspectResult inspect(SinkerMetaInspectInfo info) throws HandlerException;

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
     * @throws HandlerException 处理器异常。
     */
    void complete(SinkerMetaCompleteInfo info) throws HandlerException;

    /**
     * 下沉器元数据重置。
     *
     * <p>
     * 该方法会查找给定的补全信息中的部件主键与下沉器信息主键对应的元数据列表，并全部删除。<br>
     * 随后，查找给定的补全信息中的下沉器信息主键对应的元数据指示器列表，并初始化对应的元数据。<br>
     * 初始化的元数据的值为对应的元数据指示器的默认值。
     *
     * @param info 下沉器元数据重置信息。
     * @throws HandlerException 处理器异常。
     */
    void reset(SinkerMetaResetInfo info) throws HandlerException;
}
