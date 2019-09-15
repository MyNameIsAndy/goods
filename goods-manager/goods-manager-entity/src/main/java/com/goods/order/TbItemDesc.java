package com.goods.order;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "tb_item_desc")
@NoArgsConstructor
@AllArgsConstructor
public class TbItemDesc extends BaseBean {
    private String itemId;

    private String updateTime;

    private String itemDesc;

}