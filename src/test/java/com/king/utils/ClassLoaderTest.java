package com.king.utils;

import com.king.core.ClassContainer;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by king on 2017/4/26.
 */
public class ClassLoaderTest {
    @Test
    public void test() throws Exception {
        ClassContainer classLoader = new ClassContainer("contract");
        Class cls = classLoader.getClz("com.king.tool.MyTool");
        Object Hello = cls.newInstance();
        Method m = Hello.getClass().getMethod("process");
        m.invoke(Hello);
    }

    @Test
    public void test2() {
        String line = "com.alibaba.fastjson.JSON";
        System.out.println(line.length());
        System.out.println(line.lastIndexOf("."));
        line = line.substring(0, line.lastIndexOf("."));
        System.out.println(line);
    }
}
