package com.goods.controller;

import com.goods.order.TbItem;
import com.goods.json.JsonUtil;
import com.goods.service.OwUserService;
import com.goods.sys.OwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerTest {
    @Autowired
    private OwUserService owUserService;
    @Autowired
    private JsonUtil jsonUtil;
    @RequestMapping("test")
    @ResponseBody
    public String test(){
        OwUser beanById = owUserService.getBeanById("1473166932");
        String json = jsonUtil.object2Json(beanById);
        return json;
    }
}
