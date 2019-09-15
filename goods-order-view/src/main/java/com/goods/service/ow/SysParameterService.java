package com.goods.service.ow;


import com.goods.sys.SysParameter;

import java.util.List;

/**
 * @Classname SysParameterService
 * @Description 系统参数
 * @Date 2019/9/15 17:52
 * @Created by andy
 */
public interface SysParameterService {
    List<SysParameter> findAll();
}
