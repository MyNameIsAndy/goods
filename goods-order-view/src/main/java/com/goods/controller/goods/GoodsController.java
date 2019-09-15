package com.goods.controller.goods;

import com.goods.model.DataGrid;
import com.goods.service.goods.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname GoodsController
 * @Description TODO
 * @Date 2019/9/15 15:08
 * @Created by andy
 */
@RestController
@Slf4j
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @RequestMapping("/goods/list")
    public DataGrid goodsInfo(Integer page, Integer rows){
        log.info("商品列表查询，page【{}】,rows【{}】",page,rows);
        return goodsService.findPage(page,rows);
    }
}
