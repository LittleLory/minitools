package com.king.utils;

/**
 * Created by king on 2017/5/9.
 */
public enum ParamType {
    INT("java.lang.Integer"), STRING("java.lang.String");
    private String className;

    ParamType(String className) {
        this.className = className;
    }

    public Object exchange(String value) {
        if (this == INT)
            return Integer.parseInt(value);
        else if (this == STRING)
            return value;
        else
            throw new RuntimeException("not support..");
    }

    public String className() {
        return this.className;
    }
}
