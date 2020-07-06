package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 仓库处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface RepositoryHandler extends Handler {

    /**
     * 获取仓库中的数据。
     *
     * @param category 数据的分类。
     * @param args     数据的详细参数。
     * @return 指定的数据。
     * @throws HandlerException 处理器异常。
     */
    Object getData(String category, Object... args) throws HandlerException;
}
