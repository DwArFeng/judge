package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.service.SupportQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import org.apache.commons.cli.CommandLine;

@TelqosCommand
public class SupportCommand extends CliCommand {

    private static final String IDENTITY = "support";
    private static final String DESCRIPTION = "支持操作";

    private static final String[] CMD_LINE_ARRAY = new String[]{};

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final SupportQosService supportQosService;

    public SupportCommand(SupportQosService supportQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.supportQosService = supportQosService;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) {
    }
}
