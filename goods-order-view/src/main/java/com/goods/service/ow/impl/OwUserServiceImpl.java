package com.goods.service.ow.impl;

import com.goods.mapper.ow.OwUserMapper;
import com.goods.service.ow.OwUserService;
import com.goods.common.ow.OwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Set;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2019/9/13 22:39
 * @Created by andy
 */
@Service
public class OwUserServiceImpl implements OwUserService {
    @Autowired
    private OwUserMapper owUserMapper;

    /**
     * 根据姓名获取实体
     * @param userName
     * @return
     */
    @Override
    public OwUser getOwUserByUsrename(String userName) {
        OwUser owUser = new OwUser();
        owUser.setUsername(userName);
        return owUserMapper.selectOne(owUser);
    }

    @Override
    public Set<String> findRolesCodeByUsername(String userName) {
        Set<String> rolesCodeByUsername = owUserMapper.findRolesCodeByUsername(userName);
        return rolesCodeByUsername;
    }

    @Override
    public Set<String> findPermissionsCodeByUsername(String username) {
        Set<String> permissionsCodeByUsername = owUserMapper.findPermissionsCodeByUsername(username);
        return permissionsCodeByUsername;
    }

    @Override
    public void updateStateByUsername(String username, String state) {
        Example example = new Example(OwUser.class);
        example.createCriteria().andEqualTo("username");
        OwUser owUser = new OwUser();
        owUser.setUsername(username);
        owUser.setState(state);
        owUserMapper.updateByExample(owUser,example);
    }
}
