package com.dwarfeng.judge.impl.handler.judger;

import com.dwarfeng.judge.impl.handler.JudgerSupporter;
import org.springframework.stereotype.Component;

/**
 * Groovy判断器支持器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class GroovyJudgerSupporter implements JudgerSupporter {

    public static final String SUPPORT_TYPE = "groovy_judger";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "Groovy判断器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的Groovy脚本对数据进行判断。";
    }

    @Override
    public String provideExampleContent() {
        return "import com.dwarfeng.judge.impl.handler.judger.GroovyJudgerMaker\n" +
                "import com.dwarfeng.judge.stack.bean.dto.JudgedValue\n" +
                "import com.dwarfeng.judge.stack.bean.dto.JudgementInfo\n" +
                "import com.dwarfeng.judge.stack.exception.JudgerException\n" +
                "import com.dwarfeng.judge.stack.handler.RepositoryHandler\n" +
                "import com.dwarfeng.subgrade.stack.bean.key.LongIdKey\n" +
                "\n" +
                "/**\n" +
                " * 通过判启动判断时的系统时间对数据做出判断的脚本。\n" +
                " * <p> 取判断启动的系统时间，取模1000，得出的数据除以1000，得到介于0.0和1.0之间的数。\n" +
                " */\n" +
                "@SuppressWarnings(\"GrPackage\")\n" +
                "class ExampleJudgerProcessor implements GroovyJudgerMaker.Processor {\n" +
                "\n" +
                "    @Override\n" +
                "    JudgedValue judge(LongIdKey judgerInfoKey, RepositoryHandler repositoryHandler) throws JudgerException {\n" +
                "        def happenedDate = new Date();\n" +
                "        def value = (happenedDate.getTime() % 1000) / 1000;\n" +
                "        def judgementInfo = new JudgementInfo(value, Collections.emptyList(), Collections.emptyList());\n" +
                "        return new JudgedValue(judgerInfoKey, judgementInfo, happenedDate);\n" +
                "    }\n" +
                "}\n";
    }
}
