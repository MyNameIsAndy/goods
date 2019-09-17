package com.goods.service.order;


import com.goods.order.TbItem;

import java.util.List;

/**
 *商品
 */
public interface ItemService {
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    TbItem getBeanById(String id);

    /**
     * 查询所有商品信息
     * @return
     */
    List<TbItem> findAll();
}
