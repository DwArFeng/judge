package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.judge.stack.handler.Provider;
import com.dwarfeng.judge.stack.handler.ProviderSession;
import com.dwarfeng.judge.stack.service.ProviderSessionQosService;
import com.dwarfeng.judge.stack.service.ProviderSessionQosService.ProviderSessionDescription;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
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

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "ps";

    // region 指令选项

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLOSE_AND_CLEAR = "cnc";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLOSE_AND_CLEAR
    };

    // endregion

    private final ProviderSessionQosService providerSessionQosService;

    public ProviderSessionCommand(ProviderSessionQosService providerSessionQosService) {
        super(IDENTITY);
        this.providerSessionQosService = providerSessionQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "提供器会话操作";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " provider-info-id",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_CLOSE_AND_CLEAR)
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_LOOKUP).optionalArg(true).hasArg(true).type(Number.class)
                        .desc("查询提供器会话").build()
        );
        list.add(Option.builder(COMMAND_OPTION_CLOSE_AND_CLEAR).optionalArg(true).hasArg(false)
                .desc("关闭并清除提供器会话").build());
        return list;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected void executeWithCmd(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        Pair<String, Integer> pair = CliCommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
        if (pair.getRight() != 1) {
            context.sendMessage(CliCommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
            context.sendMessage(context.getCommandManual(context.getRuntimeIdentity()));
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
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleLookup(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        long providerInfoId;
        try {
            providerInfoId = ((Number) cmd.getParsedOptionValue(COMMAND_OPTION_LOOKUP)).longValue();
        } catch (ParseException e) {
            LOGGER.warn("解析命令选项时发生异常，异常信息如下", e);
            context.sendMessage("命令行格式错误，正确的格式为: " + context.getRuntimeIdentity() + " " +
                    CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " provider-info-id");
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
