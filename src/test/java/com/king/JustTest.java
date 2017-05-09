package com.king;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by king on 2017/5/8.
 */
public class JustTest {
    @Test
    public void test() {
        final Pattern pattern = Pattern.compile("\\S*\\.jar");
        File dir = new File("/Users/king/.m2/repository/com/bj58/che/chelib/com.bj58.che.chelib.contract/1.0.1-SNAPSHOT");
        File[] list = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                Matcher m = pattern.matcher(name);
                return m.matches();
            }
        });
        System.out.println(list[list.length-1].getName());
    }
}
