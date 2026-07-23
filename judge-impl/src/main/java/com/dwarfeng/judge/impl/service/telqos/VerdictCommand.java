package com.dwarfeng.judge.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.bean.dto.WebInputVerdictInspectBySectionInfo;
import com.dwarfeng.judge.sdk.bean.dto.WebInputVerdictUpdateByTaskInfo;
import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionInfo;
import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionResult;
import com.dwarfeng.judge.stack.bean.dto.VerdictUpdateByTaskInfo;
import com.dwarfeng.judge.stack.bean.entity.Verdict;
import com.dwarfeng.judge.stack.bean.key.VerdictKey;
import com.dwarfeng.judge.stack.service.VerdictQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 部件裁决结果 telqos 命令。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
@TelqosCommand
public class VerdictCommand extends CliCommand {

    private static final String IDENTITY = "verdict";

    private static final String COMMAND_OPTION_INSPECT = "i";
    private static final String COMMAND_OPTION_UPDATE = "u";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_UPDATE
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    private final VerdictQosService verdictQosService;

    public VerdictCommand(VerdictQosService verdictQosService) {
        super(IDENTITY);
        this.verdictQosService = verdictQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "部件裁决结果操作";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_UPDATE) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]"
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_INSPECT).desc("检查部件全部裁决结果").build());
        list.add(Option.builder(COMMAND_OPTION_UPDATE).desc("根据任务更新部件裁决结果").build());
        list.add(
                Option.builder(COMMAND_OPTION_JSON).desc("JSON 字符串").hasArg().type(String.class).build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_JSON_FILE).longOpt(COMMAND_OPTION_JSON_FILE_LONG_OPT).desc("JSON 文件")
                        .hasArg().type(File.class).build()
        );
        return list;
    }

    @Override
    protected void executeWithCmd(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        Pair<String, Integer> pair = CliCommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
        if (pair.getRight() != 1) {
            context.sendMessage(CliCommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
            context.sendMessage(context.getCommandManual(context.getRuntimeIdentity()));
            return;
        }
        switch (pair.getLeft()) {
            case COMMAND_OPTION_INSPECT:
                handleInspect(context, cmd);
                break;
            case COMMAND_OPTION_UPDATE:
                handleUpdate(context, cmd);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        WebInputVerdictInspectBySectionInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 WebInputVerdictInspectBySectionInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = cmd.getOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, WebInputVerdictInspectBySectionInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 WebInputVerdictInspectBySectionInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File file = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                info = JSON.parseObject(inputStream, WebInputVerdictInspectBySectionInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 将输入信息解析为查询信息。
        VerdictInspectBySectionInfo verdictInspectBySectionInfo = WebInputVerdictInspectBySectionInfo.toStackBean(info);

        // 执行查询，获取结果。
        VerdictInspectBySectionResult verdictInspectBySectionResult = verdictQosService.inspectBySection(
                verdictInspectBySectionInfo
        );

        // 输出结果。
        printVerdictInspectBySectionResult(context, verdictInspectBySectionResult);
    }

    private void handleUpdate(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        WebInputVerdictUpdateByTaskInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 WebInputVerdictUpdateByTaskInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = cmd.getOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, WebInputVerdictUpdateByTaskInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 WebInputVerdictUpdateByTaskInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File file = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                info = JSON.parseObject(inputStream, WebInputVerdictUpdateByTaskInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 将输入信息解析为更新信息。
        VerdictUpdateByTaskInfo verdictUpdateByTaskInfo = WebInputVerdictUpdateByTaskInfo.toStackBean(info);

        // 执行更新。
        verdictQosService.updateByTask(verdictUpdateByTaskInfo);
        context.sendMessage("裁决更新成功!");
    }

    private void printVerdictInspectBySectionResult(
            CommandExecutor.Context context, VerdictInspectBySectionResult result
    ) throws Exception {
        List<Verdict> verdicts = result.getVerdicts();
        if (Objects.isNull(verdicts) || verdicts.isEmpty()) {
            context.sendMessage("无数据");
            context.sendMessage("");
            return;
        }

        CliCommandUtil.CropResult cropResult = CliCommandUtil.cropData(
                context,
                verdicts,
                "输入 all 查看所有数据，输入 begin-end 查看指定范围的数据(开始于 0)"
        );
        if (cropResult.isExitFlag()) {
            return;
        }

        for (int i = cropResult.getBeginIndex(); i < cropResult.getEndIndex(); i++) {
            printVerdictInspectBySectionResultItem(context, i, cropResult.getEndIndex(), verdicts.get(i));
        }
    }

    private void printVerdictInspectBySectionResultItem(
            CommandExecutor.Context context, int i, int endIndex, Verdict verdict
    ) throws Exception {
        context.sendMessage(String.format("索引: %d/%d", i, endIndex));
        if (Objects.isNull(verdict)) {
            context.sendMessage("null");
            context.sendMessage("");
            return;
        }
        VerdictKey key = verdict.getKey();
        context.sendMessage("  key:");
        if (Objects.isNull(key)) {
            context.sendMessage("    null");
        } else {
            context.sendMessage(String.format("    sectionLongId: %s", key.getSectionLongId()));
            context.sendMessage(String.format("    dataStringId: %s", key.getDataStringId()));
        }
        context.sendMessage(String.format("  value: %s", verdict.getValue()));
        context.sendMessage(String.format("  message: %s", verdict.getMessage()));
        context.sendMessage("");
    }
}
