package com.bcxtm.solution.utils;

import com.bcxtm.solution.config.DataSourceConfig;
import com.bcxtm.solution.handler.IResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jiangyf
 * @version 1.0.0
 * @ClassName CRUDUtil.java
 * @Description 增删改查工具
 * @createTime 2021年04月30日 15:03:00
 */
public class CRUDUtil {

	/**
	 * 增删改操作
	 * @param sql 传入的SQL语句
	 * @param params 可变参数
	 * @return 操作结果
	 */
	public static int executeUpdate(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			//获取数据库连接对象
			conn = DataSourceConfig.getConnection();
			//获取预编译语句对象
			ps = conn.prepareStatement(sql);
			//给预编译语句赋值
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			//执行SQL语句获取执行结果
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭数据库连接
			DataSourceConfig.close(null, ps, conn);
		}
		return result;
	}

	/**
	 * 查询操作
	 * @param sql SQL语句
	 * @param handler 判断查询一个还是多个
	 * @param params 可变参数
	 * @param <T> 具体操作的实体类
	 * @return 返回IResultSetHandler接口中的泛型
	 */
	public static <T> T executeQuery(String sql, IResultSetHandler<T> handler, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//获取数据库连接对象
			conn = DataSourceConfig.getConnection();
			//获取预编译语句对象
			ps = conn.prepareStatement(sql);
			//给预编译语句赋值
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			//执行SQL语句获取结果集
			rs = ps.executeQuery();
			//处理结果集，映射成JavaBean
			return handler.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭数据库连接
			DataSourceConfig.close(rs, ps, conn);
		}
		return null;
	}
}
