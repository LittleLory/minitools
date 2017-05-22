package com.king.core;

import com.king.utils.ParamType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 函数参数 实体类
 *
 * Created by king on 2017/4/27.
 */
public class Parameter {
    private ParamType type;//数据类型
    private String name;//参数名
    private String show;//展示名称
    private int sort;//排序

    public Parameter() {
    }

    public Parameter(ParamType type, String name, String show, int sort) {
        this.type = type;
        this.name = name;
        this.show = show;
        this.sort = sort;
    }

    public static String toParameterCode(List<Parameter> parameters) {
        Collections.sort(parameters, sortCompare);
        StringBuilder sb = new StringBuilder();
        for (Parameter parameter : parameters)
            sb.append(",").append(parameter.type.className()).append(" ").append(parameter.name);
        return sb.substring(1);
    }

    private static Comparator<Parameter> sortCompare = new Comparator<Parameter>() {
        @Override
        public int compare(Parameter o1, Parameter o2) {
            return o1.sort < o2.sort ? -1 : 1;
        }
    };

    public ParamType getType() {
        return type;
    }

    public void setType(ParamType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public static Comparator<Parameter> getSortCompare() {
        return sortCompare;
    }

    public static void setSortCompare(Comparator<Parameter> sortCompare) {
        Parameter.sortCompare = sortCompare;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", show='" + show + '\'' +
                ", sort=" + sort +
                '}';
    }
}
