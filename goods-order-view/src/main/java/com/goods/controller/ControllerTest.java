package com.goods.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerTest {
    @Value("${b}")
    private String b;
    @RequestMapping("/index")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
