package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.exception.RepositoryException;

/**
 * 仓库。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface Repository {

    /**
     * 返回仓库是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 仓库是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 获取仓库中的数据。
     *
     * @param category 数据的分类。
     * @param args     数据的详细参数。
     * @return 指定的数据。
     * @throws RepositoryException 仓库异常。
     */
    Object getData(String category, Object... args) throws RepositoryException;
}
