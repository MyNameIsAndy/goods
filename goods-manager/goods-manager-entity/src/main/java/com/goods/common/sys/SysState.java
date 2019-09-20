package com.goods.common.sys;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Table(name = "sys_state")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SysState extends BaseBean{
    private String code;
    private String name;
    private String sysDesc;
}
