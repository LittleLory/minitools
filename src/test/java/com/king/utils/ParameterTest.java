package com.king.utils;

import com.king.core.Parameter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by king on 2017/4/27.
 */
public class ParameterTest {
    @Test
    public void parseParameters() {
        Parameter p1 = new Parameter(ParamType.INT, "a", "a", 1);
        Parameter p2 = new Parameter(ParamType.INT, "b", "b", 2);
        List<Parameter> list = new ArrayList<Parameter>();
        list.add(p1);
        list.add(p2);
        String result = Parameter.toParameterCode(list);
        Assert.assertEquals("int a,int b", result);
    }
}
