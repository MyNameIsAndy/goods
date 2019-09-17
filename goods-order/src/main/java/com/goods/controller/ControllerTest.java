package com.goods.controller;

import com.goods.order.TbItem;
import com.goods.json.JsonUtil;
import com.goods.redis.RedisKey;
import com.goods.redis.util.RedisUtil;
import com.goods.service.order.ItemService;
import com.goods.service.ow.OwUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private RedisUtil redisUtil;
    @RequestMapping("order_info")
    public String test(){
        return "";
    }
}
