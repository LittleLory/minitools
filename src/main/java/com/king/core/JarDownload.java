package com.king.core;

import com.king.exception.MvnBuildFailException;
import com.king.exception.OSExcuteException;
import com.king.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 依赖Jar包下载类
 * <p>
 * Created by king on 2017/5/2.
 */
public class JarDownload {
    private static final Log log = LogFactory.getLog(JarDownload.class);

    /**
     * 拉取依赖Jar包
     *
     * @param path 指定路径，将会在此路径下生成pom.xml，创建jar文件夹，并拷贝依赖的jar包到jar文件夹下
     * @param dependencies 依赖的dependency信息
     * @throws IOException 创建pom.xml文件失败
     * @throws MvnBuildFailException mvn命令执行失败
     */
    public static void download(String path, List<Dependency> dependencies) throws IOException, MvnBuildFailException {
        List<String> configs = new ArrayList<String>();
        for (Dependency dependency : dependencies)
            configs.add(exchangeDependencyToConfig(dependency));

        //在指定路径下，根据依赖信息，创建pom文件
        createPom(path, configs);
        //执行mvn install，拉取jar包到本地repository
        mvnInstall(path);
        //拷贝jar包到指定路径的jar文件夹下
        copyJar(path, dependencies);
    }


    private static void copyJar(String path, List<Dependency> dependencies) throws FileNotFoundException {
        for (Dependency dependency : dependencies) {
            String jarPath = exchangeDependencyToPath(dependency);
            String targetPath = path + File.separatorChar + "jar";
            File targetDir = new File(targetPath);
            if (!targetDir.exists())
                if(!targetDir.mkdir())
                    throw new FileNotFoundException("[copyJar]not found jar["+targetDir+"] dir, and create jar dir failed...");


            String out = OSExecutor.command(targetPath, "cp " + jarPath + " .");
            if (StringUtils.isNotBlank(out))
                throw new OSExcuteException("[copyJar]copy jar["+jarPath+"] to ["+targetPath+"] error! out = ["+out+"].");
        }
    }

    private static final Pattern pattern = Pattern.compile("\\S*\\.jar");

    private static String exchangeDependencyToPath(Dependency dependency) throws FileNotFoundException {
        String dirPath = Constants.M2_REPOSITORY + File.separatorChar + dependency.groupId.replace(".", "/") + File.separatorChar + dependency.artifactId + File.separatorChar + dependency.version;
        File dir = new File(dirPath);
        if (!dir.exists())
            throw new FileNotFoundException("dir[" + dirPath + "] not found!");
        File[] list = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                Matcher m = pattern.matcher(name);
                return m.matches();
            }
        });
        if (list == null || list.length == 0)
            throw new FileNotFoundException("not jar in dir[" + dirPath + "]!");

        return list[list.length-1].getPath();
    }

    private static void mvnInstall(String downloadProjectPath) throws MvnBuildFailException {
        String out = OSExecutor.command(downloadProjectPath, "mvn clean install");
        log.info("[mvnInstall] --------- out -------- ");
        log.info("[mvnInstall] " + out);
        log.info("[mvnInstall] --------- out -------- ");
        if (!out.contains("BUILD SUCCESS"))
            throw new MvnBuildFailException("[mvInstall] build error! downloadProjectPath = [" + downloadProjectPath + "].");
    }

    private static void createPom(String downloadProjectPath, List<String> configs) throws IOException {
        StringBuilder result = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <groupId>com.king</groupId>\n" +
                "    <artifactId>minitools-dep</artifactId>\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "\n" +
                "    <dependencies>\n");

        for (String dependency : configs)
            result.append(dependency);

        result.append("\n" + "    </dependencies>\n" + "</project>");

        BufferedWriter bw = new BufferedWriter(new FileWriter(downloadProjectPath + File.separatorChar + "pom.xml"));
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    private static String exchangeDependencyToConfig(Dependency dependency) {
        return "<dependency>\n" +
                "            <groupId>" + dependency.groupId + "</groupId>\n" +
                "            <artifactId>" + dependency.artifactId + "</artifactId>\n" +
                "            <version>" + dependency.version + "</version>\n" +
                "        </dependency>";
    }
}
