package com.goods.order;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
@Data
@Table(name = "tb_order_item")
@NoArgsConstructor
@AllArgsConstructor
public class TbOrderItem extends BaseBean {
    private String itemId;

    private String orderId;

    private String num;

    private String title;

    private String price;

    private String totalFee;

    private String picPath;

}