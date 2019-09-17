package com.goods.common;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @Classname OwPermission
 * @Description 资源
 * @Date 2019/9/14 8:49
 * @Created by andy
 */
@Table(name = "OW_PERMISSION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwPermission extends BaseBean{
    private String permname;
    private String url;
    private String permCode;
    private String isnav;
    private String priority;
    private String icon;
    private String pid;
    private String state;
    private String pname;


}
