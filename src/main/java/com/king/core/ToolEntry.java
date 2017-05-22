package com.king.core;

import java.util.Date;
import java.util.List;

/**
 * Created by king on 2017/5/9.
 */
public class ToolEntry {
    private int entryId;
    private String entryName;
    private List<Dependency> dependencies;
    private List<Parameter> parameters;
    private String code;
    private int belongId;
    private String belongName;
    private boolean isPublic;
    private boolean isOpen;
    private Date createTime;

    public String getContainerName() {
        return "tool_" + entryId;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getBelongId() {
        return belongId;
    }

    public void setBelongId(int belongId) {
        this.belongId = belongId;
    }

    public String getBelongName() {
        return belongName;
    }

    public void setBelongName(String belongName) {
        this.belongName = belongName;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ToolEntry{" +
                "entryId=" + entryId +
                ", entryName='" + entryName + '\'' +
                ", dependencies=" + dependencies +
                ", parameters=" + parameters +
                ", code='" + code + '\'' +
                ", belongId=" + belongId +
                ", belongName='" + belongName + '\'' +
                ", isPublic=" + isPublic +
                ", isOpen=" + isOpen +
                ", createTime=" + createTime +
                '}';
    }
}
