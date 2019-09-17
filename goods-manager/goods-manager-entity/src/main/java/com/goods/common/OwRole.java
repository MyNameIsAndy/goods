package com.goods.common;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @Classname OwRole
 * @Description 角色
 * @Date 2019/9/14 8:49
 * @Created by andy
 */
@Table(name = "OW_ROLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwRole extends BaseBean {
    private String roleName;
    private String description;
    private String roleCode;
}
