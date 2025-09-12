package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;

/**
 * 分析结果图片插入或更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisPictureUpsertInfo implements Dto {

    private static final long serialVersionUID = 3049141120087374196L;

    private String originName;

    /**
     * 内容。
     *
     * <p>
     * 此处的内容是一个对象，其类型只能是如下类型之一：
     *
     * <ul>
     *     <li><code>byte[]</code></li>
     *     <li><code>AnalysisPictureUpsertInfo.FileStream</code></li>
     * </ul>
     *
     * <p>
     * 如果该值的类型是 <code>AnalysisPictureUpsertInfo.FileStream</code>，则不建议序列化该对象。<br>
     * 换而言之，<code>AnalysisPictureUpsertInfo.FileStream</code> 类型推荐在本地使用，不推荐作为 RPC 参数使用。
     *
     * <p>
     * 如果该值的类型是 <code>AnalysisPictureUpsertInfo.FileStream</code>，则调用方有义务在调用后关闭对象中的输入流，
     * 即 <code>AnalysisPictureUpsertInfo.FileStream.getInputStream()</code>。
     */
    private Object content;

    public AnalysisPictureUpsertInfo() {
    }

    public AnalysisPictureUpsertInfo(String originName, Object content) {
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
        return "AnalysisPictureUpsertInfo{" +
                "originName='" + originName + '\'' +
                ", content=" + content +
                '}';
    }

    public static class FileStream implements Dto {

        private static final long serialVersionUID = -7614303752587057620L;

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
