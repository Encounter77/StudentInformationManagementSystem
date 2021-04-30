package com.bcxtm.solution.handler;

import java.sql.ResultSet;

/**
 *  ResultSet结果处理
 * @param <T>
 */
public interface IResultSetHandler<T> {
	T handle(ResultSet rs) throws Exception;
}