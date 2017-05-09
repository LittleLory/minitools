package com.king.core;

import com.king.exception.MvnBuildFailException;
import com.king.utils.ParamType;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2017/5/9.
 */
public class TotalTest {

    ToolEntry entry;
    @Before
    public void before() {
        entry  = new ToolEntry();
        entry.entryId = 1;
        entry.entryName = "加法器";

        List<Parameter> parameters = new ArrayList<Parameter>(){{
            add(new Parameter(ParamType.INT, "a", "A", 1));
            add(new Parameter(ParamType.INT, "b", "B", 1));
        }};
        entry.parameters = parameters;

        List<Dependency> dependencies = new ArrayList<Dependency>(){{
            add(new Dependency("com.alibaba", "fastjson", "1.2.31"));
        }};
        entry.dependencies = dependencies;

        entry.code = "import com.alibaba.fastjson.JSON;\n" +
                "import java.util.Date;\n" +
                "public static String process(Integer a,Integer b) throws Exception {\n" +
                "\tSystem.out.println(\"result: \" + (a+b));\n" +
                "\tSystem.out.println(\"time: \" + JSON.toJSONString(new Date()));\n" +
                "\treturn (a+b) + \"\";" +
                "}";

    }

    @Test
    public void 生成模板代码() {
        System.out.println(CodeProcess.createCodeTemplate(entry.parameters));
    }

    @Test
    public void 部署容器() throws IOException, MvnBuildFailException {
        ContainerManager.deploy(entry);
    }

    @Test
    public void 加载容器() throws Exception {
        ContainerManager.load(entry.getContainerName());

        String invokeResult = Invoke.invoke(entry.getContainerName(), entry.parameters);
        System.out.println(invokeResult);
    }
}
