package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.handler.Sinker;
import com.dwarfeng.judge.stack.handler.SinkerSession;
import com.dwarfeng.judge.stack.service.SinkerSessionQosService;
import com.dwarfeng.judge.stack.service.SinkerSessionQosService.SinkerSessionDescription;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class SinkerSessionCommand extends CliCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(SinkerSessionCommand.class);

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLOSE_AND_CLEAR = "cnc";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLOSE_AND_CLEAR
    };

    private static final String IDENTITY = "ss";
    private static final String DESCRIPTION = "下沉会话操作";

    private static final String CMD_LINE_SYNTAX_LOOKUP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " sinker-info-id";
    private static final String CMD_LINE_SYNTAX_CLOSE_AND_CLEAR = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLOSE_AND_CLEAR);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_LOOKUP,
            CMD_LINE_SYNTAX_CLOSE_AND_CLEAR
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final SinkerSessionQosService sinkerSessionQosService;

    public SinkerSessionCommand(SinkerSessionQosService sinkerSessionQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.sinkerSessionQosService = sinkerSessionQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_LOOKUP).desc("查询下沉会话").hasArg().type(Number.class).build());
        list.add(Option.builder(COMMAND_OPTION_CLOSE_AND_CLEAR).desc("关闭并清除下沉会话").build());
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
                case COMMAND_OPTION_LOOKUP:
                    handleLookup(context, cmd);
                    break;
                case COMMAND_OPTION_CLOSE_AND_CLEAR:
                    sinkerSessionQosService.closeAndClearHolding();
                    context.sendMessage("本地缓存已清除");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleLookup(Context context, CommandLine cmd) throws Exception {
        long sinkerInfoId;
        try {
            sinkerInfoId = ((Number) cmd.getParsedOptionValue(COMMAND_OPTION_LOOKUP)).longValue();
        } catch (ParseException e) {
            LOGGER.warn("解析命令选项时发生异常，异常信息如下", e);
            context.sendMessage("命令行格式错误，正确的格式为: " + CMD_LINE_SYNTAX_LOOKUP);
            context.sendMessage("请留意选项 " + COMMAND_OPTION_LOOKUP + " 后接参数的类型应该是数字 ");
            return;
        }
        LongIdKey sinkerInfoKey = new LongIdKey(sinkerInfoId);
        if (!sinkerSessionQosService.exists(sinkerInfoKey)) {
            context.sendMessage("not exists!");
            return;
        }
        SinkerSessionDescription description = sinkerSessionQosService.get(sinkerInfoKey);
        SinkerInfo sinkerInfo = description.getSinkerInfo();
        Sinker sinker = description.getSinker();
        SinkerSession sinkerSession = description.getSinkerSession();
        context.sendMessage(String.format("sinkerInfo: %s", sinkerInfo));
        context.sendMessage(String.format("sinker: %s", sinker));
        context.sendMessage(String.format("sinkerSession: %s", sinkerSession));
    }
}
