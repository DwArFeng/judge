package com.dwarfeng.judge.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.bean.dto.WebInputAlarmModalUpdateInfo;
import com.dwarfeng.judge.stack.bean.dto.AlarmModalUpdateInfo;
import com.dwarfeng.judge.stack.service.AlarmModalQosService;
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

/**
 * 报警模态 telqos 命令。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
@TelqosCommand
public class AlarmModalCommand extends CliCommand {

    private static final String IDENTITY = "alarm-modal";

    private static final String COMMAND_OPTION_UPDATE = "u";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_UPDATE
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    private final AlarmModalQosService alarmModalQosService;

    public AlarmModalCommand(AlarmModalQosService alarmModalQosService) {
        super(IDENTITY);
        this.alarmModalQosService = alarmModalQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "报警模态操作";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_UPDATE) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]"
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_UPDATE).desc("更新报警模态").build());
        list.add(
                Option.builder(COMMAND_OPTION_JSON).desc("JSON 字符串").hasArg().type(String.class).build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_JSON_FILE).longOpt(COMMAND_OPTION_JSON_FILE_LONG_OPT).desc("JSON 文件")
                        .hasArg().type(File.class).build()
        );
        return list;
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    protected void executeWithCmd(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        Pair<String, Integer> pair = CliCommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
        if (pair.getRight() != 1) {
            context.sendMessage(CliCommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
            context.sendMessage(context.getCommandManual(context.getRuntimeIdentity()));
            return;
        }
        switch (pair.getLeft()) {
            case COMMAND_OPTION_UPDATE:
                handleUpdate(context, cmd);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleUpdate(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        WebInputAlarmModalUpdateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 WebInputAlarmModalUpdateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = cmd.getOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, WebInputAlarmModalUpdateInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 WebInputAlarmModalUpdateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File file = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                info = JSON.parseObject(inputStream, WebInputAlarmModalUpdateInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 将输入信息解析为更新信息。
        AlarmModalUpdateInfo alarmModalUpdateInfo = WebInputAlarmModalUpdateInfo.toStackBean(info);

        // 执行更新。
        alarmModalQosService.updateModal(alarmModalUpdateInfo);
        context.sendMessage("报警模态更新成功!");
    }
}
