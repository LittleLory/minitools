package com.king.bean;

import com.king.core.Parameter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by king on 2017/5/12.
 */
@Validated
public class TemplateVO {
    @NotNull
    private List<Parameter> parameters;

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
