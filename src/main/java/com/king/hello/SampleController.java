package com.king.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.king.core.ClassContainer;

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

        ClassContainer classLoader = new ClassContainer("contract");
        Class cls = classLoader.getClz("com.king.contract.Hello2");
        Object Hello = cls.newInstance();
        Method m = Hello.getClass().getMethod("process");
        m.invoke(Hello);

        return "Hello2 World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }
}
