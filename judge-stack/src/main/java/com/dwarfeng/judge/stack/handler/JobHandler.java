package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.JobCreateInfo;
import com.dwarfeng.judge.stack.bean.dto.JobCreateResult;
import com.dwarfeng.judge.stack.bean.dto.JobExecuteInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.util.concurrent.CompletableFuture;

/**
 * 作业处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JobHandler extends Handler {

    /**
     * 创建作业。
     *
     * @param info 作业创建信息。
     * @return 作业创建结果。
     * @throws HandlerException 处理器异常。
     */
    JobCreateResult create(JobCreateInfo info) throws HandlerException;

    /**
     * 执行作业。
     *
     * <p>
     * 该方法调用后，会等待作业执行完成，然后返回。
     *
     * @param info 作业执行信息。
     * @throws HandlerException 处理器异常。
     */
    void execute(JobExecuteInfo info) throws HandlerException;

    /**
     * 异步执行作业。
     *
     * <p>
     * 该方法调用后，会立即返回一个 <code>CompletableFuture</code>。
     * 返回的 <code>CompletableFuture</code> 会在作业执行完成后完成。
     *
     * @param info 作业执行信息
     * @return 返回的 <code>CompletableFuture</code>。
     * @throws HandlerException 处理器异常。
     */
    CompletableFuture<Void> executeAsync(JobExecuteInfo info) throws HandlerException;
}
