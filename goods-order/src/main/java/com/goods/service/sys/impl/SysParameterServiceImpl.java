package com.goods.service.sys.impl;

import com.goods.common.sys.SysParameter;
import com.goods.mapper.sys.SysParameterMapper;
import com.goods.redis.RedisKey;
import com.goods.redis.util.RedisUtil;
import com.goods.service.sys.SysParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname SysParameterServiceImpl
 * @Description TODO
 * @Date 2019/9/17 20:47
 * @Created by andy
 */
@Slf4j
@Service
public class SysParameterServiceImpl implements SysParameterService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysParameterMapper sysParameterMapper;
    @Override
    public void findAllTbitem() {
        //查询缓存是否有数据
        boolean b = redisUtil.existKey(RedisKey.GOODS_REDIS_ORDER_MAP);
        if(!b){
            List<SysParameter> sysParameters = sysParameterMapper.selectAll();
            log.info("查询出所有商品数量【{}】=======================",sysParameters.size());
            if(sysParameters.size()>0){
                for(SysParameter sysParameter : sysParameters){
                    redisUtil.addMap(RedisKey.GOODS_REDIS_ORDER_MAP,sysParameter.getId(),sysParameter);
                }
            }
        }

    }
}
