package com.goods.service.sys.impl;

import com.goods.StringUtil;
import com.goods.common.sys.SysSerParameter;
import com.goods.mapper.sys.SysSerParameterMepper;
import com.goods.redis.RedisKey;
import com.goods.redis.util.RedisUtil;
import com.goods.service.sys.SysSerParameterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname SysSerParameterImpl
 * @Description TODO
 * @Date 2019/9/21 15:10
 * @Created by andy
 */
@Service
@Slf4j
public class SysSerParameterImpl implements SysSerParameterService {
    @Autowired
    private SysSerParameterMepper sysSerParameterMepper;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean checkSerPar(String code) {
        if(StringUtil.isNotEmpty(code)){
            boolean b = redisUtil.existKey(RedisKey.GOODS_SYS_SEVICE_MODEL);
            boolean flag = false;
            if(b){
                return redisUtil.existMapField(RedisKey.GOODS_SYS_SEVICE_MODEL, code);
            }
            List<SysSerParameter> sysSerParameters = sysSerParameterMepper.selectAll();
            for(SysSerParameter sysSerParameter : sysSerParameters){
                redisUtil.addMap(RedisKey.GOODS_SYS_SEVICE_MODEL,sysSerParameter.getServiceCode(),sysSerParameter.getName());
                if(StringUtils.equals(sysSerParameter.getServiceCode(),code)){
                    flag = true;
                }
            }
            return flag;
        }else{
            return false;
        }
    }
}
