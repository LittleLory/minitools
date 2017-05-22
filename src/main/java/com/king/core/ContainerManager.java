package com.king.core;

import com.king.exception.MvnBuildFailException;
import com.king.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 容器管理
 *
 * Created by king on 2017/5/9.
 */
public class ContainerManager {
    private static final Log log = LogFactory.getLog(ContainerManager.class);

    private static Map<String, Container> containerMap = new HashMap<String, Container>();

    //部署容器
    public static void deploy(ToolEntry entry) throws IOException, MvnBuildFailException {
        String containerName = entry.getContainerName();
        File dir = new File(Constants.CONTAINER_BASE_PATH + File.separatorChar + containerName);
        if (!dir.exists())
            if (!dir.mkdir())
                throw new RuntimeException("create container dir["+dir.getPath()+"] failed...");
        CodeProcess.deployCode(containerName, entry.getCode());
        JarProcess.deployJar(containerName, entry.getDependencies());
    }

    //加载容器
    public static void load(String containerName) throws Exception {
        Container container = new Container(containerName);
        containerMap.put(containerName, container);
    }

//    private static void add(Container container) {
//        if (container == null) {
//            log.warn("add a null container..");
//            return;
//        }
//        String containerName = container.getName();
//        if (containerMap.containsKey(containerName))
//            throw new RuntimeException("to add container["+containerName+"], but it is exist...");
//        else
//            containerMap.put(containerName, container);
//    }
//
//    private static void replace(Container container) {
//        if (container == null) {
//            log.warn("replace a null container..");
//            return;
//        }
//        String containerName = container.getName();
//        if (!containerMap.containsKey(containerName))
//            throw new RuntimeException("to replace container["+containerName+"], but it is not exist...");
//    }
    public static Container getContainer(String containerName) {
        return containerMap.get(containerName);
    }

    private static void remove(String containerName) {
        containerMap.remove(containerName);
    }

    public static Set<String> containerNameSet() {
        return containerMap.keySet();
    }
}
