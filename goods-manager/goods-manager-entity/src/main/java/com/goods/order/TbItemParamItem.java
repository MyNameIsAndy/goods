package com.goods.order;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "tb_item_param_item")
@NoArgsConstructor
@AllArgsConstructor
public class TbItemParamItem extends BaseBean {

    private Long itemId;

    private Date updateTime;

    private String paramData;
}