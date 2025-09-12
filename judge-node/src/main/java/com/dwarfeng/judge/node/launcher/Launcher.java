package com.dwarfeng.judge.node.launcher;

import com.dwarfeng.judge.node.handler.LauncherSettingHandler;
import com.dwarfeng.judge.stack.service.ResetQosService;
import com.dwarfeng.judge.stack.service.SupportQosService;
import com.dwarfeng.judge.stack.service.TaskCheckQosService;
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

    public static void main(String[] args) {
        ApplicationUtil.launch(new String[]{
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            // 根据启动器设置处理器的设置，选择性重置分析器。
            mayResetAnalyser(ctx);

            // 根据启动器设置处理器的设置，选择性重置驱动器。
            mayResetDriver(ctx);

            // 根据启动器设置处理器的设置，选择性重置判断器。
            mayResetJudger(ctx);

            // 根据启动器设置处理器的设置，选择性开启重置服务。
            mayStartReset(ctx);

            // 根据启动器设置处理器的设置，选择性上线任务检查服务。
            mayOnlineTaskCheck(ctx);
            // 根据启动器设置处理器的设置，选择性启动任务检查服务。
            mayEnableTaskCheck(ctx);
        });
    }

    private static void mayResetAnalyser(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 如果不重置分析器，则返回。
        if (!launcherSettingHandler.isResetAnalyserSupport()) {
            return;
        }

        // 重置分析器支持。
        LOGGER.info("重置分析器支持...");
        SupportQosService supportQosService = ctx.getBean(SupportQosService.class);
        try {
            supportQosService.resetAnalyser();
        } catch (ServiceException e) {
            LOGGER.warn("分析器支持重置失败，异常信息如下", e);
        }
    }

    private static void mayResetDriver(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 如果不重置驱动器，则返回。
        if (!launcherSettingHandler.isResetDriverSupport()) {
            return;
        }

        // 重置驱动器支持。
        LOGGER.info("重置驱动器支持...");
        SupportQosService supportQosService = ctx.getBean(SupportQosService.class);
        try {
            supportQosService.resetDriver();
        } catch (ServiceException e) {
            LOGGER.warn("驱动器支持重置失败，异常信息如下", e);
        }
    }

    private static void mayResetJudger(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 如果不重置判断器，则返回。
        if (!launcherSettingHandler.isResetJudgerSupport()) {
            return;
        }

        // 重置判断器支持。
        LOGGER.info("重置判断器支持...");
        SupportQosService supportQosService = ctx.getBean(SupportQosService.class);
        try {
            supportQosService.resetJudger();
        } catch (ServiceException e) {
            LOGGER.warn("判断器支持重置失败，异常信息如下", e);
        }
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

    private static void mayOnlineTaskCheck(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 获取任务检查 QOS 服务。
        TaskCheckQosService taskCheckQosService = ctx.getBean(TaskCheckQosService.class);

        // 判断任务检查处理器是否上线任务检查服务，并按条件执行不同的操作。
        long onlineTaskCheckDelay = launcherSettingHandler.getOnlineTaskCheckDelay();
        if (onlineTaskCheckDelay == 0) {
            LOGGER.info("立即上线任务检查服务...");
            try {
                taskCheckQosService.online();
            } catch (ServiceException e) {
                LOGGER.error("无法上线任务检查服务，异常原因如下", e);
            }
        } else if (onlineTaskCheckDelay > 0) {
            LOGGER.info("{} 毫秒后上线任务检查服务...", onlineTaskCheckDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("上线任务检查服务...");
                        try {
                            taskCheckQosService.online();
                        } catch (ServiceException e) {
                            LOGGER.error("无法上线任务检查服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + onlineTaskCheckDelay)
            );
        }
    }

    private static void mayEnableTaskCheck(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 获取任务检查 QOS 服务。
        TaskCheckQosService taskCheckQosService = ctx.getBean(TaskCheckQosService.class);

        // 判断任务检查处理器是否启动任务检查服务，并按条件执行不同的操作。
        long enableTaskCheckDelay = launcherSettingHandler.getEnableTaskCheckDelay();
        if (enableTaskCheckDelay == 0) {
            LOGGER.info("立即启动任务检查服务...");
            try {
                taskCheckQosService.start();
            } catch (ServiceException e) {
                LOGGER.error("无法启动任务检查服务，异常原因如下", e);
            }
        } else if (enableTaskCheckDelay > 0) {
            LOGGER.info("{} 毫秒后启动任务检查服务...", enableTaskCheckDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("启动任务检查服务...");
                        try {
                            taskCheckQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error("无法启动任务检查服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + enableTaskCheckDelay)
            );
        }
    }
}
