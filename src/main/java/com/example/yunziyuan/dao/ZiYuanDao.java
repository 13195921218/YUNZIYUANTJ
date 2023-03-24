package com.example.yunziyuan.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Date;

@Repository
public class ZiYuanDao {

    @Autowired(required = false)
    public JdbcTemplate jdbcTemplate;

    public Connection getConn() {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://172.23.62.176:3306/ziyuan?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "123456";
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, username, password);
            //System.out.println("数据库连接成功");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void getEcsCount1() throws SQLException {
        Connection con = getConn();
        Statement sql;
        sql = con.createStatement();
        ResultSet count1 = sql.executeQuery("select * from HXRegionAB");
        count1.last();
        int row_count1=count1.getRow();
        System.out.println("当前时间：" + new java.util.Date() + "      当前数据条数：" + row_count1 );

    }
    public void getEcsCount2() throws SQLException {
        Connection con = getConn();
        Statement sql;
        sql = con.createStatement();
        boolean b = sql.execute("truncate table HXRegionAB");
        System.out.println("当前时间：" + new java.util.Date() + "      数据已清空!");
        ResultSet count2 = sql.executeQuery("select * from HXRegionAB");
        count2.last();
        int row_count2 = count2.getRow();
        System.out.println("当前时间：" + new java.util.Date() + "      当前数据条数：" + row_count2 );
        System.out.println("当前时间：" + new java.util.Date() + "      数据插入开始!");
    }
    public void getDcsCount1() throws SQLException {
        Connection con = getConn();
        Statement sql;
        sql = con.createStatement();
        ResultSet count1 = sql.executeQuery("select * from hxdcslist");
        count1.last();
        int row_count1=count1.getRow();
        System.out.println("当前时间：" + new java.util.Date() + "      当前数据条数：" + row_count1 );

    }
    public void getDcsCount2() throws SQLException {
        Connection con = getConn();
        Statement sql;
        sql = con.createStatement();
        boolean b = sql.execute("truncate table hxdcslist");
        System.out.println("当前时间：" + new java.util.Date() + "      数据已清空!");
        ResultSet count2 = sql.executeQuery("select * from hxdcslist");
        count2.last();
        int row_count2 = count2.getRow();
        System.out.println("当前时间：" + new java.util.Date() + "      当前数据条数：" + row_count2 );
        System.out.println("当前时间：" + new java.util.Date() + "      数据插入开始!");
    }
}

