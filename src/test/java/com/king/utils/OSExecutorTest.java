package com.king.utils;

import com.king.core.OSExecutor;
import com.king.exception.OSExcuteException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by king on 2017/4/27.
 */
public class OSExecutorTest {
    @Test
    public void command() throws Exception {
        String result = OSExecutor.command("~", "echo \"abc\"");
        assertEquals("abc", result);
    }

    @Test(expected = OSExcuteException.class)
    public void commandExecuteError() {
        OSExecutor.command("", "maven");
    }
}