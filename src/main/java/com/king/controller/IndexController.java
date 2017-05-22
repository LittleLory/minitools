package com.king.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by king on 2017/4/25.
 */

@Controller
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String home(Model model) throws Exception {
        model.addAttribute("name", "king");
        return "index";
    }
}
