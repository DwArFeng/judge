package com.dwarfeng.judge.stack.bean;

import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * 消费者状态。
 *
 * @author DwArFeng
 * @since 1.4.1
 */
public class ConsumerStatus implements Bean {

    private static final long serialVersionUID = -285632142331336295L;

    private int bufferedSize;
    private int bufferSize;
    private int thread;
    private boolean idle;

    public ConsumerStatus() {
    }

    public ConsumerStatus(int bufferedSize, int bufferSize, int thread, boolean idle) {
        this.bufferedSize = bufferedSize;
        this.bufferSize = bufferSize;
        this.thread = thread;
        this.idle = idle;
    }

    public int getBufferedSize() {
        return bufferedSize;
    }

    public void setBufferedSize(int bufferedSize) {
        this.bufferedSize = bufferedSize;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    @Override
    public String toString() {
        return "ConsumerStatus{" +
                "bufferedSize=" + bufferedSize +
                ", bufferSize=" + bufferSize +
                ", thread=" + thread +
                ", idle=" + idle +
                '}';
    }
}
