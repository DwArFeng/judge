package com.dwarfeng.judge.node.assign.launcher;

import com.dwarfeng.judge.node.assign.handler.LauncherSettingHandler;
import com.dwarfeng.judge.stack.service.AssignQosService;
import com.dwarfeng.springterminator.stack.handler.Terminator;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since 1.4.2
 */
public class Launcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/application-context*.xml");
        ctx.registerShutdownHook();
        ctx.start();
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);
        // 指派是否开启指派服务。
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
        Terminator terminator = ctx.getBean(Terminator.class);
        System.exit(terminator.getExitCode());
    }
}
