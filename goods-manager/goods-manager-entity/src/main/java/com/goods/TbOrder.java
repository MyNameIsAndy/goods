package com.goods;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "tb_order")
@NoArgsConstructor
@AllArgsConstructor
public class TbOrder extends BaseBean {
    private String orderId;

    private String payment;

    private String paymentType;

    private String postFee;

    private String status;

    private String orderCreateTime;

    private String orderUpdateTime;

    private String paymentTime;

    private String consignTime;

    private String endTime;

    private String closeTime;

    private String shippingName;

    private String shippingCode;

    private String userId;

    private String buyerMessage;

    private String buyerNick;

    private String buyerRate;
}