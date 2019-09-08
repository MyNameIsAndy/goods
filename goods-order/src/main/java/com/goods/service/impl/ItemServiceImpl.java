package com.goods.service.impl;

import com.goods.TbItem;
import com.goods.mapper.ItemDao;
import com.goods.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemDao itemDao;

    @Override
    public TbItem getBeanById(String id) {
        TbItem tbItem = itemDao.selectByPrimaryKey(id);
        return tbItem;
    }
}
