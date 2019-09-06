package com.goods.bean;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.goods.BeanFactory;
import com.goods.StringUtil;
import org.springframework.core.env.Environment;

@JsonIgnoreProperties
public class BaseBean {
    private String id;
    private String remark;
    private String createTime;
    private String dataCenter;
    @JsonIgnore
    @JacksonInject
    private String enDataCenter;
    public BaseBean(){
        if(StringUtil.isEmpty(enDataCenter)){
            initDataCenter();
        }
       this.dataCenter=enDataCenter;
    }
    private void initDataCenter(){
        Environment environment = (Environment)BeanFactory.getBean("environment");
        this.dataCenter=environment.getProperty("data.center");
    }
}
