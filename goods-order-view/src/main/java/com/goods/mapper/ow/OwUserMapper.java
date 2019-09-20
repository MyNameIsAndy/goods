package com.goods.mapper.ow;

import com.goods.baseDao.BaseMapper;
import com.goods.common.ow.OwUser;

import java.util.Set;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2019/9/13 22:40
 * @Created by andy
 */
public interface OwUserMapper extends BaseMapper<OwUser> {
    /**
     * 根据用户名称查询所有角色
     * @param username
     */
   Set<String> findRolesCodeByUsername(String username);

    /**
     * 根据姓名获取所有资源
     * @param username
     * @return
     */
   Set<String> findPermissionsCodeByUsername(String username);

}
