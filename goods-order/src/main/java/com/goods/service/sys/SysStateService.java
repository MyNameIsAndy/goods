package com.goods.service.sys;

import com.goods.common.State;

/**
 * 系统状态
 */
public interface SysStateService {
    /**
     * 查询状态码值
     * @param state
     * @return
     */
    String findMessageByCode(String state);

    /**
     * 根据状态码获取描述
     * @param state
     * @return
     */
    String findMessageByCode(State state);
}
