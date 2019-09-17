package com.goods.service.ow;

import com.goods.common.OwUser;

import java.util.Set;

/**
 * 登陆维护
 */
public interface OwUserService {
   /**
    * 根据用户名称获取用户
    * @param userName
    * @return
    */
   OwUser getOwUserByUsrename(String userName);

   /**
    * 根据姓名获取角色
    * @param userName
    * @return
    */
   Set<String> findRolesCodeByUsername(String userName);

   /**
    * 根据姓名获取资源
    * @param username
    * @return
    */
   Set<String> findPermissionsCodeByUsername(String username);
   void updateStateByUsername(String username,String state);
}
