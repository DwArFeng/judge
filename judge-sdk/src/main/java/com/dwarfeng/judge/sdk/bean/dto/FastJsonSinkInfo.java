package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.SinkInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkInfo.*;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FastJson 下沉信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class FastJsonSinkInfo implements Dto {

    private static final long serialVersionUID = -1301306351309308751L;

    public static FastJsonSinkInfo of(SinkInfo sinkInfo) {
        if (Objects.isNull(sinkInfo)) {
            return null;
        } else {
            return new FastJsonSinkInfo(
                    FastJsonLongIdKey.of(sinkInfo.getSectionKey()),
                    FastJsonLongIdKey.of(sinkInfo.getTaskKey()),
                    sinkInfo.getSectionName(),
                    sinkInfo.getSectionRemark(),
                    sinkInfo.getTaskStatus(),
                    sinkInfo.getTaskCreatedDate(),
                    sinkInfo.getTaskStartedDate(),
                    sinkInfo.getTaskEndedDate(),
                    sinkInfo.getTaskDuration(),
                    sinkInfo.getTaskShouldExpireDate(),
                    sinkInfo.getTaskShouldDieDate(),
                    sinkInfo.getTaskExpiredDate(),
                    sinkInfo.getTaskDiedDate(),
                    sinkInfo.getTaskAnchorMessage(),
                    Optional.ofNullable(sinkInfo.getTaskEvents()).map(
                            f -> f.stream().map(FastJsonTaskEvent::of).collect(Collectors.toList())
                    ).orElse(null),
                    Optional.ofNullable(sinkInfo.getAnalyses()).map(
                            f -> f.stream().map(FastJsonAnalysis::of).collect(Collectors.toList())
                    ).orElse(null),
                    Optional.ofNullable(sinkInfo.getJudgements()).map(
                            f -> f.stream().map(FastJsonJudgement::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey sectionKey;

    @JSONField(name = "task_key", ordinal = 2)
    private FastJsonLongIdKey taskKey;

    @JSONField(name = "section_name", ordinal = 3)
    private String sectionName;

    @JSONField(name = "section_remark", ordinal = 4)
    private String sectionRemark;

    @JSONField(name = "task_status", ordinal = 5)
    private int taskStatus;

    @JSONField(name = "task_created_date", ordinal = 6)
    private Date taskCreatedDate;

    @JSONField(name = "task_started_date", ordinal = 7)
    private Date taskStartedDate;

    @JSONField(name = "task_ended_date", ordinal = 8)
    private Date taskEndedDate;

    @JSONField(name = "task_duration", ordinal = 9)
    private Long taskDuration;

    @JSONField(name = "task_should_expire_date", ordinal = 10)
    private Date taskShouldExpireDate;

    @JSONField(name = "task_should_die_date", ordinal = 11)
    private Date taskShouldDieDate;

    @JSONField(name = "task_expired_date", ordinal = 12)
    private Date taskExpiredDate;

    @JSONField(name = "task_died_date", ordinal = 13)
    private Date taskDiedDate;

    @JSONField(name = "task_anchor_message", ordinal = 14)
    private String taskAnchorMessage;

    @JSONField(name = "task_events", ordinal = 15)
    private List<FastJsonTaskEvent> taskEvents;

    @JSONField(name = "analyses", ordinal = 16)
    private List<FastJsonAnalysis> analyses;

    @JSONField(name = "analysis_pictures", ordinal = 17)
    private List<FastJsonJudgement> judgements;

    public FastJsonSinkInfo() {
    }

    public FastJsonSinkInfo(
            FastJsonLongIdKey sectionKey, FastJsonLongIdKey taskKey, String sectionName, String sectionRemark,
            int taskStatus, Date taskCreatedDate, Date taskStartedDate, Date taskEndedDate, Long taskDuration,
            Date taskShouldExpireDate, Date taskShouldDieDate, Date taskExpiredDate, Date taskDiedDate,
            String taskAnchorMessage, List<FastJsonTaskEvent> taskEvents, List<FastJsonAnalysis> analyses,
            List<FastJsonJudgement> judgements
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
    }

    public Date getTaskShouldDieDate() {
        return taskShouldDieDate;
    }

    public void setTaskShouldDieDate(Date taskShouldDieDate) {
        this.taskShouldDieDate = taskShouldDieDate;
    }

    public FastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(FastJsonLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public FastJsonLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(FastJsonLongIdKey taskKey) {
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

    public List<FastJsonTaskEvent> getTaskEvents() {
        return taskEvents;
    }

    public void setTaskEvents(List<FastJsonTaskEvent> taskEvents) {
        this.taskEvents = taskEvents;
    }

    public List<FastJsonAnalysis> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<FastJsonAnalysis> analyses) {
        this.analyses = analyses;
    }

    public List<FastJsonJudgement> getJudgements() {
        return judgements;
    }

    public void setJudgements(List<FastJsonJudgement> judgements) {
        this.judgements = judgements;
    }

    @Override
    public String toString() {
        return "FastJsonSinkInfo{" +
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
                '}';
    }

    /**
     * FastJson 任务事件。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class FastJsonTaskEvent implements Dto {

        private static final long serialVersionUID = 5252187149947420237L;

        public static FastJsonTaskEvent of(TaskEvent taskEvent) {
            if (Objects.isNull(taskEvent)) {
                return null;
            } else {
                return new FastJsonTaskEvent(
                        taskEvent.getHappenedDate(),
                        taskEvent.getMessage()
                );
            }
        }

        @JSONField(name = "happened_date", ordinal = 1)
        private Date happenedDate;

        @JSONField(name = "message", ordinal = 2)
        private String message;

        public FastJsonTaskEvent() {
        }

        public FastJsonTaskEvent(Date happenedDate, String message) {
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
            return "FastJsonTaskEvent{" +
                    "happenedDate=" + happenedDate +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    /**
     * FastJson 分析结果。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class FastJsonAnalysis implements Dto {

        private static final long serialVersionUID = -8494087839929892445L;

        public static FastJsonAnalysis of(Analysis analysis) {
            if (Objects.isNull(analysis)) {
                return null;
            } else {
                return new FastJsonAnalysis(
                        analysis.getDataId(), analysis.getDataType(), analysis.getValue()
                );
            }
        }

        @JSONField(name = "data_id", ordinal = 1)
        private String dataId;

        @JSONField(name = "data_type", ordinal = 2)
        private int dataType;

        @JSONField(name = "value", ordinal = 3)
        private Object value;

        public FastJsonAnalysis() {
        }

        public FastJsonAnalysis(String dataId, int dataType, Object value) {
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
            return "FastJsonAnalysis{" +
                    "dataId='" + dataId + '\'' +
                    ", dataType=" + dataType +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * FastJson 分析结果图片。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class FastJsonAnalysisPicture implements Dto {

        private static final long serialVersionUID = 3216260549902767527L;

        public static FastJsonAnalysisPicture of(AnalysisPicture analysisPicture) {
            if (Objects.isNull(analysisPicture)) {
                return null;
            } else {
                return new FastJsonAnalysisPicture(
                        FastJsonLongIdKey.of(analysisPicture.getAnalysisPictureInfoKey()),
                        analysisPicture.getOriginName(),
                        analysisPicture.getLength(),
                        analysisPicture.getRemark()
                );
            }
        }

        @JSONField(name = "analysis_picture_info_key", ordinal = 1)
        private FastJsonLongIdKey analysisPictureInfoKey;

        @JSONField(name = "origin_name", ordinal = 2)
        private String originName;

        @JSONField(name = "length", ordinal = 3)
        private Long length;

        @JSONField(name = "remark", ordinal = 4)
        private String remark;

        public FastJsonAnalysisPicture() {
        }

        public FastJsonAnalysisPicture(
                FastJsonLongIdKey analysisPictureInfoKey, String originName, Long length, String remark
        ) {
            this.analysisPictureInfoKey = analysisPictureInfoKey;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public FastJsonLongIdKey getAnalysisPictureInfoKey() {
            return analysisPictureInfoKey;
        }

        public void setAnalysisPictureInfoKey(FastJsonLongIdKey analysisPictureInfoKey) {
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
            return "FastJsonAnalysisPicture{" +
                    "analysisPictureInfoKey=" + analysisPictureInfoKey +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * FastJson 分析结果图片包。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class FastJsonAnalysisPicturePack implements Dto {

        private static final long serialVersionUID = 343272202454475803L;

        public static FastJsonAnalysisPicturePack of(AnalysisPicturePack analysisPicturePack) {
            if (Objects.isNull(analysisPicturePack)) {
                return null;
            } else {
                return new FastJsonAnalysisPicturePack(
                        FastJsonLongIdKey.of(analysisPicturePack.getAnalysisPicturePackKey()),
                        analysisPicturePack.getItemAnchorIndex(),
                        analysisPicturePack.getRemark(),
                        Optional.ofNullable(analysisPicturePack.getItems()).map(
                                f -> f.stream().map(FastJsonAnalysisPicturePackItem::of).collect(Collectors.toList())
                        ).orElse(null)
                );
            }
        }

        @JSONField(name = "analysis_picture_pack_key", ordinal = 1)
        private FastJsonLongIdKey analysisPicturePackKey;

        @JSONField(name = "item_anchor_index", ordinal = 2)
        private int itemAnchorIndex;

        @JSONField(name = "remark", ordinal = 3)
        private String remark;

        @JSONField(name = "items", ordinal = 4)
        private List<FastJsonAnalysisPicturePackItem> items;

        public FastJsonAnalysisPicturePack() {
        }

        public FastJsonAnalysisPicturePack(
                FastJsonLongIdKey analysisPicturePackKey, int itemAnchorIndex, String remark,
                List<FastJsonAnalysisPicturePackItem> items
        ) {
            this.analysisPicturePackKey = analysisPicturePackKey;
            this.itemAnchorIndex = itemAnchorIndex;
            this.remark = remark;
            this.items = items;
        }

        public FastJsonLongIdKey getAnalysisPicturePackKey() {
            return analysisPicturePackKey;
        }

        public void setAnalysisPicturePackKey(FastJsonLongIdKey analysisPicturePackKey) {
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

        public List<FastJsonAnalysisPicturePackItem> getItems() {
            return items;
        }

        public void setItems(List<FastJsonAnalysisPicturePackItem> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "FastJsonAnalysisPicturePack{" +
                    "analysisPicturePackKey=" + analysisPicturePackKey +
                    ", itemAnchorIndex=" + itemAnchorIndex +
                    ", remark='" + remark + '\'' +
                    ", items=" + items +
                    '}';
        }
    }

    /**
     * FastJson 分析结果图片包条目。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class FastJsonAnalysisPicturePackItem implements Dto {

        private static final long serialVersionUID = 3100220612231690311L;

        public static FastJsonAnalysisPicturePackItem of(AnalysisPicturePackItem analysisPicturePackItem) {
            if (Objects.isNull(analysisPicturePackItem)) {
                return null;
            } else {
                return new FastJsonAnalysisPicturePackItem(
                        FastJsonLongIdKey.of(analysisPicturePackItem.getAnalysisPicturePackItemInfoKey()),
                        analysisPicturePackItem.getIndex(),
                        analysisPicturePackItem.getOriginName(),
                        analysisPicturePackItem.getLength(),
                        analysisPicturePackItem.getRemark()
                );
            }
        }

        @JSONField(name = "analysis_picture_pack_item_info_key", ordinal = 1)
        private FastJsonLongIdKey analysisPicturePackItemInfoKey;

        @JSONField(name = "index", ordinal = 2)
        private int index;

        @JSONField(name = "origin_name", ordinal = 3)
        private String originName;

        @JSONField(name = "length", ordinal = 4)
        private Long length;

        @JSONField(name = "remark", ordinal = 5)
        private String remark;

        public FastJsonAnalysisPicturePackItem() {
        }

        public FastJsonAnalysisPicturePackItem(
                FastJsonLongIdKey analysisPicturePackItemInfoKey, int index, String originName, Long length,
                String remark
        ) {
            this.analysisPicturePackItemInfoKey = analysisPicturePackItemInfoKey;
            this.index = index;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public FastJsonLongIdKey getAnalysisPicturePackItemInfoKey() {
            return analysisPicturePackItemInfoKey;
        }

        public void setAnalysisPicturePackItemInfoKey(FastJsonLongIdKey analysisPicturePackItemInfoKey) {
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
            return "FastJsonAnalysisPicturePackItem{" +
                    "analysisPicturePackItemInfoKey=" + analysisPicturePackItemInfoKey +
                    ", index=" + index +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * FastJson 分析结果文件。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class FastJsonAnalysisFile implements Dto {

        private static final long serialVersionUID = 4524492155197965015L;

        public static FastJsonAnalysisFile of(AnalysisFile analysisFile) {
            if (Objects.isNull(analysisFile)) {
                return null;
            } else {
                return new FastJsonAnalysisFile(
                        FastJsonLongIdKey.of(analysisFile.getAnalysisFileInfoKey()),
                        analysisFile.getOriginName(),
                        analysisFile.getLength(),
                        analysisFile.getRemark()
                );
            }
        }

        @JSONField(name = "analysis_file_info_key", ordinal = 1)
        private FastJsonLongIdKey analysisFileInfoKey;

        @JSONField(name = "origin_name", ordinal = 2)
        private String originName;

        @JSONField(name = "length", ordinal = 3)
        private Long length;

        @JSONField(name = "remark", ordinal = 4)
        private String remark;

        public FastJsonAnalysisFile() {
        }

        public FastJsonAnalysisFile(
                FastJsonLongIdKey analysisFileInfoKey, String originName, Long length, String remark
        ) {
            this.analysisFileInfoKey = analysisFileInfoKey;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public FastJsonLongIdKey getAnalysisFileInfoKey() {
            return analysisFileInfoKey;
        }

        public void setAnalysisFileInfoKey(FastJsonLongIdKey analysisFileInfoKey) {
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
            return "FastJsonAnalysisFile{" +
                    "analysisFileInfoKey=" + analysisFileInfoKey +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * FastJson 分析结果文件包。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class FastJsonAnalysisFilePack implements Dto {

        private static final long serialVersionUID = 9127002569157280584L;

        public static FastJsonAnalysisFilePack of(AnalysisFilePack analysisFilePack) {
            if (Objects.isNull(analysisFilePack)) {
                return null;
            } else {
                return new FastJsonAnalysisFilePack(
                        FastJsonLongIdKey.of(analysisFilePack.getAnalysisFilePackKey()),
                        analysisFilePack.getItemAnchorIndex(),
                        analysisFilePack.getRemark(),
                        Optional.ofNullable(analysisFilePack.getItems()).map(
                                f -> f.stream().map(FastJsonAnalysisFilePackItem::of).collect(Collectors.toList())
                        ).orElse(null)
                );
            }
        }

        @JSONField(name = "analysis_file_pack_key", ordinal = 1)
        private FastJsonLongIdKey analysisFilePackKey;

        @JSONField(name = "item_anchor_index", ordinal = 2)
        private int itemAnchorIndex;

        @JSONField(name = "remark", ordinal = 3)
        private String remark;

        @JSONField(name = "items", ordinal = 4)
        private List<FastJsonAnalysisFilePackItem> items;

        public FastJsonAnalysisFilePack() {
        }

        public FastJsonAnalysisFilePack(
                FastJsonLongIdKey analysisFilePackKey, int itemAnchorIndex, String remark,
                List<FastJsonAnalysisFilePackItem> items
        ) {
            this.analysisFilePackKey = analysisFilePackKey;
            this.itemAnchorIndex = itemAnchorIndex;
            this.remark = remark;
            this.items = items;
        }

        public FastJsonLongIdKey getAnalysisFilePackKey() {
            return analysisFilePackKey;
        }

        public void setAnalysisFilePackKey(FastJsonLongIdKey analysisFilePackKey) {
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

        public List<FastJsonAnalysisFilePackItem> getItems() {
            return items;
        }

        public void setItems(List<FastJsonAnalysisFilePackItem> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "FastJsonAnalysisFilePack{" +
                    "analysisFilePackKey=" + analysisFilePackKey +
                    ", itemAnchorIndex=" + itemAnchorIndex +
                    ", remark='" + remark + '\'' +
                    ", items=" + items +
                    '}';
        }
    }

    /**
     * FastJson 分析结果图片包条目。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class FastJsonAnalysisFilePackItem implements Dto {

        private static final long serialVersionUID = -6004226485600466930L;

        public static FastJsonAnalysisFilePackItem of(AnalysisFilePackItem analysisFilePackItem) {
            if (Objects.isNull(analysisFilePackItem)) {
                return null;
            } else {
                return new FastJsonAnalysisFilePackItem(
                        FastJsonLongIdKey.of(analysisFilePackItem.getAnalysisFilePackItemInfoKey()),
                        analysisFilePackItem.getIndex(),
                        analysisFilePackItem.getOriginName(),
                        analysisFilePackItem.getLength(),
                        analysisFilePackItem.getRemark()
                );
            }
        }

        @JSONField(name = "analysis_file_pack_item_info_key", ordinal = 1)
        private FastJsonLongIdKey analysisFilePackItemInfoKey;

        @JSONField(name = "index", ordinal = 2)
        private int index;

        @JSONField(name = "origin_name", ordinal = 3)
        private String originName;

        @JSONField(name = "length", ordinal = 4)
        private Long length;

        @JSONField(name = "remark", ordinal = 5)
        private String remark;

        public FastJsonAnalysisFilePackItem() {
        }

        public FastJsonAnalysisFilePackItem(
                FastJsonLongIdKey analysisFilePackItemInfoKey, int index, String originName, Long length, String remark
        ) {
            this.analysisFilePackItemInfoKey = analysisFilePackItemInfoKey;
            this.index = index;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public FastJsonLongIdKey getAnalysisFilePackItemInfoKey() {
            return analysisFilePackItemInfoKey;
        }

        public void setAnalysisFilePackItemInfoKey(FastJsonLongIdKey analysisFilePackItemInfoKey) {
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
            return "FastJsonAnalysisFilePackItem{" +
                    "analysisFilePackItemInfoKey=" + analysisFilePackItemInfoKey +
                    ", index=" + index +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * FastJson 分析结果。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class FastJsonJudgement implements Dto {

        private static final long serialVersionUID = 6499226944724770541L;

        public static FastJsonJudgement of(Judgement judgement) {
            if (Objects.isNull(judgement)) {
                return null;
            } else {
                return new FastJsonJudgement(
                        judgement.getDataId(),
                        judgement.getValue(),
                        judgement.getMessage()
                );
            }
        }

        @JSONField(name = "data_id", ordinal = 1)
        private String dataId;

        @JSONField(name = "value", ordinal = 2)
        private double value;

        @JSONField(name = "message", ordinal = 3)
        private String message;

        public FastJsonJudgement() {
        }

        public FastJsonJudgement(String dataId, double value, String message) {
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
            return "FastJsonJudgement{" +
                    "dataId='" + dataId + '\'' +
                    ", value=" + value +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
