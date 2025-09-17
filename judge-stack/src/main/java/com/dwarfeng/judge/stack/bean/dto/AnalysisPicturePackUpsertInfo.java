package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;
import java.util.List;

/**
 * 分析结果图片包插入或更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPicturePackUpsertInfo implements Dto {

    private static final long serialVersionUID = 768892530295959580L;

    /**
     * 插入或更新类型。
     *
     * <p>
     * int 枚举，可能的状态为：
     * <ul>
     *     <li>追加</li>
     *     <li>替换</li>
     * </ul>
     * 详细值参考 sdk 模块的常量工具类。
     */
    private int upsertType;

    private List<Item> items;

    public AnalysisPicturePackUpsertInfo() {
    }

    public AnalysisPicturePackUpsertInfo(int upsertType, List<Item> items) {
        this.upsertType = upsertType;
        this.items = items;
    }

    public int getUpsertType() {
        return upsertType;
    }

    public void setUpsertType(int upsertType) {
        this.upsertType = upsertType;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "AnalysisPicturePackUpsertInfo{" +
                "upsertType=" + upsertType +
                ", items=" + items +
                '}';
    }

    public static class Item implements Dto {

        private static final long serialVersionUID = -7107013949785834719L;

        private String originName;

        /**
         * 内容。
         *
         * <p>
         * 此处的内容是一个对象，其类型只能是如下类型之一：
         *
         * <ul>
         *     <li><code>byte[]</code></li>
         *     <li><code>AnalysisPicturePackUpsertInfo.FileStream</code></li>
         * </ul>
         *
         * <p>
         * 如果该值的类型是 <code>AnalysisPicturePackUpsertInfo.FileStream</code>，则不建议序列化该对象。<br>
         * 换而言之，<code>AnalysisPicturePackUpsertInfo.FileStream</code> 类型推荐在本地使用，不推荐作为 RPC 参数使用。
         *
         * <p>
         * 如果该值的类型是 <code>AnalysisPicturePackUpsertInfo.FileStream</code>，
         * 则调用方有义务在调用后关闭对象中的输入流，
         * 即 <code>AnalysisPicturePackUpsertInfo.FileStream.getInputStream()</code>。
         */
        private Object content;

        public Item() {
        }

        public Item(String originName, Object content) {
            this.originName = originName;
            this.content = content;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "originName='" + originName + '\'' +
                    ", content=" + content +
                    '}';
        }
    }

    public static class FileStream implements Dto {

        private static final long serialVersionUID = 576825810252857640L;

        private long length;
        private InputStream inputStream;

        public FileStream() {
        }

        public FileStream(long length, InputStream inputStream) {
            this.length = length;
            this.inputStream = inputStream;
        }

        public long getLength() {
            return length;
        }

        public void setLength(long length) {
            this.length = length;
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        public void setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public String toString() {
            return "FileStream{" +
                    "length=" + length +
                    ", inputStream=" + inputStream +
                    '}';
        }
    }
}
