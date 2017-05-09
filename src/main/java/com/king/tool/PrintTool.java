package com.king.tool;

import com.king.core.IProcessor;

/**
 * Created by king on 2017/4/26.
 */
public class PrintTool implements IProcessor {
    @Override
    public void process() {
        System.out.println("print ...");
    }
}
