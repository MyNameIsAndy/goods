package com.goods.order;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "tb_item_param")
@NoArgsConstructor
@AllArgsConstructor
public class TbItemParam extends BaseBean {

    private String itemCatId;

    private String updateTime;

    private String paramData;
}