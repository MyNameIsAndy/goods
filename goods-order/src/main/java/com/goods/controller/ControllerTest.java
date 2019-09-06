package com.goods.controller;

import com.goods.bean.BaseBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class ControllerTest {
    @RequestMapping("test")
    public String test(){
        BaseBean baseBean = new BaseBean();

        return "";
    }
}
