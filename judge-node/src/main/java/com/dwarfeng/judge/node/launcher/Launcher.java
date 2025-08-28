package com.dwarfeng.judge.node.launcher;

import com.dwarfeng.judge.node.handler.LauncherSettingHandler;
import com.dwarfeng.judge.stack.service.ResetQosService;
import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since 1.4.2
 */
public class Launcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    @SuppressWarnings("Convert2MethodRef")
    public static void main(String[] args) {
        ApplicationUtil.launch(new String[]{
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            // 根据启动器设置处理器的设置，选择性开启重置服务。
            mayStartReset(ctx);
        });
    }

    private static void mayStartReset(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 处理重置处理器的启动选项。
        ResetQosService resetQosService = ctx.getBean(ResetQosService.class);

        // 重置处理器是否启动重置服务。
        long startResetDelay = launcherSettingHandler.getStartResetDelay();
        if (startResetDelay == 0) {
            LOGGER.info("立即启动重置服务...");
            try {
                resetQosService.start();
            } catch (ServiceException e) {
                LOGGER.error("无法启动重置服务，异常原因如下", e);
            }
        } else if (startResetDelay > 0) {
            LOGGER.info("{} 毫秒后启动重置服务...", startResetDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("启动重置服务...");
                        try {
                            resetQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error("无法启动重置服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + startResetDelay)
            );
        }
    }
}
