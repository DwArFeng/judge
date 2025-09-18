package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.service.SupportQosService;
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
public class SupportCommand extends CliCommand {

    private static final String COMMAND_OPTION_RESET_ANALYSER = "reset-analyser";
    private static final String COMMAND_OPTION_RESET_DRIVER = "reset-driver";
    private static final String COMMAND_OPTION_RESET_JUDGER = "reset-judger";
    private static final String COMMAND_OPTION_RESET_SINKER = "reset-sinker";
    private static final String COMMAND_OPTION_RESET_PROVIDER = "reset-provider";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_RESET_ANALYSER,
            COMMAND_OPTION_RESET_DRIVER,
            COMMAND_OPTION_RESET_JUDGER,
            COMMAND_OPTION_RESET_SINKER,
            COMMAND_OPTION_RESET_PROVIDER
    };

    private static final String IDENTITY = "support";
    private static final String DESCRIPTION = "支持操作";

    private static final String CMD_LINE_SYNTAX_RESET_ANALYSER = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_ANALYSER);
    private static final String CMD_LINE_SYNTAX_RESET_DRIVER = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_DRIVER);
    private static final String CMD_LINE_SYNTAX_RESET_JUDGER = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_JUDGER);
    private static final String CMD_LINE_SYNTAX_RESET_SINKER = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_SINKER);
    private static final String CMD_LINE_SYNTAX_RESET_PROVIDER = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_PROVIDER);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_RESET_ANALYSER,
            CMD_LINE_SYNTAX_RESET_DRIVER,
            CMD_LINE_SYNTAX_RESET_JUDGER,
            CMD_LINE_SYNTAX_RESET_SINKER,
            CMD_LINE_SYNTAX_RESET_PROVIDER
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final SupportQosService supportQosService;

    public SupportCommand(SupportQosService supportQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.supportQosService = supportQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_ANALYSER).desc("重置分析器支持").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_DRIVER).desc("重置驱动器支持").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_JUDGER).desc("重置判断器支持").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_SINKER).desc("重置下沉器").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_PROVIDER).desc("重置提供器").build());
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
                case COMMAND_OPTION_RESET_ANALYSER:
                    supportQosService.resetAnalyser();
                    context.sendMessage("重置分析器支持成功。");
                    break;
                case COMMAND_OPTION_RESET_DRIVER:
                    supportQosService.resetDriver();
                    context.sendMessage("重置驱动器支持成功。");
                    break;
                case COMMAND_OPTION_RESET_JUDGER:
                    supportQosService.resetJudger();
                    context.sendMessage("重置判断器支持成功。");
                    break;
                case COMMAND_OPTION_RESET_SINKER:
                    supportQosService.resetSinker();
                    context.sendMessage("重置下沉器成功。");
                    break;
                case COMMAND_OPTION_RESET_PROVIDER:
                    supportQosService.resetProvider();
                    context.sendMessage("重置提供器成功。");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }
}
