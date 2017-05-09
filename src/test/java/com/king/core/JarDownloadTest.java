package com.king.core;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by king on 2017/5/2.
 */
public class JarDownloadTest {
    @Test
    public void download() throws Exception {
        Dependency dependency = new Dependency("junit", "junit", "4.0");
        JarDownload.download("/Users/king/forMiniTool", Collections.singletonList(dependency));
    }

}