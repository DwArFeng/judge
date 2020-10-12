package com.dwarfeng.judge.node.assign.launcher;

import com.dwarfeng.judge.node.assign.handler.LauncherSettingHandler;
import com.dwarfeng.judge.stack.service.AssignQosService;
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
            // 判断是否开启指派服务。
            long startAssignDelay = launcherSettingHandler.getStartAssignDelay();
            AssignQosService assignQosService = ctx.getBean(AssignQosService.class);
            if (startAssignDelay == 0) {
                LOGGER.info("立即启动指派服务...");
                try {
                    assignQosService.startAssign();
                } catch (ServiceException e) {
                    LOGGER.error("无法启动指派服务，异常原因如下", e);
                }
            } else if (startAssignDelay > 0) {
                LOGGER.info(startAssignDelay + " 毫秒后启动指派服务...");
                try {
                    Thread.sleep(startAssignDelay);
                } catch (InterruptedException ignored) {
                }
                LOGGER.info("启动指派服务...");
                try {
                    assignQosService.startAssign();
                } catch (ServiceException e) {
                    LOGGER.error("无法启动指派服务，异常原因如下", e);
                }
            }
        });
    }
}
