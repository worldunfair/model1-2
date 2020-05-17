package com.lagou.edu.utils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author 应癫
 */
public class DruidUtils {

    private DruidUtils(){
    }

    private static DruidDataSource druidDataSource = new DruidDataSource();


    static {
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/bank?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456aaa");
        System.out.println("DruidDataSource11");


    }

    public static DruidDataSource getInstance() {
        System.out.println("DruidDataSource");
        return druidDataSource;
    }

}
