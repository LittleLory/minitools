package com.king.core;

import org.junit.Test;

import java.util.Collections;

/**
 * Created by king on 2017/5/2.
 */
public class JarProcessTest {
    @Test
    public void download() throws Exception {
        Dependency dependency = new Dependency("junit", "junit", "4.0");
        JarProcess.deployJar("/Users/king/forMiniTool", Collections.singletonList(dependency));
    }

}