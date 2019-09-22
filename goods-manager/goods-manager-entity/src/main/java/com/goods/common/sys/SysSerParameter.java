package com.goods.common.sys;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @Classname SysCmdParameter
 * @Description 服务模块
 * @Date 2019/9/21 15:02
 * @Created by andy
 */
@Data
@Table(name = "sys_ser_parameter")
@NoArgsConstructor
@AllArgsConstructor
public class SysSerParameter extends BaseBean{
    private String serviceCode;
    private String name;
    private String sysDesc;
}
