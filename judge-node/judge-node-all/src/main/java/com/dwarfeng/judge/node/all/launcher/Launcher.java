package com.dwarfeng.judge.node.all.launcher;

import com.dwarfeng.judge.node.all.handler.LauncherSettingHandler;
import com.dwarfeng.judge.stack.service.JudgeQosService;
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
//        //判断是否重置触发器和过滤器。
//        if (launcherSettingHandler.isResetFilterSupport()) {
//            LOGGER.info("重置过滤器支持...");
//            FilterSupportMaintainService maintainService = ctx.getBean(FilterSupportMaintainService.class);
//            try {
//                maintainService.reset();
//            } catch (ServiceException e) {
//                LOGGER.warn("触发器支持重置失败，异常信息如下", e);
//            }
//        }
//        if (launcherSettingHandler.isResetTriggerSupport()) {
//            LOGGER.info("重置触发器支持...");
//            TriggerSupportMaintainService maintainService = ctx.getBean(TriggerSupportMaintainService.class);
//            try {
//                maintainService.reset();
//            } catch (ServiceException e) {
//                LOGGER.warn("过滤器支持重置失败，异常信息如下", e);
//            }
//        }
        // 判断是否开启判断服务。
        long startJudgeDelay = launcherSettingHandler.getStartJudgeDelay();
        JudgeQosService judgeQosService = ctx.getBean(JudgeQosService.class);
        if (startJudgeDelay == 0) {
            LOGGER.info("立即启动判断服务...");
            try {
                judgeQosService.startJudge();
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
                judgeQosService.startJudge();
            } catch (ServiceException e) {
                LOGGER.error("无法启动判断服务，异常原因如下", e);
            }
        }
        Terminator terminator = ctx.getBean(Terminator.class);
        System.exit(terminator.getExitCode());
    }
}
