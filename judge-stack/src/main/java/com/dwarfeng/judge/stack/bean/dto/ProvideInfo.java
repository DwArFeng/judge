package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 提供信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class ProvideInfo implements Dto {

    private static final long serialVersionUID = -3288491347046829360L;

    private LongIdKey providerInfoKey;

    /**
     * 预设。
     *
     * <p>
     * 方法中的 <code>objs</code> 参数用于指定预设的参数，提供器根据参数提供对应的数据。<br>
     * 例如一个提供器可以根据时间范围提供历史数据，则 <code>objs</code> 中应该包含两个参数，分别表示开始时间和结束时间。
     */
    private String preset;

    /**
     * 预设对应的对象数组。
     *
     * <p>
     * 方法中的 <code>objs</code> 参数用于指定预设的参数，提供器根据参数提供对应的数据。<br>
     * 例如一个提供器可以根据时间范围提供历史数据，则 <code>objs</code> 中应该包含两个参数，分别表示开始时间和结束时间。
     *
     * <p>
     * 方法中的 <code>objs</code> 参数中的每一个对象应该是简单对象，例如字符串、数字等。<br>
     * 禁止使用无法序列化的复杂对象，例如数据库连接、文件句柄等。
     */
    private Object[] objs;

    public ProvideInfo() {
    }

    public ProvideInfo(LongIdKey providerInfoKey, String preset, Object[] objs) {
        this.providerInfoKey = providerInfoKey;
        this.preset = preset;
        this.objs = objs;
    }

    public LongIdKey getProviderInfoKey() {
        return providerInfoKey;
    }

    public void setProviderInfoKey(LongIdKey providerInfoKey) {
        this.providerInfoKey = providerInfoKey;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public Object[] getObjs() {
        return objs;
    }

    public void setObjs(Object[] objs) {
        this.objs = objs;
    }

    @Override
    public String toString() {
        return "ProvideInfo{" +
                "providerInfoKey=" + providerInfoKey +
                ", preset='" + preset + '\'' +
                ", objs=" + Arrays.toString(objs) +
                '}';
    }
}
