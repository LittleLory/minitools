package com.king.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.king.bean.JsonpBean;
import com.king.bean.TemplateVO;
import com.king.core.*;
import com.king.repository.ToolEntryRepository;
import com.king.utils.ParamType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by king on 2017/5/11.
 */
@RestController
@RequestMapping(value = "/tool")
public class ToolController {
    private static final Log log = LogFactory.getLog(ToolController.class);
    @Autowired
    private ToolEntryRepository toolEntryRepository;

    @RequestMapping(value = "/ids", method = RequestMethod.GET)
    public JsonpBean getAll(@RequestAttribute Integer userId) {
        log.debug("userId: " + userId);
        List<Integer> result = toolEntryRepository.getAll().stream().map(ToolEntry::getEntryId).collect(Collectors.toList());
        return JsonpBean.bean(result);
    }

    @RequestMapping(value = "/{toolId}", method = RequestMethod.GET)
    public JsonpBean getTool(@PathVariable Integer toolId) {
        return JsonpBean.bean(toolEntryRepository.getToolById(toolId));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public JsonpBean create(HttpServletRequest request) {
        ToolEntry entry = new ToolEntry();
        entry.setEntryName(request.getParameter("entryName"));
        entry.setDependencies(JSON.parseArray(request.getParameter("dependencies"), Dependency.class));
        entry.setParameters(JSON.parseArray(request.getParameter("parameters"), Parameter.class));
        entry.setCode(request.getParameter("code"));
        entry.setIsPublic(Boolean.parseBoolean(request.getParameter("isPublic")));
//        entry.belongId = Integer.parseInt((String) request.getAttribute("userId"));
        toolEntryRepository.create(entry);
        log.debug("[create]created:[" + entry + "]");
        return JsonpBean.bean(true);
    }

    @RequestMapping(value = "/param_types")
    public JsonpBean paramTypes() {
        return JsonpBean.bean(ParamType.values());
    }

    @RequestMapping(value = "/template")
    public JsonpBean codeTemplate(HttpServletRequest request) {
        String callback = WebUtils.findParameterValue(request, "callback");
        String parametersJson = WebUtils.findParameterValue(request, "parameters");
        List<Parameter> parameters = JSON.parseArray(parametersJson, Parameter.class);

        String template = CodeProcess.createCodeTemplate(parameters);
        return JsonpBean.bean(template);
    }

    @RequestMapping(value = "/deploy/{toolId}", method = RequestMethod.GET)
    public JsonpBean deploy(@PathVariable Integer toolId) throws Exception {
        if (toolId == null) {
            log.warn("[deploy] toolId is null.");
            return JsonpBean.bean(false);
        }

        ToolEntry toolEntry = toolEntryRepository.getToolById(toolId);
        ContainerManager.deploy(toolEntry);
        ContainerManager.load(toolEntry.getContainerName());
        return JsonpBean.bean(true);
    }

    @RequestMapping(value = "/deploy/all", method = RequestMethod.GET)
    public JsonpBean deployAll() {
        log.info("[loadContainers]start to load...");
        List<ToolEntry> tools = toolEntryRepository.getOpenTools();
        List<String> containerNames = tools.stream().map(ToolEntry::getContainerName).collect(Collectors.toList());
        for (String containerName : containerNames) {
            try {
                log.info("[loadContainers]load container[" + containerName + "]...");
                ContainerManager.load(containerName);
            } catch (Exception e) {
                log.error("[loadContainers]container[" + containerName + "] load error!", e);
            }
        }
        return JsonpBean.bean(true);
    }

    @RequestMapping(value = "/invoke", method = RequestMethod.GET)
    public JsonpBean invoke(HttpServletRequest request) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String toolId = WebUtils.findParameterValue(request, "toolId");
        String valuesJson = WebUtils.findParameterValue(request, "values");
        JSONArray jsonArray = JSON.parseArray(valuesJson);
        String[] values = jsonArray.toArray(new String[jsonArray.size()]);

        ToolEntry toolEntry = toolEntryRepository.getToolById(Integer.parseInt(toolId));

        String result = Invoke.invoke(toolEntry.getContainerName(), toolEntry.getParameters(), exchange(toolEntry.getParameters(), values));
        return JsonpBean.bean(result);
    }


    private Object[] exchange(List<Parameter> parameters, String[] values) {
        Object[] result = new Object[values.length];
        for (Parameter parameter : parameters) {
            result[parameter.getSort() - 1] = parameter.getType().exchange(values[parameter.getSort() - 1]);
        }
        return result;
    }
}
