package com.king.utils;

import com.king.core.ContainerManager;
import com.king.core.ToolEntry;
import com.king.repository.ToolEntryRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by king on 2017/5/22.
 */
@Service
public class Init {
    private static final Log log = LogFactory.getLog(Init.class);

    @Autowired
    private ToolEntryRepository toolEntryRepository;

    public void loadContainers() {
        log.info("[loadContainers]start to load...");
        List<ToolEntry> tools = toolEntryRepository.getOpenTools();
        List<String> containerNames = tools.stream().map(ToolEntry::getContainerName).collect(Collectors.toList());
        for (String containerName : containerNames) {
            try {
                log.info("[loadContainers]load container["+containerName+"]...");
                ContainerManager.load(containerName);
            } catch (Exception e) {
                log.error("[loadContainers]container["+containerName+"] load error!", e);
            }
        }
    }
}
