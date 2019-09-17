package com.goods.common.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
public class ResponseBody {
    private String time;
    private String state;
    private String message;
    private String serialNo;
    private Object content;


    private ResponseBody responseBody;
    @PostConstruct
    private void init(){

    }
}
