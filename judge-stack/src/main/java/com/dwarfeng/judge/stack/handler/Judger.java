package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.JudgerResult;
import com.dwarfeng.judge.stack.exception.JudgerException;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 判断器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface Judger {

    /**
     * 对仓库处理器中的数据做出判断，并生成判断值。
     *
     * @param variableRepository 变量仓库。
     * @return 判断值。
     * @throws JudgerException 判断异常。
     */
    JudgerResult judge(VariableRepository variableRepository) throws JudgerException;

    /**
     * 仓库处理器。
     *
     * @author DwArFeng
     * @since 1.5.0
     */
    interface VariableRepository {

        /**
         * 查询仓库中是否有指定分类的数据。
         *
         * @param category 数据的分类。
         * @return 指定的数据是否存在。
         * @throws HandlerException 处理器异常。
         */
        boolean existsData(String category) throws HandlerException;

        /**
         * 获取仓库中的数据。
         *
         * @param category 数据的分类。
         * @return 指定的数据。
         * @throws HandlerException 处理器异常。
         */
        String getData(String category) throws HandlerException;

        /**
         * 向仓库中推送数据。
         *
         * @param category 数据的分类。
         * @param value    值。
         * @throws HandlerException 处理器异常。
         */
        void putData(String category, String value) throws HandlerException;
    }
}
