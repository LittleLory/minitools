package com.king;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharEncoding;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

    @Test
    public void test2() {
        File file = new File("/Users/king/com/king/forMiniTool");
        System.out.println(file.exists());
        System.out.println(file.canWrite());
        if (!file.exists()) {
            System.out.println(file.mkdir());
            System.out.println(file.canWrite());
        }
    }

    @Test
    public void decodeUrl() throws UnsupportedEncodingException {
        String s = URLDecoder.decode("%E4%B8%AD%E5%9B%BD", "utf8");
        System.out.println(s);
    }

    @Test
    public void encodeUrl() throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("%E4%B8%AD%E5%9B%BD", "utf8"));
    }
}
