package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.service.SinkerBindingQosService;
import com.dwarfeng.judge.stack.struct.SinkerBinding;
import com.dwarfeng.judge.stack.struct.SinkerMetaInfo;
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

import java.util.*;
import java.util.stream.Collectors;

@TelqosCommand
public class SinkerBindingLocalCacheCommand extends CliCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(SinkerBindingLocalCacheCommand.class);

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "sblc";

    // region 指令选项

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLEAR = "c";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLEAR
    };

    // endregion

    private final SinkerBindingQosService sinkerBindingQosService;

    public SinkerBindingLocalCacheCommand(SinkerBindingQosService sinkerBindingQosService) {
        super(IDENTITY);
        this.sinkerBindingQosService = sinkerBindingQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "下沉绑定本地缓存操作";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " sinker-info-id",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR)
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_LOOKUP).optionalArg(true).hasArg(true).type(Number.class)
                        .desc("查询下沉绑定").build()
        );
        list.add(Option.builder(COMMAND_OPTION_CLEAR).optionalArg(true).hasArg(false).desc("清除下沉绑定本地缓存").build());
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
            case COMMAND_OPTION_CLEAR:
                handleClear(context);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleLookup(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        long sectionId;
        try {
            sectionId = ((Number) cmd.getParsedOptionValue(COMMAND_OPTION_LOOKUP)).longValue();
        } catch (ParseException e) {
            LOGGER.warn("解析命令选项时发生异常，异常信息如下", e);
            context.sendMessage("命令行格式错误，正确的格式为: " + context.getRuntimeIdentity() + " " +
                    CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " sinker-info-id");
            context.sendMessage("请留意选项 " + COMMAND_OPTION_LOOKUP + " 后接参数的类型应该是数字 ");
            return;
        }
        SinkerBinding sinkerBinding = sinkerBindingQosService.getSinkerBinding(new LongIdKey(sectionId));
        if (Objects.isNull(sinkerBinding)) {
            context.sendMessage("not exists!");
            return;
        }
        List<Map.Entry<LongIdKey, Map<String, SinkerMetaInfo>>> sinkerMetaInfoMapMapEntries =
                sinkerBinding.getMap().entrySet()
                        .stream()
                        .sorted(Comparator.comparing(entry -> entry.getKey().getLongId()))
                        .collect(Collectors.toList());
        processSinkerMetaInfoMapMapEntries(context, sinkerMetaInfoMapMapEntries);
    }

    @SuppressWarnings("DuplicatedCode")
    private void processSinkerMetaInfoMapMapEntries(
            CommandExecutor.Context context, List<Map.Entry<LongIdKey, Map<String, SinkerMetaInfo>>> entries
    ) throws Exception {
        while (true) {
            CliCommandUtil.CropResult cropResult = CliCommandUtil.cropData(
                    context, entries, "数据总数: " + entries.size()
            );
            if (cropResult.isExitFlag()) {
                break;
            }
            context.sendMessage("");
            for (int i = cropResult.getBeginIndex(); i < cropResult.getEndIndex(); i++) {
                Map.Entry<LongIdKey, Map<String, SinkerMetaInfo>> entry = entries.get(i);
                printSinkerMetaInfoMapMapEntry(context, i, cropResult.getEndIndex(), entry);
            }
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void printSinkerMetaInfoMapMapEntry(
            CommandExecutor.Context context, int i, int endIndex,
            Map.Entry<LongIdKey, Map<String, SinkerMetaInfo>> entry
    ) throws Exception {
        context.sendMessage(String.format("索引: %d/%d", i, endIndex));
        if (Objects.isNull(entry)) {
            context.sendMessage("null");
        } else {
            LongIdKey sectionKey = entry.getKey();
            Map<String, SinkerMetaInfo> sinkerMetaInfoMap = entry.getValue();
            context.sendMessage(String.format("sectionKey: %s", sectionKey));
            for (Map.Entry<String, SinkerMetaInfo> innerEntry : sinkerMetaInfoMap.entrySet()) {
                String metaId = innerEntry.getKey();
                SinkerMetaInfo sinkerMetaInfo = innerEntry.getValue();
                context.sendMessage(String.format("  metaId: %s", metaId));
                context.sendMessage(String.format("    meta: %s", sinkerMetaInfo.getMetaValue()));
                context.sendMessage(String.format("    default: %s", sinkerMetaInfo.getDefaultValue()));
                context.sendMessage(String.format("    equivalent: %s", sinkerMetaInfo.getEquivalentValue()));
            }
        }
        context.sendMessage("");
    }

    private void handleClear(CommandExecutor.Context context) throws Exception {
        sinkerBindingQosService.clearLocalCache();
        context.sendMessage("缓存已清空");
    }
}
