package com.goods.common;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @Classname OwRolePermission
 * @Description 角色资源关联
 * @Date 2019/9/14 8:49
 * @Created by andy
 */
@Table(name = "OW_ROLE_PERMISSION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwRolePermission extends BaseBean {
    private String role_id;
    private String permission_id;
}
