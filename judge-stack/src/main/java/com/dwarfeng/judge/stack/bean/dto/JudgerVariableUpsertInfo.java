package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 判断器变量插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JudgerVariableUpsertInfo implements Dto {

    private static final long serialVersionUID = -5147803588583298700L;

    private LongIdKey judgerInfoKey;
    private String judgerVariableId;

    /**
     * 变量类型。
     *
     * <p>
     * int 枚举，可能的状态为：
     * <ol>
     *     <li>文本</li>
     *     <li>整数</li>
     *     <li>浮点数</li>
     *     <li>布尔值</li>
     *     <li>日期值</li>
     * </ol>
     * 详细值参考 sdk 模块的常量工具类。
     */
    private int valueType;

    /**
     * 变量值。
     *
     * <p>
     * 该字段的具体类型取决于 <code>valueType</code> 字段的值。<br>
     * 对应的类型关系如下：
     * <table>
     *     <tr>
     *         <th>valueType 值</th>
     *         <th>valueType 含义</th>
     *         <th>value 字段类型</th>
     *     </tr>
     *     <tr>
     *         <td>0</td>
     *         <td>文本</td>
     *         <td>String</td>
     *     </tr>
     *     <tr>
     *         <td>1</td>
     *         <td>整数</td>
     *         <td>Long</td>
     *     </tr>
     *     <tr>
     *         <td>2</td>
     *         <td>浮点数</td>
     *         <td>Double</td>
     *     </tr>
     *     <tr>
     *         <td>3</td>
     *         <td>布尔值</td>
     *         <td>Boolean</td>
     *     </tr>
     *     <tr>
     *         <td>4</td>
     *         <td>日期值</td>
     *         <td>Date</td>
     *     </tr>
     * </table>
     */
    private Object value;

    public JudgerVariableUpsertInfo() {
    }

    public JudgerVariableUpsertInfo(
            LongIdKey judgerInfoKey, String judgerVariableId, int valueType, Object value
    ) {
        this.judgerInfoKey = judgerInfoKey;
        this.judgerVariableId = judgerVariableId;
        this.valueType = valueType;
        this.value = value;
    }

    public LongIdKey getJudgerInfoKey() {
        return judgerInfoKey;
    }

    public void setJudgerInfoKey(LongIdKey judgerInfoKey) {
        this.judgerInfoKey = judgerInfoKey;
    }

    public String getJudgerVariableId() {
        return judgerVariableId;
    }

    public void setJudgerVariableId(String judgerVariableId) {
        this.judgerVariableId = judgerVariableId;
    }

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "JudgerVariableUpsertInfo{" +
                "judgerInfoKey=" + judgerInfoKey +
                ", judgerVariableId='" + judgerVariableId + '\'' +
                ", valueType=" + valueType +
                ", value=" + value +
                '}';
    }
}
