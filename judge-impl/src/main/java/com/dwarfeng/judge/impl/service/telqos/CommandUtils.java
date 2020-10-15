package com.dwarfeng.judge.impl.service.telqos;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 指令工具类。
 *
 * @author DwArFeng
 * @since 1.4.1
 */
class CommandUtils {

    public static List<Option> buildOfOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder("online").optionalArg(true).hasArg(true).desc("上线服务").build());
        list.add(Option.builder("offline").optionalArg(true).hasArg(true).desc("下线服务").build());
        return list;
    }

    public static List<Option> buildLcOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder("c").optionalArg(true).hasArg(false).desc("清除缓存").build());
        list.add(Option.builder("s").optionalArg(true).hasArg(true).type(Number.class).argName("section-id")
                .desc("查看指定部件的详细信息，如果本地缓存中不存在，则尝试抓取").build());
        return list;
    }

    public static List<Option> buildCsuOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder("l").optionalArg(true).hasArg(false).desc("查看记录者状态").build());
        list.add(Option.builder("s").optionalArg(true).hasArg(false).desc("设置记录者参数").build());
        list.add(Option.builder("b").optionalArg(true).hasArg(true).type(Number.class)
                .argName("buffer-size").desc("缓冲器的大小").build());
        list.add(Option.builder("t").optionalArg(true).hasArg(true).type(Number.class)
                .argName("thread").desc("记录者的线程数量").build());
        return list;
    }

    public static Pair<String, Integer> analyseOfCommand(CommandLine cmd) {
        int i = 0;
        String subCmd = null;
        if (cmd.hasOption("online")) {
            i++;
            subCmd = "online";
        }
        if (cmd.hasOption("offline")) {
            i++;
            subCmd = "offline";
        }
        return Pair.of(subCmd, i);
    }


    public static Pair<String, Integer> analyseLcCommand(CommandLine cmd) {
        int i = 0;
        String subCmd = null;
        if (cmd.hasOption("c")) {
            i++;
            subCmd = "c";
        }
        if (cmd.hasOption("s")) {
            i++;
            subCmd = "s";
        }
        return Pair.of(subCmd, i);
    }

    public static Pair<String, Integer> analyseCsuCommand(CommandLine cmd) {
        int i = 0;
        String subCmd = null;
        if (cmd.hasOption("l")) {
            i++;
            subCmd = "l";
        }
        if (cmd.hasOption("s")) {
            i++;
            subCmd = "s";
        }
        return Pair.of(subCmd, i);
    }

    private CommandUtils() {
        throw new IllegalArgumentException("禁止实例化");
    }
}
