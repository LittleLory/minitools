package com.king.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by king on 2017/5/9.
 */
public class Invoke {
    private static final Log log = LogFactory.getLog(Invoke.class);

    /**
     *
     * @param containerName
     * @param parameters
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static String invoke(String containerName, List<Parameter> parameters, Object[] values) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (parameters.size() != values.length)
            throw new RuntimeException("values size is not match parameters.");
        log.debug("invoke: containerName = [" + containerName + "], parameters = [" + parameters + "].");
        for (Object value : values)
            log.debug("invoke: value[type=("+value.getClass().getName()+"), value=("+value+")]");

        Container container = ContainerManager.getContainer(containerName);
        Class<?>[] classes = new Class[parameters.size()];
        for (int i = 0; i < classes.length; i ++) {
            classes[i] = Class.forName(parameters.get(i).getType().className());
        }
        Class clz = container.getClz(containerName);
        Method m = clz.getMethod("process", classes);
        Object result = m.invoke(null, values);
        return (String) result;
    }
}
