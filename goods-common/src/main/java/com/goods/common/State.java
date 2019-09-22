package com.goods.common;

import lombok.Getter;

@Getter
public enum  State {
    /**
     * goods-order服务模块
     */
    GOODS_SERVICE_ORDER("P0001"),
    /**
     * goods-order-view服务模块
     */
    GOODS_SERVICE_ORDER_VIEW("P0002"),




    /**
     * 成功
     */
    SUCCESS_CODE("10000"),



    /**
     *处理中
     */
    DOING("21002"),



    /**
     * 失败
     */
    ERROR_CODE("30000"),
    /**
     * 业务数据不合规
     */
    BUSINESS_DATA_INVALID("31000"),
    ;

    private String code;
    State(String code){
        this.code=code;
    }
}
