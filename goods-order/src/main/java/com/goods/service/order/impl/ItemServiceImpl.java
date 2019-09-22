package com.goods.service.order.impl;

import com.goods.common.State;
import com.goods.common.check.DataCheck;
import com.goods.common.vo.RequestBody;
import com.goods.common.vo.ResponseBody;
import com.goods.common.vo.ResponseInfo;
import com.goods.mapper.order.ItemMapper;
import com.goods.order.TbItem;
import com.goods.redis.RedisKey;
import com.goods.redis.util.RedisUtil;
import com.goods.service.order.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private DataCheck dataCheck;
    @Override
    public TbItem getBeanById(String id) {
        boolean b = redisUtil.existKey(RedisKey.GOODS_ORDER_MAP);
        if(!b){
           TbItem mapField = redisUtil.getMapField(RedisKey.GOODS_ORDER_MAP, id);
            if(null == mapField){
                mapField = itemMapper.selectByPrimaryKey(id);
                if(null != mapField){
                    redisUtil.addMap(RedisKey.GOODS_ORDER_MAP,mapField.getId(),mapField);
                }
            }
        }
        return redisUtil.getMapField(RedisKey.GOODS_ORDER_MAP, id);
    }

    @Override
    public ResponseInfo loadItemcheck(HttpServletRequest request, String parameter) {
        Map<String, String> stringStringMap = dataCheck.dataCheckAndSaveBase(parameter, true);
        String state = stringStringMap.get("state");
        String message = stringStringMap.get("message");
        String serialNo = stringStringMap.get("serialNo");
        String baseId = stringStringMap.get("baseId");
        if(!StringUtils.equals(state, State.SUCCESS_CODE.getCode())){
            return ResponseBody.error(serialNo,State.GOODS_SERVICE_ORDER.getCode(),state,message);
        }
        boolean b = redisUtil.existKey(RedisKey.GOODS_ORDER_MAP);
        if(!b){
            List<TbItem> tbItems = itemMapper.selectAll();
            log.info("查询出所有商品数量【{}】=======================",tbItems.size());
            if(tbItems.size()>0){
                for(TbItem tbItem : tbItems){
                    redisUtil.addMap(RedisKey.GOODS_ORDER_MAP,tbItem.getId(),tbItem);
                }
            }
        }
    }

}
