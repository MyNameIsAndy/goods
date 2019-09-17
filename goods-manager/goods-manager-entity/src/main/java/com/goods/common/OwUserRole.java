package com.goods.common;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @Classname OwUserRole
 * @Description 用户角色
 * @Date 2019/9/14 8:49
 * @Created by andy
 */
@Table(name = "OW_USER_ROLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwUserRole extends BaseBean {
    private String userId;
    private String roleId;
    private String updateTime;
}
