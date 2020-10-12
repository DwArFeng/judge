package com.dwarfeng.judge.node.evaluate.launcher;

import com.dwarfeng.judge.node.evaluate.handler.LauncherSettingHandler;
import com.dwarfeng.judge.stack.service.EvaluateQosService;
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
            // 判断是否开启评估服务。
            long startEvaluateDelay = launcherSettingHandler.getStartEvaluateDelay();
            EvaluateQosService evaluateQosService = ctx.getBean(EvaluateQosService.class);
            if (startEvaluateDelay == 0) {
                LOGGER.info("立即启动评估服务...");
                try {
                    evaluateQosService.startEvaluate();
                } catch (ServiceException e) {
                    LOGGER.error("无法启动评估服务，异常原因如下", e);
                }
            } else if (startEvaluateDelay > 0) {
                LOGGER.info(startEvaluateDelay + " 毫秒后启动评估服务...");
                try {
                    Thread.sleep(startEvaluateDelay);
                } catch (InterruptedException ignored) {
                }
                LOGGER.info("启动评估服务...");
                try {
                    evaluateQosService.startEvaluate();
                } catch (ServiceException e) {
                    LOGGER.error("无法启动评估服务，异常原因如下", e);
                }
            }
        });
    }
}
