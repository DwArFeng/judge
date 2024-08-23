package com.dwarfeng.judge.node.launcher;

import com.dwarfeng.judge.node.handler.LauncherSettingHandler;
import com.dwarfeng.judge.stack.service.DriverSupportMaintainService;
import com.dwarfeng.judge.stack.service.JudgeQosService;
import com.dwarfeng.judge.stack.service.JudgerSupportMaintainService;
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
            // 根据启动器设置处理器的设置，选择性重置驱动器。
            mayResetDriver(ctx);

            // 根据启动器设置处理器的设置，选择性重置判断器。
            mayResetJudger(ctx);

            // 根据启动器设置处理器的设置，选择性启动判断服务。
            mayStartJudge(ctx);
        });
    }

    private static void mayResetDriver(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 判断是否重置驱动器支持，并按条件执行重置操作。
        if (launcherSettingHandler.isResetDriverSupport()) {
            LOGGER.info("重置驱动器支持...");
            DriverSupportMaintainService maintainService = ctx.getBean(DriverSupportMaintainService.class);
            try {
                maintainService.reset();
            } catch (ServiceException e) {
                LOGGER.warn("驱动器支持重置失败，异常信息如下", e);
            }
        }
    }

    private static void mayResetJudger(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 判断是否重置判断器支持，并按条件执行重置操作。
        if (launcherSettingHandler.isResetJudgerSupport()) {
            LOGGER.info("重置判断器支持...");
            JudgerSupportMaintainService maintainService = ctx.getBean(JudgerSupportMaintainService.class);
            try {
                maintainService.reset();
            } catch (ServiceException e) {
                LOGGER.warn("判断器支持重置失败，异常信息如下", e);
            }
        }
    }

    private static void mayStartJudge(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 获取判断 QOS 服务。
        JudgeQosService judgeQosService = ctx.getBean(JudgeQosService.class);

        // 判断判断处理器是否启动判断服务，并按条件执行不同的操作。
        long startJudgeDelay = launcherSettingHandler.getStartJudgeDelay();
        if (startJudgeDelay == 0) {
            LOGGER.info("立即启动判断服务...");
            try {
                judgeQosService.start();
            } catch (ServiceException e) {
                LOGGER.error("无法启动判断服务，异常原因如下", e);
            }
        } else if (startJudgeDelay > 0) {
            LOGGER.info("{} 毫秒后启动判断服务...", startJudgeDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("启动判断服务...");
                        try {
                            judgeQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error("无法启动判断服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + startJudgeDelay)
            );
        }
    }
}
