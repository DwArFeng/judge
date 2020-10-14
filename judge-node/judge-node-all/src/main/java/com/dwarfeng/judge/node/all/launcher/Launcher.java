package com.dwarfeng.judge.node.all.launcher;

import com.dwarfeng.judge.node.all.handler.LauncherSettingHandler;
import com.dwarfeng.judge.stack.service.DriverSupportMaintainService;
import com.dwarfeng.judge.stack.service.JudgeQosService;
import com.dwarfeng.judge.stack.service.JudgerSupportMaintainService;
import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);
            //判断是否重置判断器和驱动器。
            if (launcherSettingHandler.isResetDriverSupport()) {
                LOGGER.info("重置驱动器支持...");
                DriverSupportMaintainService maintainService = ctx.getBean(DriverSupportMaintainService.class);
                try {
                    maintainService.reset();
                } catch (ServiceException e) {
                    LOGGER.warn("驱动器支持重置失败，异常信息如下", e);
                }
            }
            if (launcherSettingHandler.isResetJudgerSupport()) {
                LOGGER.info("重置判断器支持...");
                JudgerSupportMaintainService maintainService = ctx.getBean(JudgerSupportMaintainService.class);
                try {
                    maintainService.reset();
                } catch (ServiceException e) {
                    LOGGER.warn("判断器支持重置失败，异常信息如下", e);
                }
            }
            // 判断是否开启判断服务。
            long startJudgeDelay = launcherSettingHandler.getStartJudgeDelay();
            JudgeQosService judgeQosService = ctx.getBean(JudgeQosService.class);
            if (startJudgeDelay == 0) {
                LOGGER.info("立即启动判断服务...");
                try {
                    judgeQosService.start();
                } catch (ServiceException e) {
                    LOGGER.error("无法启动判断服务，异常原因如下", e);
                }
            } else if (startJudgeDelay > 0) {
                LOGGER.info(startJudgeDelay + " 毫秒后启动判断服务...");
                try {
                    Thread.sleep(startJudgeDelay);
                } catch (InterruptedException ignored) {
                }
                LOGGER.info("启动判断服务...");
                try {
                    judgeQosService.start();
                } catch (ServiceException e) {
                    LOGGER.error("无法启动判断服务，异常原因如下", e);
                }
            }
        });
    }
}
