package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.service.AssignQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class AssignCommand extends CliCommand {

    private static final String COMMAND_OPTION_START = "start";
    private static final String COMMAND_OPTION_STOP = "stop";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_START,
            COMMAND_OPTION_STOP
    };

    private static final String IDENTITY = "assign";
    private static final String DESCRIPTION = "指派功能启动/停止";

    private static final String CMD_LINE_SYNTAX_START = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_START);
    private static final String CMD_LINE_SYNTAX_STOP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_STOP);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_START,
            CMD_LINE_SYNTAX_STOP
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final AssignQosService assignQosService;

    public AssignCommand(AssignQosService assignQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.assignQosService = assignQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_START).desc("启动服务").build());
        list.add(Option.builder(COMMAND_OPTION_STOP).desc("停止服务").build());
        return list;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
            if (pair.getRight() != 1) {
                context.sendMessage(CommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            switch (pair.getLeft()) {
                case COMMAND_OPTION_START:
                    assignQosService.start();
                    context.sendMessage("指派功能已启动!");
                    break;
                case COMMAND_OPTION_STOP:
                    assignQosService.stop();
                    context.sendMessage("指派功能已停止!");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }
}
