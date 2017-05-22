package com.king;

import com.king.core.Dependency;
import com.king.core.Parameter;
import com.king.core.ToolEntry;
import com.king.repository.ToolEntryRepository;
import com.king.utils.ParamType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2017/5/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ToolEntryServiceTest {
    @Autowired
    private ToolEntryRepository toolEntryRepository;

    @Test
    public void create() {
        ToolEntry entry = new ToolEntry();
        entry.setEntryId(1);
        entry.setEntryName("URL解码");

        List<Parameter> parameters = new ArrayList<Parameter>(){{
            add(new Parameter(ParamType.STRING, "s", "字符串", 1));
        }};
        entry.setParameters(parameters);

        List<Dependency> dependencies = new ArrayList<Dependency>(){{
        }};
        entry.setDependencies(dependencies);

        entry.setCode("\n" +
                "import java.io.UnsupportedEncodingException;\n" +
                "import java.net.URLDecoder;\n" +
                "public static String process(String s) throws Exception {\n" +
                "String s = URLDecoder.decode(s, \"utf8\");\n" +
                "return s;" +
                "}");

        toolEntryRepository.create(entry);
    }

    @Test
    public void getAll() {
        System.out.println(toolEntryRepository.getAll());
    }
}
