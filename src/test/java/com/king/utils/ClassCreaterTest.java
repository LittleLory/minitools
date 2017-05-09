//package com.king.utils;
//
//import com.king.core.ClassCreater;
//import javassist.CannotCompileException;
//import javassist.NotFoundException;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
///**
// * Created by king on 2017/4/26.
// */
//public class ClassCreaterTest {
//    @Test
//    public void test() throws CannotCompileException, NotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, IOException {
//        Class clz = ClassCreater.create("com.king.MyTest", "public void process() {\n" +
//                "        System.out.println(\"print ...\");\n" +
//                "    }", this.getClass().getClassLoader());
//        Method m = clz.getMethod("process");
//        Object obj = clz.newInstance();
//        m.invoke(obj);
//    }
//}
