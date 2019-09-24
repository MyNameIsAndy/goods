package com.goods.service.sys;

/**
 * @Classname SysCmdParameter
 * @Description 服务模块
 * @Date 2019/9/21 15:05
 * @Created by andy
 */
public interface SysSerParameterService {
    /**
     * 校验服务模块是否存在
     * @param code
     * @return
     */
    boolean checkSerPar(String code);
}
