package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.bean.ConsumerStatus;
import com.dwarfeng.judge.stack.service.JudgeQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class JudgeConsumerCommand extends CliCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(JudgeConsumerCommand.class);

    private static final String IDENTITY = "jcsu";
    private static final String DESCRIPTION = "消费者操作";
    private static final String CMD_LINE_SYNTAX_L = "jcsu -l";
    private static final String CMD_LINE_SYNTAX_S = "jcsu -s [-b val] [-t val]";
    private static final String CMD_LINE_SYNTAX = CMD_LINE_SYNTAX_L + System.lineSeparator() + CMD_LINE_SYNTAX_S;

    public JudgeConsumerCommand() {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
    }

    @Autowired
    private JudgeQosService judgeQosService;

    @Override
    protected List<Option> buildOptions() {
        return CommandUtils.buildCsuOptions();
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtils.analyseCsuCommand(cmd);
            if (pair.getRight() != 1) {
                context.sendMessage("下列选项必须且只能含有一个: -l -s");
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            switch (pair.getLeft()) {
                case "l":
                    handleL(context);
                    break;
                case "s":
                    handleS(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleL(Context context) throws Exception {
        printConsumerStatus(context);
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleS(Context context, CommandLine cmd) throws Exception {
        Integer newBufferSize = null;
        Integer newThread = null;
        try {
            if (cmd.hasOption("b")) newBufferSize = Integer.parseInt(cmd.getOptionValue("b"));
            if (cmd.hasOption("t")) newThread = Integer.parseInt(cmd.getOptionValue("t"));
        } catch (Exception e) {
            LOGGER.warn("解析命令选项时发生异常，异常信息如下", e);
            context.sendMessage("命令行格式错误，正确的格式为: " + CMD_LINE_SYNTAX_S);
            context.sendMessage("请留意选项 b,t 后接参数的类型应该是数字 ");
            return;
        }

        ConsumerStatus consumerStatus = judgeQosService.getConsumerStatus();
        int bufferSize = Objects.nonNull(newBufferSize) ? newBufferSize : consumerStatus.getBufferSize();
        int thread = Objects.nonNull(newThread) ? newThread : consumerStatus.getThread();
        judgeQosService.setConsumerParameters(bufferSize, thread);

        context.sendMessage("设置完成，消费者新的参数为: ");
        printConsumerStatus(context);
    }

    private void printConsumerStatus(Context context) throws ServiceException, TelqosException {
        ConsumerStatus consumerStatus = judgeQosService.getConsumerStatus();
        context.sendMessage(String.format("buffered-size:%-7d buffer-size:%-7d thread:%-3d idle:%b",
                consumerStatus.getBufferedSize(), consumerStatus.getBufferSize(), consumerStatus.getThread(),
                consumerStatus.isIdle())
        );
    }
}
