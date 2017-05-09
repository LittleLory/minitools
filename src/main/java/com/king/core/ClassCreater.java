package com.king.core;

import javassist.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by king on 2017/4/26.
 */
public class ClassCreater {
    private static final Log log = LogFactory.getLog(ClassCreater.class);

    /**
     * 在指定classloader中，创建自定义代码生成的class
     * @param name 类名
     * @param code 自定义代码
     * @param loader 目标classloader
     * @return 生成的class对象
     * @throws CannotCompileException 自定义代码编译失败
     * @throws IOException 解析自定义代码异常
     */
    public static Class create(String name, String code, ClassLoader loader) throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();

        //以classPath的形式，加载自定义classloader到pool，以解决编译代码时找不到import的类
        pool.appendClassPath(new LoaderClassPath(loader));

        BufferedReader br = new BufferedReader(new StringReader(code));
        StringBuilder methodCode = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("import")) {
                line = line.replace("import", "").replace(";", "").trim();
                pool.importPackage(line.substring(0, line.lastIndexOf(".")));
                log.info("[class create]import package["+line.substring(0, line.lastIndexOf(".")) +"].");
            } else
                methodCode.append(line).append("\n");
        }

        CtClass newCls = pool.makeClass(name);
        CtMethod method = CtNewMethod.make(methodCode.toString(), newCls);
        newCls.addMethod(method);
        return newCls.toClass(loader, null);
    }
}
