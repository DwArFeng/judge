package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果插入或更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisUpsertInfo implements Dto {

    private static final long serialVersionUID = -1446112363236322235L;

    private LongIdKey taskKey;
    private String dataStringId;

    /**
     * 数据类型。
     *
     * <p>
     * int 枚举，可能的状态为：
     * <ul>
     *     <li>文本</li>
     *     <li>整数</li>
     *     <li>浮点数</li>
     *     <li>布尔值</li>
     *     <li>日期值</li>
     *     <li>图片</li>
     *     <li>图片包</li>
     *     <li>文件</li>
     *     <li>文件包</li>
     * </ul>
     * 详细值参考 sdk 模块的常量工具类。
     */
    private int dataType;

    /**
     * 属性值。
     *
     * <p>
     * 此处的属性值是一个对象，其类型由 {@link #dataType} 决定。其对应关系如下：
     * <table>
     *     <tr>
     *         <th>属性类型</th>
     *         <th>属性值类型</th>
     *     </tr>
     *     <tr>
     *         <td>文本</td>
     *         <td>{@link String}</td>
     *     </tr>
     *     <tr>
     *         <td>整数</td>
     *         <td>{@link Long}</td>
     *     </tr>
     *     <tr>
     *         <td>浮点数</td>
     *         <td>{@link Double}</td>
     *     </tr>
     *     <tr>
     *         <td>布尔值</td>
     *         <td>{@link Boolean}</td>
     *     </tr>
     *     <tr>
     *         <td>日期值</td>
     *         <td>{@link java.util.Date}</td>
     *     </tr>
     *     <tr>
     *         <td>图片</td>
     *         <td>{@link AnalysisPictureUpsertInfo}</td>
     *     </tr>
     *     <tr>
     *         <td>图片包</td>
     *         <td>{@link AnalysisPicturePackUpsertInfo}</td>
     *     </tr>
     *     <tr>
     *         <td>文件</td>
     *         <td>{@link AnalysisFileUpsertInfo}</td>
     *     </tr>
     *     <tr>
     *         <td>文件包</td>
     *         <td>{@link AnalysisFilePackUpsertInfo}</td>
     *     </tr>
     * </table>
     */
    private Object value;

    public AnalysisUpsertInfo() {
    }

    public AnalysisUpsertInfo(LongIdKey taskKey, String dataStringId, int dataType, Object value) {
        this.taskKey = taskKey;
        this.dataStringId = dataStringId;
        this.dataType = dataType;
        this.value = value;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getDataStringId() {
        return dataStringId;
    }

    public void setDataStringId(String dataStringId) {
        this.dataStringId = dataStringId;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AnalysisUpsertInfo{" +
                "taskKey=" + taskKey +
                ", dataStringId='" + dataStringId + '\'' +
                ", dataType=" + dataType +
                ", value=" + value +
                '}';
    }
}
