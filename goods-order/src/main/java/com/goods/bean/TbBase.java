package com.goods.bean;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.goods.BeanFactory;
import com.goods.StringUtil;
import lombok.Data;
import org.springframework.core.env.Environment;

import javax.persistence.Table;
import java.util.UUID;

@Data
@Table(name = "TB_BASE")
public class TbBase {
    private String id;
    private String remark;
    private String createTime;
    private String reqTxCode;
    private String content;
    private String serialNo;
    private String state;
    private String message;
}
