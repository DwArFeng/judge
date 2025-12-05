package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.service.SinkerBindingQosService;
import com.dwarfeng.judge.stack.struct.SinkerBinding;
import com.dwarfeng.judge.stack.struct.SinkerMetaInfo;
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

import java.util.*;
import java.util.stream.Collectors;

@TelqosCommand
public class SinkerBindingLocalCacheCommand extends CliCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(SinkerBindingLocalCacheCommand.class);

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLEAR = "c";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLEAR
    };

    @SuppressWarnings("SpellCheckingInspection")
    private static final String IDENTITY = "sblc";
    private static final String DESCRIPTION = "下沉绑定本地缓存操作";

    private static final String CMD_LINE_SYNTAX_LOOKUP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " sinker-info-id";
    private static final String CMD_LINE_SYNTAX_CLEAR = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_LOOKUP,
            CMD_LINE_SYNTAX_CLEAR
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final SinkerBindingQosService sinkerBindingQosService;

    public SinkerBindingLocalCacheCommand(SinkerBindingQosService sinkerBindingQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.sinkerBindingQosService = sinkerBindingQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_LOOKUP).desc("查询下沉绑定").hasArg().type(Number.class).build());
        list.add(Option.builder(COMMAND_OPTION_CLEAR).desc("清除下沉绑定本地缓存").build());
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
                case COMMAND_OPTION_CLEAR:
                    handleClear(context);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleLookup(Context context, CommandLine cmd) throws Exception {
        long sectionId;
        try {
            sectionId = ((Number) cmd.getParsedOptionValue(COMMAND_OPTION_LOOKUP)).longValue();
        } catch (ParseException e) {
            LOGGER.warn("解析命令选项时发生异常，异常信息如下", e);
            context.sendMessage("命令行格式错误，正确的格式为: " + CMD_LINE_SYNTAX_LOOKUP);
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

    private void processSinkerMetaInfoMapMapEntries(
            Context context, List<Map.Entry<LongIdKey, Map<String, SinkerMetaInfo>>> entries
    ) throws Exception {
        while (true) {
            CommandUtil.CropResult cropResult = CommandUtil.cropData(
                    context, entries, "数据总数: " + entries.size(), "输入 q 退出"
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

    private void printSinkerMetaInfoMapMapEntry(
            Context context, int i, int endIndex, Map.Entry<LongIdKey, Map<String, SinkerMetaInfo>> entry
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

    private void handleClear(Context context) throws Exception {
        sinkerBindingQosService.clearLocalCache();
        context.sendMessage("缓存已清空");
    }
}
