package com.king.core;

import com.king.utils.Constants;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2017/4/25.
 */
public class Container {
    private static Log log = LogFactory.getLog(Container.class);

    private String name;
    private ClassLoader classLoader;
    private File[] classFiles;
    private File[] jarFiles;
    private List<URL> jarResources;

    public Container(String containerName) throws Exception {
        this.name = containerName;

        //检查目标文件夹是否完整
        checkDir();

        //读取jar文件，添加到classloader的资源List
        findJarResources();

        //初始化classloader，加载了jar文件
        classLoader = new URLClassLoader(jarResources.toArray(new URL[jarResources.size()]));

        //加载代码，编译并load到classloader
        loadAndCompileCode();
    }

    public String getName() {
        return this.name;
    }

    public Class getClz(String name) throws ClassNotFoundException {
        return classLoader.loadClass(name);
    }

    private String readCode(File classFile) throws IOException {
        StringBuilder code = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(classFile));
        String line;
        while ((line = br.readLine()) != null) {
            code.append(line).append("\n");
        }
        return code.toString();
    }

    private void checkDir() throws FileNotFoundException {
        String loadPath = Constants.CONTAINER_BASE_PATH + File.separatorChar + name;
        File classDir = new File(loadPath + File.separatorChar + "class");
        File jarDir = new File(loadPath + File.separatorChar + "jar");

        if (!classDir.exists() || !jarDir.exists()) {
            log.warn("base path[" + loadPath + "] lose directory.");
            throw new FileNotFoundException("base path[" + loadPath + "] lose "+ (classDir.exists() ? "jar" : "class") +" directory.");
        }

        classFiles = classDir.listFiles();
        jarFiles = jarDir.listFiles();

        if (jarFiles == null || jarFiles.length == 0) {
            log.warn("jar path[" + loadPath + "/jar] have no jar file.");
        }

        if (classFiles == null || classFiles.length == 0) {
            log.error("class path[" + loadPath + "/class] have no class file.");
            throw new FileNotFoundException("jar path[" + loadPath + "/jar] have no class file.");
        }
    }

    private void findJarResources() throws MalformedURLException {
        jarResources = new ArrayList<URL>();
        if (jarFiles != null) {
            for (File file : jarFiles) {
                String fileName = file.getName();
                log.info("load jar=> " + file.toURI().toURL());
                jarResources.add(file.toURI().toURL());
            }
        }
    }

    private void loadAndCompileCode() throws IOException, CannotCompileException, NotFoundException, ClassNotFoundException {
        for (File classFile : classFiles) {
            String fileName = classFile.getName();
            //读取自定义代码
            String code = readCode(classFile);
            //动态编译代码，生成class，并加载到此classloader
            ClassCreater.create(fileName, code, classLoader);
        }
    }
}
