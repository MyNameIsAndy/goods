package com.goods.service.ow.impl;


import com.goods.mapper.ow.OwUserMapper;
import com.goods.service.ow.OwUserService;
import com.goods.common.OwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwUserServiceImpl implements OwUserService {
    @Autowired
    private OwUserMapper owUserMapper;
    @Override
    public OwUser getBeanById(String id) {
        OwUser owUser = owUserMapper.selectByPrimaryKey(id);
        return owUser;
    }
}
