package com.king.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by king on 2017/5/9.
 */
public class Invoke {

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
        Container container = ContainerManager.getContainer(containerName);
        Class<?>[] classes = new Class[parameters.size()];
        for (int i = 0; i < classes.length; i ++) {
            classes[i] = Class.forName(parameters.get(i).type.className());
        }
        Class clz = container.getClz(containerName);
        Method m = clz.getMethod("process", classes);
        Object result = m.invoke(null, values);
        return (String) result;
    }
}
