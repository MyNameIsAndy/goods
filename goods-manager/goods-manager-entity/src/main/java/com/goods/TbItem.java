package com.goods;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "tb_item")
@NoArgsConstructor
@AllArgsConstructor
public class TbItem extends BaseBean{

    private String title;

    private String sellPoint;

    private String price;

    private String num;

    private String barCode;

    private String image;

    private String cid;

    private String status;

    private String updateTime;

}