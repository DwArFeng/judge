package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.SinkInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkInfo.*;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * JSFixed FastJson 下沉信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JSFixedFastJsonSinkInfo implements Dto {

    private static final long serialVersionUID = -2899109967095108060L;

    public static JSFixedFastJsonSinkInfo of(SinkInfo sinkInfo) {
        if (Objects.isNull(sinkInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonSinkInfo(
                    JSFixedFastJsonLongIdKey.of(sinkInfo.getSectionKey()),
                    JSFixedFastJsonLongIdKey.of(sinkInfo.getTaskKey()),
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
                            f -> f.stream().map(JSFixedFastJsonTaskEvent::of).collect(Collectors.toList())
                    ).orElse(null),
                    Optional.ofNullable(sinkInfo.getAnalyses()).map(
                            f -> f.stream().map(JSFixedFastJsonAnalysis::of).collect(Collectors.toList())
                    ).orElse(null),
                    Optional.ofNullable(sinkInfo.getJudgements()).map(
                            f -> f.stream().map(JSFixedFastJsonJudgement::of).collect(Collectors.toList())
                    ).orElse(null),
                    Optional.ofNullable(sinkInfo.getVisualizeDatas()).map(
                            f -> f.stream().map(JSFixedFastJsonVisualizeData::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey sectionKey;

    @JSONField(name = "task_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey taskKey;

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
    private List<JSFixedFastJsonTaskEvent> taskEvents;

    @JSONField(name = "analyses", ordinal = 16)
    private List<JSFixedFastJsonAnalysis> analyses;

    @JSONField(name = "analysis_pictures", ordinal = 17)
    private List<JSFixedFastJsonJudgement> judgements;

    @JSONField(name = "visualize_datas", ordinal = 18)
    private List<JSFixedFastJsonVisualizeData> visualizeDatas;

    public JSFixedFastJsonSinkInfo() {
    }

    public JSFixedFastJsonSinkInfo(
            JSFixedFastJsonLongIdKey sectionKey, JSFixedFastJsonLongIdKey taskKey, String sectionName,
            String sectionRemark, int taskStatus, Date taskCreatedDate, Date taskStartedDate, Date taskEndedDate,
            Long taskDuration, Date taskShouldExpireDate, Date taskShouldDieDate, Date taskExpiredDate,
            Date taskDiedDate, String taskAnchorMessage, List<JSFixedFastJsonTaskEvent> taskEvents,
            List<JSFixedFastJsonAnalysis> analyses, List<JSFixedFastJsonJudgement> judgements,
            List<JSFixedFastJsonVisualizeData> visualizeDatas
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

    public JSFixedFastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(JSFixedFastJsonLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public JSFixedFastJsonLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(JSFixedFastJsonLongIdKey taskKey) {
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

    public List<JSFixedFastJsonTaskEvent> getTaskEvents() {
        return taskEvents;
    }

    public void setTaskEvents(List<JSFixedFastJsonTaskEvent> taskEvents) {
        this.taskEvents = taskEvents;
    }

    public List<JSFixedFastJsonAnalysis> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<JSFixedFastJsonAnalysis> analyses) {
        this.analyses = analyses;
    }

    public List<JSFixedFastJsonJudgement> getJudgements() {
        return judgements;
    }

    public void setJudgements(List<JSFixedFastJsonJudgement> judgements) {
        this.judgements = judgements;
    }

    public List<JSFixedFastJsonVisualizeData> getVisualizeDatas() {
        return visualizeDatas;
    }

    public void setVisualizeDatas(List<JSFixedFastJsonVisualizeData> visualizeDatas) {
        this.visualizeDatas = visualizeDatas;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonSinkInfo{" +
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
     * JSFixed FastJson 任务事件。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class JSFixedFastJsonTaskEvent implements Dto {

        private static final long serialVersionUID = -1178471599147115810L;

        public static JSFixedFastJsonTaskEvent of(TaskEvent taskEvent) {
            if (Objects.isNull(taskEvent)) {
                return null;
            } else {
                return new JSFixedFastJsonTaskEvent(
                        taskEvent.getHappenedDate(),
                        taskEvent.getMessage()
                );
            }
        }

        @JSONField(name = "happened_date", ordinal = 1)
        private Date happenedDate;

        @JSONField(name = "message", ordinal = 2)
        private String message;

        public JSFixedFastJsonTaskEvent() {
        }

        public JSFixedFastJsonTaskEvent(Date happenedDate, String message) {
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
            return "JSFixedFastJsonTaskEvent{" +
                    "happenedDate=" + happenedDate +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    /**
     * JSFixed FastJson 分析结果。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class JSFixedFastJsonAnalysis implements Dto {

        private static final long serialVersionUID = 7490010200750127458L;

        public static JSFixedFastJsonAnalysis of(Analysis analysis) {
            if (Objects.isNull(analysis)) {
                return null;
            } else {
                return new JSFixedFastJsonAnalysis(
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

        public JSFixedFastJsonAnalysis() {
        }

        public JSFixedFastJsonAnalysis(String dataId, int dataType, Object value) {
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
            return "JSFixedFastJsonAnalysis{" +
                    "dataId='" + dataId + '\'' +
                    ", dataType=" + dataType +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * JSFixed FastJson 分析结果图片。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class JSFixedFastJsonAnalysisPicture implements Dto {

        private static final long serialVersionUID = 1781661881637304905L;

        public static JSFixedFastJsonAnalysisPicture of(AnalysisPicture analysisPicture) {
            if (Objects.isNull(analysisPicture)) {
                return null;
            } else {
                return new JSFixedFastJsonAnalysisPicture(
                        JSFixedFastJsonLongIdKey.of(analysisPicture.getAnalysisPictureInfoKey()),
                        analysisPicture.getOriginName(),
                        analysisPicture.getLength(),
                        analysisPicture.getRemark()
                );
            }
        }

        @JSONField(name = "analysis_picture_info_key", ordinal = 1)
        private JSFixedFastJsonLongIdKey analysisPictureInfoKey;

        @JSONField(name = "origin_name", ordinal = 2)
        private String originName;

        @JSONField(name = "length", ordinal = 3)
        private Long length;

        @JSONField(name = "remark", ordinal = 4)
        private String remark;

        public JSFixedFastJsonAnalysisPicture() {
        }

        public JSFixedFastJsonAnalysisPicture(
                JSFixedFastJsonLongIdKey analysisPictureInfoKey, String originName, Long length, String remark
        ) {
            this.analysisPictureInfoKey = analysisPictureInfoKey;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public JSFixedFastJsonLongIdKey getAnalysisPictureInfoKey() {
            return analysisPictureInfoKey;
        }

        public void setAnalysisPictureInfoKey(JSFixedFastJsonLongIdKey analysisPictureInfoKey) {
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
            return "JSFixedFastJsonAnalysisPicture{" +
                    "analysisPictureInfoKey=" + analysisPictureInfoKey +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * JSFixed FastJson 分析结果图片包。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class JSFixedFastJsonAnalysisPicturePack implements Dto {

        private static final long serialVersionUID = 679986389003860475L;

        public static JSFixedFastJsonAnalysisPicturePack of(AnalysisPicturePack analysisPicturePack) {
            if (Objects.isNull(analysisPicturePack)) {
                return null;
            } else {
                return new JSFixedFastJsonAnalysisPicturePack(
                        JSFixedFastJsonLongIdKey.of(analysisPicturePack.getAnalysisPicturePackKey()),
                        analysisPicturePack.getItemAnchorIndex(),
                        analysisPicturePack.getRemark(),
                        Optional.ofNullable(analysisPicturePack.getItems()).map(
                                f -> f.stream().map(JSFixedFastJsonAnalysisPicturePackItem::of).collect(Collectors.toList())
                        ).orElse(null)
                );
            }
        }

        @JSONField(name = "analysis_picture_pack_key", ordinal = 1)
        private JSFixedFastJsonLongIdKey analysisPicturePackKey;

        @JSONField(name = "item_anchor_index", ordinal = 2)
        private int itemAnchorIndex;

        @JSONField(name = "remark", ordinal = 3)
        private String remark;

        @JSONField(name = "items", ordinal = 4)
        private List<JSFixedFastJsonAnalysisPicturePackItem> items;

        public JSFixedFastJsonAnalysisPicturePack() {
        }

        public JSFixedFastJsonAnalysisPicturePack(
                JSFixedFastJsonLongIdKey analysisPicturePackKey, int itemAnchorIndex, String remark,
                List<JSFixedFastJsonAnalysisPicturePackItem> items
        ) {
            this.analysisPicturePackKey = analysisPicturePackKey;
            this.itemAnchorIndex = itemAnchorIndex;
            this.remark = remark;
            this.items = items;
        }

        public JSFixedFastJsonLongIdKey getAnalysisPicturePackKey() {
            return analysisPicturePackKey;
        }

        public void setAnalysisPicturePackKey(JSFixedFastJsonLongIdKey analysisPicturePackKey) {
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

        public List<JSFixedFastJsonAnalysisPicturePackItem> getItems() {
            return items;
        }

        public void setItems(List<JSFixedFastJsonAnalysisPicturePackItem> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "JSFixedFastJsonAnalysisPicturePack{" +
                    "analysisPicturePackKey=" + analysisPicturePackKey +
                    ", itemAnchorIndex=" + itemAnchorIndex +
                    ", remark='" + remark + '\'' +
                    ", items=" + items +
                    '}';
        }
    }

    /**
     * JSFixed FastJson 分析结果图片包条目。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class JSFixedFastJsonAnalysisPicturePackItem implements Dto {

        private static final long serialVersionUID = -4030355873153176876L;

        public static JSFixedFastJsonAnalysisPicturePackItem of(AnalysisPicturePackItem analysisPicturePackItem) {
            if (Objects.isNull(analysisPicturePackItem)) {
                return null;
            } else {
                return new JSFixedFastJsonAnalysisPicturePackItem(
                        JSFixedFastJsonLongIdKey.of(analysisPicturePackItem.getAnalysisPicturePackItemInfoKey()),
                        analysisPicturePackItem.getIndex(),
                        analysisPicturePackItem.getOriginName(),
                        analysisPicturePackItem.getLength(),
                        analysisPicturePackItem.getRemark()
                );
            }
        }

        @JSONField(name = "analysis_picture_pack_item_info_key", ordinal = 1)
        private JSFixedFastJsonLongIdKey analysisPicturePackItemInfoKey;

        @JSONField(name = "index", ordinal = 2)
        private int index;

        @JSONField(name = "origin_name", ordinal = 3)
        private String originName;

        @JSONField(name = "length", ordinal = 4)
        private Long length;

        @JSONField(name = "remark", ordinal = 5)
        private String remark;

        public JSFixedFastJsonAnalysisPicturePackItem() {
        }

        public JSFixedFastJsonAnalysisPicturePackItem(
                JSFixedFastJsonLongIdKey analysisPicturePackItemInfoKey, int index, String originName, Long length,
                String remark
        ) {
            this.analysisPicturePackItemInfoKey = analysisPicturePackItemInfoKey;
            this.index = index;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public JSFixedFastJsonLongIdKey getAnalysisPicturePackItemInfoKey() {
            return analysisPicturePackItemInfoKey;
        }

        public void setAnalysisPicturePackItemInfoKey(JSFixedFastJsonLongIdKey analysisPicturePackItemInfoKey) {
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
            return "JSFixedFastJsonAnalysisPicturePackItem{" +
                    "analysisPicturePackItemInfoKey=" + analysisPicturePackItemInfoKey +
                    ", index=" + index +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * JSFixed FastJson 分析结果文件。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class JSFixedFastJsonAnalysisFile implements Dto {

        private static final long serialVersionUID = -8506981036866916862L;

        public static JSFixedFastJsonAnalysisFile of(AnalysisFile analysisFile) {
            if (Objects.isNull(analysisFile)) {
                return null;
            } else {
                return new JSFixedFastJsonAnalysisFile(
                        JSFixedFastJsonLongIdKey.of(analysisFile.getAnalysisFileInfoKey()),
                        analysisFile.getOriginName(),
                        analysisFile.getLength(),
                        analysisFile.getRemark()
                );
            }
        }

        @JSONField(name = "analysis_file_info_key", ordinal = 1)
        private JSFixedFastJsonLongIdKey analysisFileInfoKey;

        @JSONField(name = "origin_name", ordinal = 2)
        private String originName;

        @JSONField(name = "length", ordinal = 3)
        private Long length;

        @JSONField(name = "remark", ordinal = 4)
        private String remark;

        public JSFixedFastJsonAnalysisFile() {
        }

        public JSFixedFastJsonAnalysisFile(
                JSFixedFastJsonLongIdKey analysisFileInfoKey, String originName, Long length, String remark
        ) {
            this.analysisFileInfoKey = analysisFileInfoKey;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public JSFixedFastJsonLongIdKey getAnalysisFileInfoKey() {
            return analysisFileInfoKey;
        }

        public void setAnalysisFileInfoKey(JSFixedFastJsonLongIdKey analysisFileInfoKey) {
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
            return "JSFixedFastJsonAnalysisFile{" +
                    "analysisFileInfoKey=" + analysisFileInfoKey +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * JSFixed FastJson 分析结果文件包。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class JSFixedFastJsonAnalysisFilePack implements Dto {

        private static final long serialVersionUID = -955338884304933679L;

        public static JSFixedFastJsonAnalysisFilePack of(AnalysisFilePack analysisFilePack) {
            if (Objects.isNull(analysisFilePack)) {
                return null;
            } else {
                return new JSFixedFastJsonAnalysisFilePack(
                        JSFixedFastJsonLongIdKey.of(analysisFilePack.getAnalysisFilePackKey()),
                        analysisFilePack.getItemAnchorIndex(),
                        analysisFilePack.getRemark(),
                        Optional.ofNullable(analysisFilePack.getItems()).map(
                                f -> f.stream().map(JSFixedFastJsonAnalysisFilePackItem::of).collect(Collectors.toList())
                        ).orElse(null)
                );
            }
        }

        @JSONField(name = "analysis_file_pack_key", ordinal = 1)
        private JSFixedFastJsonLongIdKey analysisFilePackKey;

        @JSONField(name = "item_anchor_index", ordinal = 2)
        private int itemAnchorIndex;

        @JSONField(name = "remark", ordinal = 3)
        private String remark;

        @JSONField(name = "items", ordinal = 4)
        private List<JSFixedFastJsonAnalysisFilePackItem> items;

        public JSFixedFastJsonAnalysisFilePack() {
        }

        public JSFixedFastJsonAnalysisFilePack(
                JSFixedFastJsonLongIdKey analysisFilePackKey, int itemAnchorIndex, String remark,
                List<JSFixedFastJsonAnalysisFilePackItem> items
        ) {
            this.analysisFilePackKey = analysisFilePackKey;
            this.itemAnchorIndex = itemAnchorIndex;
            this.remark = remark;
            this.items = items;
        }

        public JSFixedFastJsonLongIdKey getAnalysisFilePackKey() {
            return analysisFilePackKey;
        }

        public void setAnalysisFilePackKey(JSFixedFastJsonLongIdKey analysisFilePackKey) {
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

        public List<JSFixedFastJsonAnalysisFilePackItem> getItems() {
            return items;
        }

        public void setItems(List<JSFixedFastJsonAnalysisFilePackItem> items) {
            this.items = items;
        }

        @Override
        public String toString() {
            return "JSFixedFastJsonAnalysisFilePack{" +
                    "analysisFilePackKey=" + analysisFilePackKey +
                    ", itemAnchorIndex=" + itemAnchorIndex +
                    ", remark='" + remark + '\'' +
                    ", items=" + items +
                    '}';
        }
    }

    /**
     * JSFixed FastJson 分析结果图片包条目。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class JSFixedFastJsonAnalysisFilePackItem implements Dto {

        private static final long serialVersionUID = 6740618933194452264L;

        public static JSFixedFastJsonAnalysisFilePackItem of(AnalysisFilePackItem analysisFilePackItem) {
            if (Objects.isNull(analysisFilePackItem)) {
                return null;
            } else {
                return new JSFixedFastJsonAnalysisFilePackItem(
                        JSFixedFastJsonLongIdKey.of(analysisFilePackItem.getAnalysisFilePackItemInfoKey()),
                        analysisFilePackItem.getIndex(),
                        analysisFilePackItem.getOriginName(),
                        analysisFilePackItem.getLength(),
                        analysisFilePackItem.getRemark()
                );
            }
        }

        @JSONField(name = "analysis_file_pack_item_info_key", ordinal = 1)
        private JSFixedFastJsonLongIdKey analysisFilePackItemInfoKey;

        @JSONField(name = "index", ordinal = 2)
        private int index;

        @JSONField(name = "origin_name", ordinal = 3)
        private String originName;

        @JSONField(name = "length", ordinal = 4)
        private Long length;

        @JSONField(name = "remark", ordinal = 5)
        private String remark;

        public JSFixedFastJsonAnalysisFilePackItem() {
        }

        public JSFixedFastJsonAnalysisFilePackItem(
                JSFixedFastJsonLongIdKey analysisFilePackItemInfoKey, int index, String originName, Long length, String remark
        ) {
            this.analysisFilePackItemInfoKey = analysisFilePackItemInfoKey;
            this.index = index;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public JSFixedFastJsonLongIdKey getAnalysisFilePackItemInfoKey() {
            return analysisFilePackItemInfoKey;
        }

        public void setAnalysisFilePackItemInfoKey(JSFixedFastJsonLongIdKey analysisFilePackItemInfoKey) {
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
            return "JSFixedFastJsonAnalysisFilePackItem{" +
                    "analysisFilePackItemInfoKey=" + analysisFilePackItemInfoKey +
                    ", index=" + index +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    /**
     * JSFixed FastJson 分析结果。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    public static class JSFixedFastJsonJudgement implements Dto {

        private static final long serialVersionUID = 4725874889403375810L;

        public static JSFixedFastJsonJudgement of(Judgement judgement) {
            if (Objects.isNull(judgement)) {
                return null;
            } else {
                return new JSFixedFastJsonJudgement(
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

        public JSFixedFastJsonJudgement() {
        }

        public JSFixedFastJsonJudgement(String dataId, double value, String message) {
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
            return "JSFixedFastJsonJudgement{" +
                    "dataId='" + dataId + '\'' +
                    ", value=" + value +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    public static class JSFixedFastJsonVisualizeData implements Dto {

        private static final long serialVersionUID = -2538921542395919760L;

        public static JSFixedFastJsonVisualizeData of(VisualizeData visualizeData) {
            if (Objects.isNull(visualizeData)) {
                return null;
            } else {
                return new JSFixedFastJsonVisualizeData(
                        visualizeData.getPerspectiveStringId(),
                        visualizeData.getContent()
                );
            }
        }

        @JSONField(name = "perspective_string_id", ordinal = 1)
        private String perspectiveStringId;

        @JSONField(name = "content", ordinal = 2)
        private String content;

        public JSFixedFastJsonVisualizeData() {
        }

        public JSFixedFastJsonVisualizeData(String perspectiveStringId, String content) {
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
            return "JSFixedFastJsonVisualizeData{" +
                    "perspectiveStringId='" + perspectiveStringId + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
