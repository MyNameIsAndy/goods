package com.goods.common;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @Classname OwUser
 * @Description 用户
 * @Date 2019/9/14 8:48
 * @Created by andy
 */
@Table(name = "OW_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwUser extends BaseBean {
    private String username;
    private String password;
    private String state;
    private String lastLoginTime;
    private String salt;

}
