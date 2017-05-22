package com.king.bean;

import com.king.core.Dependency;
import com.king.core.Parameter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by king on 2017/5/11.
 */
//@Validated
public class ToolCreateFormVO {
//    @NotNull(message = "工具名称不能为空！")
//    @Length(max = 32)
    private String entryName;

    private List<Dependency> dependencies;
//    @NotNull(message = "入参不能为空！")
//    @Size(min = 1, message = "入参至少要有一个！")
    private List<Parameter> parameters;
//    @NotNull(message = "自定义代码不能为空！")
    private String code;
    private boolean isPublic;

    private String callback;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "ToolCreateFormVO{" +
                "entryName='" + entryName + '\'' +
                ", dependencies=" + dependencies +
                ", parameters=" + parameters +
                ", code='" + code + '\'' +
                ", isPublic=" + isPublic +
                '}';
    }
}
