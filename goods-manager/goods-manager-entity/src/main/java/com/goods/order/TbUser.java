package com.goods.order;

import com.goods.base.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
public class TbUser extends BaseBean {
    private String userName;

    private String password;

    private String phone;

    private String email;

    private String updateTime;
}