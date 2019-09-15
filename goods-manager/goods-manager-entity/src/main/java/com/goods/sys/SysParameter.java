package com.goods.sys;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @Classname SysParameter
 * @Description TODO
 * @Date 2019/9/15 17:48
 * @Created by andy
 */
@Table(name = "SYS_PARAMETRS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysParameter extends BaseBean{
    private String code;
    private String status;
    private String name;
    private String sys_desc;
}
