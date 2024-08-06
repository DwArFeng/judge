package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.bean.AssignInfo;
import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.service.AssignQosService;
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
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class AssignLocalCacheCommand extends CliCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssignLocalCacheCommand.class);

    private static final String IDENTITY = "alc";
    private static final String DESCRIPTION = "本地缓存操作";
    private static final String CMD_LINE_SYNTAX_C = "alc -c";
    private static final String CMD_LINE_SYNTAX_S = "alc -s section-id";
    private static final String CMD_LINE_SYNTAX = CMD_LINE_SYNTAX_C + System.lineSeparator() + CMD_LINE_SYNTAX_S;

    private final AssignQosService assignQosService;

    public AssignLocalCacheCommand(AssignQosService assignQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.assignQosService = assignQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        return CommandUtils.buildLcOptions();
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtils.analyseLcCommand(cmd);
            if (pair.getRight() != 1) {
                context.sendMessage("下列选项必须且只能含有一个: -c -s");
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            switch (pair.getLeft()) {
                case "c":
                    handleC(context);
                    break;
                case "s":
                    handleS(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleC(Context context) throws Exception {
        assignQosService.clearLocalCache();
        context.sendMessage("缓存已清空");
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleS(Context context, CommandLine cmd) throws Exception {
        long sectionId;
        try {
            sectionId = ((Number) cmd.getParsedOptionValue("s")).longValue();
        } catch (ParseException e) {
            LOGGER.warn("解析命令选项时发生异常，异常信息如下", e);
            context.sendMessage("命令行格式错误，正确的格式为: " + CMD_LINE_SYNTAX_S);
            context.sendMessage("请留意选项 s 后接参数的类型应该是数字 ");
            return;
        }
        AssignInfo assignInfo = assignQosService.getContext(new LongIdKey(sectionId));
        if (Objects.isNull(assignInfo)) {
            context.sendMessage("not exists!");
            return;
        }
        context.sendMessage(String.format("section: %s", assignInfo.getSection().toString()));
        context.sendMessage("");
        context.sendMessage("drivers:");
        int index = 0;
        for (Map.Entry<DriverInfo, Driver> entry : assignInfo.getDriverMap().entrySet()) {
            if (index != 0) {
                context.sendMessage("");
            }
            context.sendMessage(String.format("  %-3d %s", ++index, entry.getKey().toString()));
            context.sendMessage(String.format("  %-3d %s", index, entry.getValue().toString()));
        }
    }
}
