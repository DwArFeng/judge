package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;
import java.util.List;

/**
 * 下沉信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkInfo implements Dto {

    private static final long serialVersionUID = -3708631880200815710L;

    private LongIdKey sectionKey;
    private LongIdKey taskKey;

    private String sectionName;
    private String sectionRemark;

    /**
     * 任务状态。
     *
     * <p>
     * int 枚举，可能的状态为：
     * <ol>
     *     <li>任务创建</li>
     *     <li>任务进行</li>
     *     <li>任务完成</li>
     *     <li>任务失败</li>
     *     <li>任务过期</li>
     *     <li>任务死亡</li>
     * </ol>
     * 详细值参考 sdk 模块的常量工具类。
     */
    private int taskStatus;

    private Date taskCreatedDate;
    private Date taskStartedDate;
    private Date taskEndedDate;
    private Long taskDuration;
    private Date taskShouldExpireDate;
    private Date taskShouldDieDate;
    private Date taskExpiredDate;
    private Date taskDiedDate;
    private String taskAnchorMessage;

    private List<TaskEvent> taskEvents;
    private List<Analysis> analyses;
    private List<Judgement> judgements;
    private List<VisualizeData> visualizeDatas;

    public SinkInfo() {
    }

    public SinkInfo(
            LongIdKey sectionKey, LongIdKey taskKey, String sectionName, String sectionRemark, int taskStatus,
            Date taskCreatedDate, Date taskStartedDate, Date taskEndedDate, Long taskDuration,
            Date taskShouldExpireDate, Date taskShouldDieDate, Date taskExpiredDate, Date taskDiedDate,
            String taskAnchorMessage, List<TaskEvent> taskEvents, List<Analysis> analyses, List<Judgement> judgements,
            List<VisualizeData> visualizeDatas
    ) {
        this.sectionKey = sectionKey;
        this.taskKey = taskKey;
        this.sectionName = sectionName;
        this.sectionRemark = sectionRemark;
        this.taskStatus = taskStatus;
        this.taskCreatedDate = taskCreatedDate;
        this.taskStartedDate = taskStartedDate;
        this.taskEndedDate = taskEndedDate;
        this.taskDuration = taskDuration;
        this.taskShouldExpireDate = taskShouldExpireDate;
        this.taskShouldDieDate = taskShouldDieDate;
        this.taskExpiredDate = taskExpiredDate;
        this.taskDiedDate = taskDiedDate;
        this.taskAnchorMessage = taskAnchorMessage;
        this.taskEvents = taskEvents;
        this.analyses = analyses;
        this.judgements = judgements;
        this.visualizeDatas = visualizeDatas;
    }

    public LongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(LongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionRemark() {
        return sectionRemark;
    }

    public void setSectionRemark(String sectionRemark) {
        this.sectionRemark = sectionRemark;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getTaskCreatedDate() {
        return taskCreatedDate;
    }

    public void setTaskCreatedDate(Date taskCreatedDate) {
        this.taskCreatedDate = taskCreatedDate;
    }

    public Date getTaskStartedDate() {
        return taskStartedDate;
    }

    public void setTaskStartedDate(Date taskStartedDate) {
        this.taskStartedDate = taskStartedDate;
    }

    public Date getTaskEndedDate() {
        return taskEndedDate;
    }

    public void setTaskEndedDate(Date taskEndedDate) {
        this.taskEndedDate = taskEndedDate;
    }

    public Long getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(Long taskDuration) {
        this.taskDuration = taskDuration;
    }

    public Date getTaskShouldExpireDate() {
        return taskShouldExpireDate;
    }

    public void setTaskShouldExpireDate(Date taskShouldExpireDate) {
        this.taskShouldExpireDate = taskShouldExpireDate;
    }

    public Date getTaskShouldDieDate() {
        return taskShouldDieDate;
    }

    public void setTaskShouldDieDate(Date taskShouldDieDate) {
        this.taskShouldDieDate = taskShouldDieDate;
    }

    public Date getTaskExpiredDate() {
        return taskExpiredDate;
    }

    public void setTaskExpiredDate(Date taskExpiredDate) {
        this.taskExpiredDate = taskExpiredDate;
    }

    public Date getTaskDiedDate() {
        return taskDiedDate;
    }

    public void setTaskDiedDate(Date taskDiedDate) {
        this.taskDiedDate = taskDiedDate;
    }

    public String getTaskAnchorMessage() {
        return taskAnchorMessage;
    }

    public void setTaskAnchorMessage(String taskAnchorMessage) {
        this.taskAnchorMessage = taskAnchorMessage;
    }

    public List<TaskEvent> getTaskEvents() {
        return taskEvents;
    }

    public void setTaskEvents(List<TaskEvent> taskEvents) {
        this.taskEvents = taskEvents;
    }

    public List<Analysis> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analysis> analyses) {
        this.analyses = analyses;
    }

    public List<Judgement> getJudgements() {
        return judgements;
    }

    public void setJudgements(List<Judgement> judgements) {
        this.judgements = judgements;
    }

    public List<VisualizeData> getVisualizeDatas() {
        return visualizeDatas;
    }

    public void setVisualizeDatas(List<VisualizeData> visualizeDatas) {
        this.visualizeDatas = visualizeDatas;
    }

    @Override
    public String toString() {
        return "SinkInfo{" +
                "sectionKey=" + sectionKey +
                ", taskKey=" + taskKey +
                ", sectionName='" + sectionName + '\'' +
                ", sectionRemark='" + sectionRemark + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskCreatedDate=" + taskCreatedDate +
                ", taskStartedDate=" + taskStartedDate +
                ", taskEndedDate=" + taskEndedDate +
                ", taskDuration=" + taskDuration +
                ", taskShouldExpireDate=" + taskShouldExpireDate +
                ", taskShouldDieDate=" + taskShouldDieDate +
                ", taskExpiredDate=" + taskExpiredDate +
                ", taskDiedDate=" + taskDiedDate +
                ", taskAnchorMessage='" + taskAnchorMessage + '\'' +
                ", taskEvents=" + taskEvents +
                ", analyses=" + analyses +
                ", judgements=" + judgements +
                ", visualizeDatas=" + visualizeDatas +
                '}';
    }

    /**
     * 任务事件。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class TaskEvent implements Dto {

        private static final long serialVersionUID = -1986679227635890922L;

        private Date happenedDate;
        private String message;

        public TaskEvent() {
        }

        public TaskEvent(Date happenedDate, String message) {
            this.happenedDate = happenedDate;
            this.message = message;
        }

        public Date getHappenedDate() {
            return happenedDate;
        }

        public void setHappenedDate(Date happenedDate) {
            this.happenedDate = happenedDate;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "TaskEvent{" +
                    "happenedDate=" + happenedDate +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    /**
     * 分析结果。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class Analysis implements Dto {

        private static final long serialVersionUID = -4246269394690714501L;

        private String dataId;

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
         *         <td>{@link AnalysisPicture}</td>
         *     </tr>
         *     <tr>
         *         <td>图片包</td>
         *         <td>{@link AnalysisPicturePack}</td>
         *     </tr>
         *     <tr>
         *         <td>文件</td>
         *         <td>{@link AnalysisFile}</td>
         *     </tr>
         *     <tr>
         *         <td>文件包</td>
         *         <td>{@link AnalysisFilePack}</td>
         *     </tr>
         * </table>
         */
        private Object value;

        public Analysis() {
        }

        public Analysis(String dataId, int dataType, Object value) {
            this.dataId = dataId;
            this.dataType = dataType;
            this.value = value;
        }

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
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
            return "Analysis{" +
                    "dataId='" + dataId + '\'' +
                    ", dataType=" + dataType +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * 分析结果图片。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class AnalysisPicture implements Dto {

        private static final long serialVersionUID = 3374693086936757299L;

        private LongIdKey analysisPictureInfoKey;
        private String originName;
        private Long length;
        private String remark;

        public AnalysisPicture() {
        }

        public AnalysisPicture(LongIdKey analysisPictureInfoKey, String originName, Long length, String remark) {
            this.analysisPictureInfoKey = analysisPictureInfoKey;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public LongIdKey getAnalysisPictureInfoKey() {
            return analysisPictureInfoKey;
        }

        public void setAnalysisPictureInfoKey(LongIdKey analysisPictureInfoKey) {
            this.analysisPictureInfoKey = analysisPictureInfoKey;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public Long getLength() {
            return length;
        }

        public void setLength(Long length) {
            this.length = length;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public String toString() {
            return "AnalysisPicture{" +
                    "analysisPictureInfoKey=" + analysisPictureInfoKey +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * 分析结果图片包。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class AnalysisPicturePack implements Dto {

        private static final long serialVersionUID = -6659313220651718861L;

        private LongIdKey analysisPicturePackKey;

        /**
         * 条目锚点索引。
         *
         * <p>
         * 用来指示图片包所属条目的最大索引。
         */
        private int itemAnchorIndex;

        private String remark;
        private List<AnalysisPicturePackItem> items;

        public AnalysisPicturePack() {
        }

        public AnalysisPicturePack(
                LongIdKey analysisPicturePackKey, int itemAnchorIndex, String remark,
                List<AnalysisPicturePackItem> items
        ) {
            this.analysisPicturePackKey = analysisPicturePackKey;
            this.itemAnchorIndex = itemAnchorIndex;
            this.remark = remark;
            this.items = items;
        }

        public LongIdKey getAnalysisPicturePackKey() {
            return analysisPicturePackKey;
        }

        public void setAnalysisPicturePackKey(LongIdKey analysisPicturePackKey) {
            this.analysisPicturePackKey = analysisPicturePackKey;
        }

        public int getItemAnchorIndex() {
            return itemAnchorIndex;
        }

        public void setItemAnchorIndex(int itemAnchorIndex) {
            this.itemAnchorIndex = itemAnchorIndex;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<AnalysisPicturePackItem> getItems() {
            return items;
        }

        public void setItems(List<AnalysisPicturePackItem> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "AnalysisPicturePack{" +
                    "analysisPicturePackKey=" + analysisPicturePackKey +
                    ", itemAnchorIndex=" + itemAnchorIndex +
                    ", remark='" + remark + '\'' +
                    ", items=" + items +
                    '}';
        }
    }

    /**
     * 分析结果图片包条目。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class AnalysisPicturePackItem implements Dto {

        private static final long serialVersionUID = 8579193633632456651L;

        private LongIdKey analysisPicturePackItemInfoKey;
        private int index;
        private String originName;
        private Long length;
        private String remark;

        public AnalysisPicturePackItem() {
        }

        public AnalysisPicturePackItem(
                LongIdKey analysisPicturePackItemInfoKey, int index, String originName, Long length, String remark
        ) {
            this.analysisPicturePackItemInfoKey = analysisPicturePackItemInfoKey;
            this.index = index;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public LongIdKey getAnalysisPicturePackItemInfoKey() {
            return analysisPicturePackItemInfoKey;
        }

        public void setAnalysisPicturePackItemInfoKey(LongIdKey analysisPicturePackItemInfoKey) {
            this.analysisPicturePackItemInfoKey = analysisPicturePackItemInfoKey;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public Long getLength() {
            return length;
        }

        public void setLength(Long length) {
            this.length = length;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public String toString() {
            return "AnalysisPicturePackItem{" +
                    "analysisPicturePackItemInfoKey=" + analysisPicturePackItemInfoKey +
                    ", index=" + index +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * 分析结果文件。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class AnalysisFile implements Dto {

        private static final long serialVersionUID = -3356201839569091302L;

        private LongIdKey analysisFileInfoKey;
        private String originName;
        private Long length;
        private String remark;

        public AnalysisFile() {
        }

        public AnalysisFile(LongIdKey analysisFileInfoKey, String originName, Long length, String remark) {
            this.analysisFileInfoKey = analysisFileInfoKey;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public LongIdKey getAnalysisFileInfoKey() {
            return analysisFileInfoKey;
        }

        public void setAnalysisFileInfoKey(LongIdKey analysisFileInfoKey) {
            this.analysisFileInfoKey = analysisFileInfoKey;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public Long getLength() {
            return length;
        }

        public void setLength(Long length) {
            this.length = length;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public String toString() {
            return "AnalysisFile{" +
                    "analysisFileInfoKey=" + analysisFileInfoKey +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * 分析结果文件包。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class AnalysisFilePack implements Dto {

        private static final long serialVersionUID = -4559905303410694991L;

        private LongIdKey analysisFilePackKey;

        /**
         * 条目锚点索引。
         *
         * <p>
         * 用来指示文件包所属条目的最大索引。
         */
        private int itemAnchorIndex;

        private String remark;
        private List<AnalysisFilePackItem> items;

        public AnalysisFilePack() {
        }

        public AnalysisFilePack(
                LongIdKey analysisFilePackKey, int itemAnchorIndex, String remark, List<AnalysisFilePackItem> items
        ) {
            this.analysisFilePackKey = analysisFilePackKey;
            this.itemAnchorIndex = itemAnchorIndex;
            this.remark = remark;
            this.items = items;
        }

        public LongIdKey getAnalysisFilePackKey() {
            return analysisFilePackKey;
        }

        public void setAnalysisFilePackKey(LongIdKey analysisFilePackKey) {
            this.analysisFilePackKey = analysisFilePackKey;
        }

        public int getItemAnchorIndex() {
            return itemAnchorIndex;
        }

        public void setItemAnchorIndex(int itemAnchorIndex) {
            this.itemAnchorIndex = itemAnchorIndex;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<AnalysisFilePackItem> getItems() {
            return items;
        }

        public void setItems(List<AnalysisFilePackItem> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "AnalysisFilePack{" +
                    "analysisFilePackKey=" + analysisFilePackKey +
                    ", itemAnchorIndex=" + itemAnchorIndex +
                    ", remark='" + remark + '\'' +
                    ", items=" + items +
                    '}';
        }
    }

    /**
     * 分析结果图片包条目。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class AnalysisFilePackItem implements Dto {

        private static final long serialVersionUID = -2980994140805068953L;

        private LongIdKey analysisFilePackItemInfoKey;
        private int index;
        private String originName;
        private Long length;
        private String remark;

        public AnalysisFilePackItem() {
        }

        public AnalysisFilePackItem(
                LongIdKey analysisFilePackItemInfoKey, int index, String originName, Long length, String remark
        ) {
            this.analysisFilePackItemInfoKey = analysisFilePackItemInfoKey;
            this.index = index;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public LongIdKey getAnalysisFilePackItemInfoKey() {
            return analysisFilePackItemInfoKey;
        }

        public void setAnalysisFilePackItemInfoKey(LongIdKey analysisFilePackItemInfoKey) {
            this.analysisFilePackItemInfoKey = analysisFilePackItemInfoKey;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public Long getLength() {
            return length;
        }

        public void setLength(Long length) {
            this.length = length;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public String toString() {
            return "AnalysisFilePackItem{" +
                    "analysisFilePackItemInfoKey=" + analysisFilePackItemInfoKey +
                    ", index=" + index +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * 分析结果。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class Judgement implements Dto {

        private static final long serialVersionUID = -2354411926411642366L;

        private String dataId;

        /**
         * 判断值。
         *
         * <p>
         * 一个归一化的数值，取值范围为 [0.0, 1.0]。
         */
        private double value;

        /**
         * 判断消息。
         *
         * <p>
         * 一段描述性的文本，用于说明判断值的含义或解释判断值如何得出。
         */
        private String message;

        public Judgement() {
        }

        public Judgement(String dataId, double value, String message) {
            this.dataId = dataId;
            this.value = value;
            this.message = message;
        }

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Judgement{" +
                    "dataId='" + dataId + '\'' +
                    ", value=" + value +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    public static class VisualizeData implements Dto {

        private static final long serialVersionUID = 924021593902694952L;

        private String perspectiveStringId;
        private String content;

        public VisualizeData() {
        }

        public VisualizeData(String perspectiveStringId, String content) {
            this.perspectiveStringId = perspectiveStringId;
            this.content = content;
        }

        public String getPerspectiveStringId() {
            return perspectiveStringId;
        }

        public void setPerspectiveStringId(String perspectiveStringId) {
            this.perspectiveStringId = perspectiveStringId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "VisualizeData{" +
                    "perspectiveStringId='" + perspectiveStringId + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
