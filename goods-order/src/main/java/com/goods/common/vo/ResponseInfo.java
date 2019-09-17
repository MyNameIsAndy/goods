package com.goods.common.vo;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ResponseInfo {
    private String body;//内容
    private String sign;//crc32验签
}
