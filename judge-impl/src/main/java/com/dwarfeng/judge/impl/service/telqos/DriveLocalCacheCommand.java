package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.service.DriveQosService;
import com.dwarfeng.judge.stack.struct.DriveLocalCache;
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
import java.util.Map;
import java.util.Objects;

@TelqosCommand
public class DriveLocalCacheCommand extends CliCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriveLocalCacheCommand.class);

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLEAR = "c";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLEAR
    };

    private static final String IDENTITY = "dlc";
    private static final String DESCRIPTION = "驱动器本地缓存操作";

    private static final String CMD_LINE_SYNTAX_LOOKUP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " id";
    private static final String CMD_LINE_SYNTAX_CLEAR = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_LOOKUP,
            CMD_LINE_SYNTAX_CLEAR
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final DriveQosService driveQosService;

    public DriveLocalCacheCommand(DriveQosService driveQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.driveQosService = driveQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_LOOKUP).desc("查询驱动器").hasArg().type(Number.class).build());
        list.add(Option.builder(COMMAND_OPTION_CLEAR).desc("清除驱动器").build());
        return list;
    }

    @SuppressWarnings("DuplicatedCode")
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
                    driveQosService.clearLocalCache();
                    context.sendMessage("本地缓存已清除");
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
        DriveLocalCache driveLocalCache = driveQosService.getDriveLocalCache(new LongIdKey(sectionId));
        if (Objects.isNull(driveLocalCache)) {
            context.sendMessage("not exists!");
            return;
        }
        context.sendMessage(String.format("section: %s", driveLocalCache.getSection().toString()));
        context.sendMessage("");
        context.sendMessage("drivers:");
        int index = 0;
        for (Map.Entry<DriverInfo, Driver> entry : driveLocalCache.getDriverMap().entrySet()) {
            if (index != 0) {
                context.sendMessage("");
            }
            context.sendMessage(String.format("  %-3d %s", ++index, entry.getKey().toString()));
            context.sendMessage(String.format("  %-3d %s", index, entry.getValue().toString()));
        }
    }
}
