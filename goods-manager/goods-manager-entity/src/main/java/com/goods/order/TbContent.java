package com.goods.order;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "TB_CONTENT")
@AllArgsConstructor
@NoArgsConstructor
public class TbContent extends BaseBean{

    private String categoryId;

    private String title;

    private String subTitle;

    private String titleDesc;

    private String url;

    private String pic;

    private String pic2;

    private String updateTime;

    private String content;


}