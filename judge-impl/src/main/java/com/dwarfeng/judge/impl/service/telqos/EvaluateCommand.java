package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.service.EvaluateQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EvaluateCommand extends CliCommand {

    private static final String IDENTITY = "evaluate";
    private static final String DESCRIPTION = "评估功能上线/下线";
    private static final String CMD_LINE_SYNTAX_ONLINE = "evaluate -online";
    private static final String CMD_LINE_SYNTAX_OFFLINE = "evaluate -offline";
    private static final String CMD_LINE_SYNTAX = CMD_LINE_SYNTAX_ONLINE + System.lineSeparator() +
            CMD_LINE_SYNTAX_OFFLINE;

    private final EvaluateQosService evaluateQosService;

    public EvaluateCommand(EvaluateQosService evaluateQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.evaluateQosService = evaluateQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        return CommandUtils.buildOfOptions();
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtils.analyseOfCommand(cmd);
            if (pair.getRight() != 1) {
                context.sendMessage("下列选项必须且只能含有一个: -online -offline");
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            switch (pair.getLeft()) {
                case "online":
                    evaluateQosService.start();
                    context.sendMessage("评估功能已上线!");
                    break;
                case "offline":
                    evaluateQosService.stop();
                    context.sendMessage("评估功能已下线!");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }
}
