package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.AssignInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 指派用本地缓存处理器。
 * <p>处理器在本地保存数据，缓存中的数据可以保证与数据源保持同步。</p>
 * <p>数据存放在本地，必要时才与数据访问层通信，这有助于程序效率的提升。</p>
 * <p>该处理器线程安全。</p>
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface AssignLocalCacheHandler extends Handler {

    /**
     * 获取指定部件的指派信息。
     *
     * @param sectionKey 指定部件的指派上下文，或者是null。
     * @return 指定部件的指派信息。
     * @throws HandlerException 处理器异常。
     */
    AssignInfo getAssignInfo(LongIdKey sectionKey) throws HandlerException;

    /**
     * 清除本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clear() throws HandlerException;
}
