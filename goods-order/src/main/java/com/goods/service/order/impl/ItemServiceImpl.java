package com.goods.service.order.impl;

import com.goods.common.SysParameter;
import com.goods.mapper.order.ItemMapper;
import com.goods.order.TbItem;
import com.goods.redis.RedisKey;
import com.goods.redis.util.RedisUtil;
import com.goods.service.order.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
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
    public void findAll() {
        boolean b = redisUtil.existKey(RedisKey.GOODS_REDIS_ORDER_MAP);
        if(!b){
            List<TbItem> tbItems = itemMapper.selectAll();
            log.info("查询出所有商品数量【{}】=======================",tbItems.size());
            if(tbItems.size()>0){
                for(TbItem tbItem : tbItems){
                    redisUtil.addMap(RedisKey.GOODS_REDIS_ORDER_MAP,tbItem.getId(),tbItem);
                }
            }
        }
    }
}
