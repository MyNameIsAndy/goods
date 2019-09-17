package com.goods.service.order.impl;

import com.goods.mapper.order.ItemMapper;
import com.goods.order.TbItem;
import com.goods.redis.RedisKey;
import com.goods.redis.util.RedisUtil;
import com.goods.service.order.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public TbItem getBeanById(String id) {
        boolean b = redisUtil.existKey(RedisKey.GOODS_REDIS_ORDER_MAP);
        if(!b){
           TbItem mapField = redisUtil.getMapField(RedisKey.GOODS_REDIS_ORDER_MAP, id);
            if(null == mapField){
                mapField = itemMapper.selectByPrimaryKey(id);
                if(null != mapField){
                    redisUtil.addMap(RedisKey.GOODS_REDIS_ORDER_MAP,mapField.getId(),mapField);
                }
            }
        }
        return redisUtil.getMapField(RedisKey.GOODS_REDIS_ORDER_MAP, id);
    }

    @Override
    public List<TbItem> findAll() {
        boolean b = redisUtil.existKey(RedisKey.GOODS_REDIS_ORDER_MAP);
        if(!b){

        }
        return null;
    }
}
