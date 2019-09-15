package com.goods.service.impl;


import com.goods.mapper.OwUserMapper;
import com.goods.service.OwUserService;
import com.goods.sys.OwUser;
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
