package com.dwarfeng.judge.node.maintain.launcher;

import com.dwarfeng.judge.node.maintain.handler.LauncherSettingHandler;
import com.dwarfeng.judge.stack.service.DriverSupportMaintainService;
import com.dwarfeng.judge.stack.service.JudgerSupportMaintainService;
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
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml");
        ctx.registerShutdownHook();
        ctx.start();
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
        Terminator terminator = ctx.getBean(Terminator.class);
        System.exit(terminator.getExitCode());
    }
}
