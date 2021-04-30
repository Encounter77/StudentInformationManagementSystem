package com.bcxtm.solution.config;

import cn.hutool.db.DbUtil;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Properties;

/**
 * @author Jiangyf
 * @version 1.0.0
 * @ClassName DataSourceConfig.java
 * @Description 数据库配置
 * @createTime 2021年04月30日 14:37:00
 */
public class DataSourceConfig {

	private static Properties properties = new Properties();


	/**
	 * 加载外部配置文件
	 */
	static {
		try {
			// load db config properties
			FileInputStream fileInputStream = new FileInputStream("db.properties");
			if (fileInputStream == null){
				throw new FileNotFoundException("数据库配置文件未找到");
			}
			properties.load(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接
	 * @return Connection
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 加载数据库驱动
			Class.forName(properties.getProperty("driverClassName"));
			// 获取数据库连接
			conn = DriverManager.getConnection(properties.getProperty("url"),
					properties.getProperty("username"), properties.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭连接
	 * @param rs 返回结果集
	 * @param ps 执行对象
	 * @param conn 连接对象
	 */
	public static void close(ResultSet rs, Statement ps, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


