package com.goods.order;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_item_cat")
@NoArgsConstructor
@AllArgsConstructor
public class TbItemCat extends BaseBean {

    private String parentId;

    private String name;

    private String status;

    private String sortOrder;

    private String isParent;

    private String updateTime;

}