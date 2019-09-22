package com.goods.service.order;


import com.goods.common.vo.ResponseInfo;
import com.goods.order.TbItem;

import javax.servlet.http.HttpServletRequest;
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
     * 加载所有商品到缓存
     * @return
     */
    ResponseInfo loadItemcheck(HttpServletRequest request,String parameter);
}
