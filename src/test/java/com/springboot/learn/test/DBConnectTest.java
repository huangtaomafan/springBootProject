/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库链接测试
 * @author liuzq
 * @version $Id: DBConnectTest.java, v 0.1 2016年4月28日 上午11:18:12 liuzq Exp $
 */
public class DBConnectTest {

    private static String jdbcUrl      = "jdbc:mysql://localhost:3306/springboot";
    private static String jdbcUser     = "root";
    private static String jdbcPassword = "123456";

    public static Connection getConn() throws SQLException {
        Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        if (conn == null || conn.isClosed()) {
            throw new SQLException();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(DBConnectTest.getConn());
    }
}
