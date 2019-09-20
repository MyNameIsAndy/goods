package com.goods.service.sys.impl;

import com.goods.StringUtil;
import com.goods.common.State;
import com.goods.common.sys.SysState;
import com.goods.mapper.sys.SysStateMapper;
import com.goods.redis.RedisKey;
import com.goods.redis.util.RedisUtil;
import com.goods.service.sys.SysStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysStateServiceImpl implements SysStateService {
    @Autowired
    private SysStateMapper sysStateMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String findMessageByCode(String state) {
        if(StringUtil.isNotEmpty(state)){
            boolean b = redisUtil.existKey(RedisKey.GOODS_SYS_STATE);
            String message = "";
            if(!b){
                List<SysState> sysStates = sysStateMapper.selectAll();
                for(SysState sysState : sysStates){
                    redisUtil.addMap(RedisKey.GOODS_SYS_STATE,state,sysState.getName());
                    if(sysState.getCode().equals(state)){
                        message = sysState.getName();
                    }
                }
                return message;
            }
            return redisUtil.getMapField(RedisKey.GOODS_SYS_STATE,state);
        }else{
            return "";
        }
    }

    @Override
    public String findMessageByState(State state) {
        String code = state.getCode();
        String messageByCode = findMessageByCode(code);
        return messageByCode;
    }
}
