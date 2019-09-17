package com.goods.service.fegin;

import org.springframework.cloud.netflix.feign.FeignClient;
@FeignClient(value = "goods-order")
public interface GoodsOrderService {
}
