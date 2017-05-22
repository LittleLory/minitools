package com.king.bean;

/**
 * Created by king on 2017/5/22.
 */
public class JsonpBean {
    private Object data;

    public JsonpBean() {
    }

    private JsonpBean(Object data) {
        this.data = data;
    }

    public static JsonpBean bean(Object obj) {
        return new JsonpBean(obj);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonpBean{" +
                "data=" + data +
                '}';
    }
}
