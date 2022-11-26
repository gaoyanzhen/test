package com.demo.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

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
     * 获取Mysql数据库连接
     *
     * @return
     */
    public static DataSource getDataSource() {
        Properties properties = new Properties();
        InputStream is = DbUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            log.error("druid.properties文件读取异常");
            return null;
        }
        log.info("druid.properties文件加载成功");
        DataSource ds = null;
        try {
            if(properties.isEmpty()){
                log.error("请配置druid.properties");
                return null;
            }
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            log.error("数据源创建异常");
        }
        return ds;
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
