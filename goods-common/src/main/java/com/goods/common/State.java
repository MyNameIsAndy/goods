package com.goods.common;

import lombok.Getter;

@Getter
public enum  State {
    /**
     * 成功
     */
    SUCCESS_CODE("10000"),



    ;

    private String code;
    State(String code){
        this.code=code;
    }
}
