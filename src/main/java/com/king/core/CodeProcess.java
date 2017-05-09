package com.king.core;

import com.king.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 自定义代码 处理
 *
 * Created by king on 2017/5/9.
 */
public class CodeProcess {
    private static final Log log = LogFactory.getLog(CodeProcess.class);

    /**
     * 部署代码到容器目录
     *
     * @param containerName 容器名称
     * @param code 代码
     * @throws IOException 写文件异常
     */
    public static void deployCode(String containerName, String code) throws IOException {
        File containPath = new File(Constants.CONTAINER_BASE_PATH + File.separatorChar + containerName);
        if (!containPath.exists())
            throw new FileNotFoundException("container dir[" + containPath.getPath() + "] not exist...");
        File classDir = new File(containPath.getPath() + File.separatorChar + "class");
        if (!classDir.exists())
            if (!classDir.mkdir())
                throw new RuntimeException("create dir[" + classDir + "] failed...");
        FileWriter classFile = new FileWriter(classDir.getPath() + File.separatorChar + containerName);
        classFile.write(code + "\n");
        classFile.flush();
        classFile.close();
    }

    /**
     * 创建自动以代码模板
     *
     * @param parameters 入参列表信息
     * @return 模板
     */
    public static String createCodeTemplate(List<Parameter> parameters) {
        return "public static String process(" + Parameter.toParameterCode(parameters) + ") throws Exception {\n}\n";
    }
}
