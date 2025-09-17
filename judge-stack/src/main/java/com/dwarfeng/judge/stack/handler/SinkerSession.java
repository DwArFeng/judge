package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.exception.SinkerSessionException;
import com.dwarfeng.judge.stack.struct.SectionBinding;
import com.dwarfeng.judge.stack.struct.SinkerBinding;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 下沉器会话。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerSession {

    /**
     * 初始化下沉器会话。
     *
     * @param context 下沉器会话上下文。
     */
    void init(Context context);

    /**
     * 打开下沉器会话。
     *
     * @throws SinkerSessionException 下沉器会话异常。
     */
    void openSession() throws SinkerSessionException;

    /**
     * 关闭下沉器会话。
     *
     * @throws SinkerSessionException 下沉器会话异常。
     */
    void closeSession() throws SinkerSessionException;

    /**
     * 下沉。
     *
     * @param info 下沉信息。
     * @throws SinkerSessionException 下沉器会话异常。
     */
    void sink(SinkInfo info) throws SinkerSessionException;

    /**
     * 下沉器会话上下文。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    interface Context {

        /**
         * 获取此下沉器会话对应的下沉器信息主键。
         *
         * @return 此下沉器会话对应的下沉器信息主键。
         * @throws Exception 任何可能的异常。
         */
        @Nonnull
        LongIdKey getSinkerInfoKey() throws Exception;

        /**
         * 获取指定的部件对应的部件绑定项。
         *
         * <p>
         * 该方法返回指定的部件的主键对应的部件绑定项。<br>
         * 如果指定的部件不存在，则返回 <code>null</code>。
         *
         * <p>
         * 为了提高查询效率，该方法的返回结果进行了缓存，调用者<b>不能</b>对返回的结果进行修改。<br>
         * 如果为了提高业务逻辑的便利性，要修改其中的参数，请对返回结果进行拷贝，并修改副本。
         *
         * @param sectionKey 指定的部件的主键。
         * @return 指定的部件对应的部件绑定项。
         * @throws Exception 任何可能的异常。
         */
        @Nullable
        SectionBinding getSectionBinding(LongIdKey sectionKey) throws Exception;

        /**
         * 获取指定的下沉器对应的下沉器绑定项。
         *
         * <p>
         * 该方法返回指定的下沉器的主键对应的下沉器绑定项。<br>
         * 如果指定的下沉器不存在，则返回 <code>null</code>。
         *
         * <p>
         * 为了提高查询效率，该方法的返回结果进行了缓存，调用者<b>不能</b>对返回的结果进行修改。<br>
         * 如果为了提高业务逻辑的便利性，要修改其中的参数，请对返回结果进行拷贝，并修改副本。
         *
         * @param sinkerKey 指定的下沉器的主键。
         * @return 指定的下沉器对应的下沉器绑定项。
         * @throws Exception 任何可能的异常。
         * @since 2.1.0-beta
         */
        @Nullable
        SinkerBinding getSinkerBinding(LongIdKey sinkerKey) throws Exception;

        /**
         * 查看变量。
         *
         * <p>
         * 该方法返回指定的变量查看信息对应的变量的查看结果。<br>
         * 如果指定的变量不存在，则返回 <code>null</code>。
         *
         * @param info 下沉器变量查看信息。
         * @return 下沉器变量的查看结果。
         * @throws Exception 任何可能的异常。
         */
        @Nullable
        SinkerVariableInspectResult inspectVariable(SinkerVariableInspectInfo info) throws Exception;

        /**
         * 插入/更新变量。
         *
         * @param info 下沉器变量插入/更新信息。
         * @throws Exception 任何可能的异常。
         */
        void upsertVariable(SinkerVariableUpsertInfo info) throws Exception;

        /**
         * 删除变量。
         *
         * @param info 下沉器变量删除信息。
         * @throws Exception 任何可能的异常。
         */
        void removeVariable(SinkerVariableRemoveInfo info) throws Exception;
    }
}
