package com.dwarfeng.judge.impl.handler.sink;

/**
 * 抽象 Sink。
 *
 * @author DwArFeng
 * @see com.dwarfeng.judge.sdk.handler.sink.AbstractSink
 * @since 1.3.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractSink extends com.dwarfeng.judge.sdk.handler.sink.AbstractSink {

    public AbstractSink() {
    }

    public AbstractSink(String sinkType) {
        super(sinkType);
    }
}
