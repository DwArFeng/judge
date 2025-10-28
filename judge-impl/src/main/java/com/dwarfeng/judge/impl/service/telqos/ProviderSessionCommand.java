package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.judge.stack.handler.Provider;
import com.dwarfeng.judge.stack.handler.ProviderSession;
import com.dwarfeng.judge.stack.service.ProviderSessionQosService;
import com.dwarfeng.judge.stack.service.ProviderSessionQosService.ProviderSessionDescription;
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
public class ProviderSessionCommand extends CliCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderSessionCommand.class);

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLOSE_AND_CLEAR = "cnc";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLOSE_AND_CLEAR
    };

    private static final String IDENTITY = "ps";
    private static final String DESCRIPTION = "提供器会话操作";

    private static final String CMD_LINE_SYNTAX_LOOKUP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " provider-info-id";
    private static final String CMD_LINE_SYNTAX_CLOSE_AND_CLEAR = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLOSE_AND_CLEAR);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_LOOKUP,
            CMD_LINE_SYNTAX_CLOSE_AND_CLEAR
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final ProviderSessionQosService providerSessionQosService;

    public ProviderSessionCommand(ProviderSessionQosService providerSessionQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.providerSessionQosService = providerSessionQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_LOOKUP).desc("查询提供器会话").hasArg().type(Number.class).build());
        list.add(Option.builder(COMMAND_OPTION_CLOSE_AND_CLEAR).desc("关闭并清除提供器会话").build());
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
                    providerSessionQosService.closeAndClearHolding();
                    context.sendMessage("本地缓存已清除");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleLookup(Context context, CommandLine cmd) throws Exception {
        long providerInfoId;
        try {
            providerInfoId = ((Number) cmd.getParsedOptionValue(COMMAND_OPTION_LOOKUP)).longValue();
        } catch (ParseException e) {
            LOGGER.warn("解析命令选项时发生异常，异常信息如下", e);
            context.sendMessage("命令行格式错误，正确的格式为: " + CMD_LINE_SYNTAX_LOOKUP);
            context.sendMessage("请留意选项 " + COMMAND_OPTION_LOOKUP + " 后接参数的类型应该是数字 ");
            return;
        }
        LongIdKey providerInfoKey = new LongIdKey(providerInfoId);
        if (!providerSessionQosService.exists(providerInfoKey)) {
            context.sendMessage("not exists!");
            return;
        }
        ProviderSessionDescription description = providerSessionQosService.get(providerInfoKey);
        ProviderInfo providerInfo = description.getProviderInfo();
        Provider provider = description.getProvider();
        ProviderSession providerSession = description.getProviderSession();
        context.sendMessage(String.format("providerInfo: %s", providerInfo));
        context.sendMessage(String.format("provider: %s", provider));
        context.sendMessage(String.format("providerSession: %s", providerSession));
    }
}
