package com.goods.controller;

import com.alibaba.fastjson.JSONObject;
import com.goods.TbItem;
import com.goods.bean.BaseBean;
import com.goods.json.JsonUtil;
import com.goods.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private JsonUtil jsonUtil;
    @RequestMapping("test")
    @ResponseBody
    public String test(){
        TbItem beanById = itemService.getBeanById("1473166932");
        String json = jsonUtil.object2Json(beanById);
        return json;
    }
}
