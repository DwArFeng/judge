package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.service.SupportQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class SupportCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "support";

    // region 指令选项

    private static final String COMMAND_OPTION_RESET_ANALYSER = "reset-analyser";
    private static final String COMMAND_OPTION_RESET_DRIVER = "reset-driver";
    private static final String COMMAND_OPTION_RESET_JUDGER = "reset-judger";
    private static final String COMMAND_OPTION_RESET_SINKER = "reset-sinker";
    private static final String COMMAND_OPTION_RESET_PROVIDER = "reset-provider";
    private static final String COMMAND_OPTION_RESET_VISUALIZER = "reset-visualizer";
    private static final String COMMAND_OPTION_RESET_ADAPTER = "reset-adapter";
    private static final String COMMAND_OPTION_RESET_FILTER = "reset-filter";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_RESET_ANALYSER,
            COMMAND_OPTION_RESET_DRIVER,
            COMMAND_OPTION_RESET_JUDGER,
            COMMAND_OPTION_RESET_SINKER,
            COMMAND_OPTION_RESET_PROVIDER,
            COMMAND_OPTION_RESET_VISUALIZER,
            COMMAND_OPTION_RESET_ADAPTER,
            COMMAND_OPTION_RESET_FILTER
    };

    // endregion

    private final SupportQosService supportQosService;

    public SupportCommand(SupportQosService supportQosService) {
        super(IDENTITY);
        this.supportQosService = supportQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "支持操作";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_ANALYSER),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_DRIVER),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_JUDGER),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_SINKER),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_PROVIDER),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_VISUALIZER),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_ADAPTER),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_FILTER)
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_ANALYSER).optionalArg(true).hasArg(false)
                .desc("重置分析器支持").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_DRIVER).optionalArg(true).hasArg(false)
                .desc("重置驱动器支持").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_JUDGER).optionalArg(true).hasArg(false)
                .desc("重置判断器支持").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_SINKER).optionalArg(true).hasArg(false)
                .desc("重置下沉器").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_PROVIDER).optionalArg(true).hasArg(false)
                .desc("重置提供器").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_VISUALIZER).optionalArg(true).hasArg(false)
                .desc("重置可视化器支持").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_ADAPTER).optionalArg(true).hasArg(false)
                .desc("重置适配器").build());
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_FILTER).optionalArg(true).hasArg(false)
                .desc("重置过滤器").build());
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
            case COMMAND_OPTION_RESET_ANALYSER:
                supportQosService.resetAnalyser();
                context.sendMessage("重置分析器支持成功。");
                break;
            case COMMAND_OPTION_RESET_DRIVER:
                supportQosService.resetDriver();
                context.sendMessage("重置驱动器支持成功。");
                break;
            case COMMAND_OPTION_RESET_JUDGER:
                supportQosService.resetJudger();
                context.sendMessage("重置判断器支持成功。");
                break;
            case COMMAND_OPTION_RESET_SINKER:
                supportQosService.resetSinker();
                context.sendMessage("重置下沉器成功。");
                break;
            case COMMAND_OPTION_RESET_PROVIDER:
                supportQosService.resetProvider();
                context.sendMessage("重置提供器成功。");
                break;
            case COMMAND_OPTION_RESET_VISUALIZER:
                supportQosService.resetVisualizer();
                context.sendMessage("重置可视化器支持成功。");
                break;
            case COMMAND_OPTION_RESET_ADAPTER:
                supportQosService.resetAdapter();
                context.sendMessage("重置适配器成功。");
                break;
            case COMMAND_OPTION_RESET_FILTER:
                supportQosService.resetFilter();
                context.sendMessage("重置过滤器成功。");
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }
}
