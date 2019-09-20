package com.goods.common;

import lombok.Getter;

@Getter
public enum  State {
    SUCCESS_CODE("10000");

    private String code;
    State(String code){
        this.code=code;
    }
}
