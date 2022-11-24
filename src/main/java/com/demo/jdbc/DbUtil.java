package com.demo.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * 数据库连接
 *
 * @author gaoyanzhen
 * @since 2022-05-24
 */
@Slf4j
public class DbUtil {
    /**
     * 获取Mysql数据库连接
     *
     * @return
     */
    public static Connection getMysqlConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 获取Postgre数据库连接
     *
     * @return
     */
    public static Connection getPostgreConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jeecg-boot?characterEncoding=UTF-8";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 输出结果集
     *
     * @param resultSet
     * @throws SQLException
     */
    public static void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) {
                System.out.print(",");
            }
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) {
                    System.out.print(",");
                }
                System.out.print(resultSet.getString(i));
            }
            System.out.println();
        }
    }
}
