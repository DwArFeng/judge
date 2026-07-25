package com.dwarfeng.judge.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.dutil.basic.cna.CollectionUtil;
import com.dwarfeng.judge.stack.bean.dto.ProvideLookupInfo;
import com.dwarfeng.judge.stack.bean.dto.ProvideLookupResult;
import com.dwarfeng.judge.stack.service.ProvideQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;

/**
 * 提供指令。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@TelqosCommand
public class ProvideCommand extends CliCommand {

    private static final String COMMAND_OPTION_LOOKUP = "l";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    private static final String IDENTITY = "provide";
    private final ProvideQosService provideQosService;

    public ProvideCommand(ProvideQosService provideQosService) {
        super(IDENTITY);
        this.provideQosService = provideQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "提供服务";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]"
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_LOOKUP).desc("单次查询").build());
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
            case COMMAND_OPTION_LOOKUP:
                handleLookup(context, cmd);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleLookup(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        InputProvideLookupInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 InputProvideLookupInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = cmd.getOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, InputProvideLookupInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 InputProvideLookupInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File file = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                info = JSON.parseObject(inputStream, InputProvideLookupInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 参数校验。
        if (!checkInputProvideLookupInfoValid(context, info)) {
            return;
        }

        // 将输入信息解析为查询信息。
        ProvideLookupInfo provideLookupInfo = parseProvideLookupInfo(info);

        // 执行查询，获取结果。
        ProvideLookupResult provideLookupResult = provideQosService.lookup(provideLookupInfo);

        // 输出结果。
        printProvideLookupResult(context, provideLookupResult);
    }

    private boolean checkInputProvideLookupInfoValid(
            CommandExecutor.Context context, InputProvideLookupInfo info
    ) throws Exception {
        // 展开 InputProvideLookupInfo 参数。
        String preset = info.getPreset();
        String[] argTypes = info.getArgTypes();
        String[] argStrings = info.getArgStrings();

        // InputProvideLookupInfo 参数判断。
        if (Objects.isNull(preset)) {
            context.sendMessage("参数 preset 为 null");
            return false;
        }
        if (Objects.isNull(argTypes)) {
            context.sendMessage("参数 argsTypes 为 null");
            return false;
        }
        if (Objects.isNull(argStrings)) {
            context.sendMessage("参数 argStrings 为 null");
            return false;
        }
        if (argTypes.length != argStrings.length) {
            context.sendMessage("参数 argTypes 和 argStrings 长度不匹配");
            return false;
        }

        // 检查通过。
        return true;
    }

    private ProvideLookupInfo parseProvideLookupInfo(InputProvideLookupInfo info) {
        // 展开 InputProvideLookupInfo 参数。
        WebInputLongIdKey providerInfoKey = info.getProviderInfoKey();
        WebInputLongIdKey adapterInfoKey = info.getAdapterInfoKey();
        WebInputLongIdKey filterInfoKey = info.getFilterInfoKey();
        String preset = info.getPreset();
        String[] argTypes = info.getArgTypes();
        String[] argStrings = info.getArgStrings();

        // 解析 args。
        Object[] args = new Object[argTypes.length];
        for (int i = 0; i < args.length; i++) {
            ArgType argType;
            argType = ArgType.of(argTypes[i]);
            args[i] = argType.convert(argStrings[i]);
        }

        // 构造并返回结果。
        return new ProvideLookupInfo(
                WebInputLongIdKey.toStackBean(providerInfoKey),
                WebInputLongIdKey.toStackBean(adapterInfoKey),
                WebInputLongIdKey.toStackBean(filterInfoKey),
                preset,
                args
        );
    }

    private void printProvideLookupResult(CommandExecutor.Context context, ProvideLookupResult result)
            throws Exception {
        while (true) {
            context.sendMessage("输入 data 查看数据，输入 meta 查看元数据，输入 q 退出");
            String message = context.receiveMessage();
            if (Objects.isNull(message)) {
                context.sendMessage("输入格式错误");
                context.sendMessage("");
                continue;
            }

            switch (message.trim().toLowerCase(Locale.ROOT)) {
                case "q":
                    return;
                case "meta":
                    printStringObjectMap(context, result.getMeta(), "");
                    break;
                case "data":
                    printProvideLookupResultDatas(context, result.getDatas());
                    break;
                default:
                    context.sendMessage("输入格式错误");
                    context.sendMessage("");
                    break;
            }
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void printProvideLookupResultDatas(
            CommandExecutor.Context context, List<Map<String, Object>> datas
    ) throws Exception {
        if (Objects.isNull(datas) || datas.isEmpty()) {
            context.sendMessage("无数据");
            context.sendMessage("");
            return;
        }

        CliCommandUtil.CropResult cropResult = CliCommandUtil.cropData(
                context,
                datas,
                "输入 all 查看所有数据，输入 begin-end 查看指定范围的数据(开始于 0)"
        );
        if (cropResult.isExitFlag()) {
            return;
        }

        for (int i = cropResult.getBeginIndex(); i < cropResult.getEndIndex(); i++) {
            printProvideLookupResultItem(context, i, cropResult.getEndIndex(), datas.get(i));
        }
    }

    private void printProvideLookupResultItem(
            CommandExecutor.Context context, int i, int endIndex, Map<String, Object> data
    ) throws Exception {
        context.sendMessage(String.format("索引: %d/%d", i, endIndex));
        printStringObjectMap(context, data, "  ");
    }

    private void printStringObjectMap(
            CommandExecutor.Context context, Map<String, Object> map, String indent
    ) throws Exception {
        if (Objects.isNull(map)) {
            context.sendMessage(indent + "null");
            return;
        }

        List<String> keys = new ArrayList<>(map.size());
        for (String key : map.keySet()) {
            CollectionUtil.insertByOrder(keys, key, Comparator.naturalOrder());
        }
        for (String key : keys) {
            Object value = map.get(key);
            context.sendMessage(String.format("%skey: %s", indent, key));
            context.sendMessage(String.format(
                    "%s  valueClass: %s",
                    indent,
                    Objects.isNull(value) ? "null" : value.getClass().getCanonicalName()
            ));
            context.sendMessage(String.format("%s  value: %s", indent, value));
        }
    }

    private enum ArgType {

        LONG {
            @Override
            Object convert(String valueString) {
                return Objects.isNull(valueString) ? null : Long.parseLong(valueString);
            }
        },

        DOUBLE {
            @Override
            Object convert(String valueString) {
                return Objects.isNull(valueString) ? null : Double.parseDouble(valueString);
            }
        },

        BOOLEAN {
            @Override
            Object convert(String valueString) {
                return Objects.isNull(valueString) ? null : Boolean.parseBoolean(valueString);
            }
        },

        DATE {
            @Override
            Object convert(String valueString) {
                return Objects.isNull(valueString) ? null : new Date(Long.parseLong(valueString));
            }
        },

        STRING {
            @Override
            Object convert(String valueString) {
                return valueString;
            }
        };

        static ArgType of(String argType) {
            if (Objects.isNull(argType) || argType.trim().isEmpty()) {
                return STRING;
            }
            return ArgType.valueOf(argType.trim().toUpperCase(Locale.ROOT));
        }

        abstract Object convert(String valueString);
    }

    public static class InputProvideLookupInfo implements Dto {

        private static final long serialVersionUID = -5241451342772218527L;

        @JSONField(name = "provider_info_key")
        private WebInputLongIdKey providerInfoKey;

        @JSONField(name = "adapter_info_key")
        private WebInputLongIdKey adapterInfoKey;

        @JSONField(name = "filter_info_key")
        private WebInputLongIdKey filterInfoKey;

        @JSONField(name = "preset")
        private String preset;

        @JSONField(name = "arg_types")
        private String[] argTypes;

        @JSONField(name = "arg_strings")
        private String[] argStrings;

        public InputProvideLookupInfo() {
        }

        public WebInputLongIdKey getProviderInfoKey() {
            return providerInfoKey;
        }

        public void setProviderInfoKey(WebInputLongIdKey providerInfoKey) {
            this.providerInfoKey = providerInfoKey;
        }

        public WebInputLongIdKey getAdapterInfoKey() {
            return adapterInfoKey;
        }

        public void setAdapterInfoKey(WebInputLongIdKey adapterInfoKey) {
            this.adapterInfoKey = adapterInfoKey;
        }

        public WebInputLongIdKey getFilterInfoKey() {
            return filterInfoKey;
        }

        public void setFilterInfoKey(WebInputLongIdKey filterInfoKey) {
            this.filterInfoKey = filterInfoKey;
        }

        public String getPreset() {
            return preset;
        }

        public void setPreset(String preset) {
            this.preset = preset;
        }

        public String[] getArgTypes() {
            return argTypes;
        }

        public void setArgTypes(String[] argTypes) {
            this.argTypes = argTypes;
        }

        public String[] getArgStrings() {
            return argStrings;
        }

        public void setArgStrings(String[] argStrings) {
            this.argStrings = argStrings;
        }

        @Override
        public String toString() {
            return "InputProvideLookupInfo{" +
                    "providerInfoKey=" + providerInfoKey +
                    ", adapterInfoKey=" + adapterInfoKey +
                    ", filterInfoKey=" + filterInfoKey +
                    ", preset='" + preset + '\'' +
                    ", argTypes=" + Arrays.toString(argTypes) +
                    ", argStrings=" + Arrays.toString(argStrings) +
                    '}';
        }
    }
}
