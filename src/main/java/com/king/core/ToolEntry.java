package com.king.core;

import java.util.Date;
import java.util.List;

/**
 * Created by king on 2017/5/9.
 */
public class ToolEntry {
    int entryId;
    String entryName;
    List<Dependency> dependencies;
    List<Parameter> parameters;
    String code;
    int belongId;
    String belongName;
    boolean isPublic;
    Date createTime;

    public String getContainerName() {
        return "tool_" + entryId;
    }
}
