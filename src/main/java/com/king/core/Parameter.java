package com.king.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 函数参数 实体类
 *
 * Created by king on 2017/4/27.
 */
public class Parameter {
    public String type;//数据类型
    public String name;//参数名
    public int sort;//排序

    public Parameter(String type, String name, int sort) {
        this.type = type;
        this.name = name;
        this.sort = sort;
    }

    public static String toParameterCode(List<Parameter> parameters) {
        Collections.sort(parameters, sortCompare);
        StringBuilder sb = new StringBuilder();
        for (Parameter parameter : parameters)
            sb.append(",").append(parameter.type).append(" ").append(parameter.name);
        return sb.substring(1);
    }

    private static Comparator<Parameter> sortCompare = new Comparator<Parameter>() {
        @Override
        public int compare(Parameter o1, Parameter o2) {
            return o1.sort < o2.sort ? -1 : 1;
        }
    };

}
