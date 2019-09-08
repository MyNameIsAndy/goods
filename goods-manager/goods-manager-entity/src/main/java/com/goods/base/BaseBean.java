package com.goods.base;

import lombok.Data;

import javax.persistence.Id;
import java.util.Random;
import java.util.UUID;

@Data
public class BaseBean {
    @Id
    private String id;
    private String createTime;
    private String remark;
    public BaseBean(){
        initId();
        initCreateTime();
    }
    //自动生成主键
    public void initId(){
        this.id = UUID.randomUUID().toString().replace("-","");
    }
    //自动生成创建时间
    public void initCreateTime(){
       this.createTime =  Long.toString(System.currentTimeMillis());
    }
}
