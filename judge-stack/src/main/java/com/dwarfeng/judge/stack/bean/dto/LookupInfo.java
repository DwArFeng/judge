package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 查询信息。
 *
 * @author DwArFeng
 * @author wangyc
 * @since 2.3.0
 */
public class LookupInfo implements Dto {

    private static final long serialVersionUID = -4728418298113771459L;

    /**
     * 提供器信息键。
     */
    private LongIdKey providerInfoKey;

    /**
     * 适配器信息键。
     *
     * <p>
     * 该字段用于指定查询过程中使用的数据适配器。<br>
     * 当该字段不为 <code>null</code> 时，系统会在调用提供器查询数据之前，
     * 先使用指定的适配器对查询参数进行适配处理。<br>
     * 适配器的主要作用是将分析器的查询参数转换为提供器能够理解的格式，
     * 或者对查询参数进行预处理、验证、转换等操作。
     *
     * <p>
     * 例如，分析器可能需要查询某个时间范围内的数据，但提供器期望接收的是
     * 时间戳格式的参数，此时可以通过适配器将 <code>Date</code> 对象转换为
     * <code>Long</code> 类型的时间戳。
     *
     * <p>
     * 当该字段为 <code>null</code> 时，表示不需要使用适配器，直接使用原始的
     * 查询参数调用提供器。
     *
     * <p>
     * 该字段对应的适配器信息必须存在于系统中，且必须处于启用状态。
     */
    private LongIdKey adapterInfoKey;

    /**
     * 预设。
     *
     * <p>
     * <code>preset</code> 参数用于区分数据的查询方式。<br>
     * 例如一个提供器可以查询常规历史数据和报警历史数据，则这两种数据应该用不同的 <code>preset</code> 加以区分。
     */
    private String preset;

    /**
     * 预设对应的对象数组。
     *
     * <p>
     * <code>objs</code> 参数用于指定预设的参数，提供器根据参数查询对应的数据。<br>
     * 例如一个提供器可以根据时间范围分页查询历史数据，则 <code>objs</code> 可能为以下值:
     * <table>
     *     <tr>
     *         <th>索引</th>
     *         <th>类型</th>
     *         <th>描述</th>
     *     </tr>
     *     <tr>
     *         <td>0</td>
     *         <td>Date</td>
     *         <td>起始时间</td>
     *     </tr>
     *     <tr>
     *         <td>1</td>
     *         <td>Date</td>
     *         <td>结束时间</td>
     *     </tr>
     *     <tr>
     *         <td>2</td>
     *         <td>int</td>
     *         <td>查询的页数</td>
     *     </tr>
     *     <tr>
     *         <td>3</td>
     *         <td>int</td>
     *         <td>每页返回的行数</td>
     *     </tr>
     * </table>
     *
     * <p>
     * <code>objs</code> 参数中的每一个对象必须是简单对象，例如字符串、数字等。<br>
     * 禁止使用无法序列化的复杂对象，例如数据库连接、文件句柄等。
     */
    private Object[] objs;

    public LookupInfo() {
    }

    public LookupInfo(LongIdKey providerInfoKey, LongIdKey adapterInfoKey, String preset, Object[] objs) {
        this.providerInfoKey = providerInfoKey;
        this.adapterInfoKey = adapterInfoKey;
        this.preset = preset;
        this.objs = objs;
    }

    public LongIdKey getProviderInfoKey() {
        return providerInfoKey;
    }

    public void setProviderInfoKey(LongIdKey providerInfoKey) {
        this.providerInfoKey = providerInfoKey;
    }

    public LongIdKey getAdapterInfoKey() {
        return adapterInfoKey;
    }

    public void setAdapterInfoKey(LongIdKey adapterInfoKey) {
        this.adapterInfoKey = adapterInfoKey;
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
        return "LookupInfo{" +
                "providerInfoKey=" + providerInfoKey +
                ", adapterInfoKey=" + adapterInfoKey +
                ", preset='" + preset + '\'' +
                ", objs=" + Arrays.toString(objs) +
                '}';
    }
}
