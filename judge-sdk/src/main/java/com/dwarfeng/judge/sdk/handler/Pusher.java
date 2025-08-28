package com.dwarfeng.judge.sdk.handler;

/**
 * 事件推送器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
public interface Pusher {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);
}
