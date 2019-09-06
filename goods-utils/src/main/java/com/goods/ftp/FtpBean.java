package com.goods.ftp;

import lombok.Data;

@Data
public class FtpBean {


    private String ipAddr;
    private Integer port ;

    private String userName;
    private String pwd;
    private String encoding="UTF-8";



}  