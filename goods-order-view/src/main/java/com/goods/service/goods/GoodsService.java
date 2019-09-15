package com.goods.service.goods;

import com.goods.model.DataGrid;
import com.goods.order.TbItem;

import java.util.List;

/**
 * @Classname GoodsService
 * @Description TODO
 * @Date 2019/9/15 15:21
 * @Created by andy
 */
public interface GoodsService {
    /**
     * 商品查询分页
     * @param page
     * @param rows
     * @return
     */
    DataGrid findPage(Integer page, Integer rows);
}
