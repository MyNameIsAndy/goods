package com.goods.common.vo;

import lombok.Data;
import lombok.NonNull;

/**
 * @Classname RequestInfo
 * @Description 请求参数
 * @Date 2019/9/21 10:49
 * @Created by andy
 */
@Data
public class RequestInfo {
    @NonNull
    private String body;
    @NonNull
    private String signed;
}
