package com.goods.common.vo;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Classname ReqContent
 * @Description 请求
 * @Date 2019/9/21 10:34
 * @Created by andy
 */
@Data
public class RequestBody {
    @NonNull
    @Pattern(regexp = "^[0-9a-zA-Z_]{1,64}$",message = "serialNo字段不合规")
    private String serialNo;//交互流水号
    @NonNull
    private String time;//请求时间
    private String content;//内容
    @NonNull
    @Size(max = 5,message = "reqTxCode字段不合规")
    private String reqTxCode;//系统编号
    @NonNull
    @Pattern(regexp = "^0[1234]$",message = "command字段不合规")
    private String command;//指令01增加02删除03改04查询
}
