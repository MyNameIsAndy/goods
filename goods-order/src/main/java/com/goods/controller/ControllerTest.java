package com.goods.controller;

import com.goods.common.vo.ResponseInfo;
import com.goods.order.TbItem;
import com.goods.json.JsonUtil;
import com.goods.redis.RedisKey;
import com.goods.redis.util.RedisUtil;
import com.goods.service.order.ItemService;
import com.goods.service.ow.OwUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("tbItme")
public class ControllerTest {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/itemInfoCache")
    public ResponseInfo itemCache(HttpServletRequest request ,String parameter){

    }
}
