package com.dwarfeng.judge.impl.service.telqos;

import com.dwarfeng.judge.stack.bean.entity.AnalyserInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.judge.stack.handler.Analyser;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.Visualizer;
import com.dwarfeng.judge.stack.service.JobQosService;
import com.dwarfeng.judge.stack.struct.JobLocalCache;
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
import java.util.Objects;

@TelqosCommand
public class JobLocalCacheCommand extends CliCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobLocalCacheCommand.class);

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLEAR = "c";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLEAR
    };

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String IDENTITY = "jlc";
    private static final String DESCRIPTION = "作业器本地缓存操作";

    private static final String CMD_LINE_SYNTAX_LOOKUP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " id";
    private static final String CMD_LINE_SYNTAX_CLEAR = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_LOOKUP,
            CMD_LINE_SYNTAX_CLEAR
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final JobQosService jobQosService;

    public JobLocalCacheCommand(JobQosService jobQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.jobQosService = jobQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_LOOKUP).desc("查询作业本地缓存").hasArg().type(Number.class).build());
        list.add(Option.builder(COMMAND_OPTION_CLEAR).desc("清除作业本地缓存").build());
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
                    jobQosService.clearLocalCache();
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
        JobLocalCache jobLocalCache = jobQosService.getJobLocalCache(new LongIdKey(sectionId));
        if (Objects.isNull(jobLocalCache)) {
            context.sendMessage("not exists!");
            return;
        }
        context.sendMessage(String.format("section: %s", jobLocalCache.getSection().toString()));
        context.sendMessage("");
        context.sendMessage("analysers:");
        int index = 0;
        for (LongIdKey analyserInfoKey : jobLocalCache.getAnalyserInfoKeys()) {
            if (index != 0) {
                context.sendMessage("");
            }
            index++;
            AnalyserInfo analyserInfo = jobLocalCache.getAnalyserInfoMap().get(analyserInfoKey);
            Analyser analyser = jobLocalCache.getAnalyserMap().get(analyserInfoKey);
            context.sendMessage(String.format("  %-3d %s", index, analyserInfo));
            context.sendMessage(String.format("  %-3d %s", index, analyser));
        }
        context.sendMessage("judgers:");
        index = 0;
        for (LongIdKey judgerInfoKey : jobLocalCache.getJudgerInfoKeys()) {
            if (index != 0) {
                context.sendMessage("");
            }
            index++;
            JudgerInfo judgerInfo = jobLocalCache.getJudgerInfoMap().get(judgerInfoKey);
            Judger judger = jobLocalCache.getJudgerMap().get(judgerInfoKey);
            context.sendMessage(String.format("  %-3d %s", index, judgerInfo));
            context.sendMessage(String.format("  %-3d %s", index, judger));
        }
        context.sendMessage("visualizers:");
        index = 0;
        for (LongIdKey visualizerInfoKey : jobLocalCache.getVisualizerInfoKeys()) {
            if (index != 0) {
                context.sendMessage("");
            }
            index++;
            VisualizerInfo visualizerInfo = jobLocalCache.getVisualizerInfoMap().get(visualizerInfoKey);
            Visualizer visualizer = jobLocalCache.getVisualizerMap().get(visualizerInfoKey);
            context.sendMessage(String.format("  %-3d %s", index, visualizerInfo));
            context.sendMessage(String.format("  %-3d %s", index, visualizer));
        }
    }
}
