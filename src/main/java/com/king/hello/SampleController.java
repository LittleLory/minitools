package com.king.hello;

import com.king.core.ContainerManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.king.core.Container;

import java.lang.reflect.Method;

/**
 * Created by king on 2017/4/25.
 */

@Controller
@EnableAutoConfiguration
public class SampleController {
    @RequestMapping("/")
    @ResponseBody
    String home() throws Exception {
        return "Hello2 World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }
}
