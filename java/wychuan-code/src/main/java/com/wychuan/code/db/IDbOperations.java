package com.wychuan.code.db;

import java.sql.SQLException;
import java.util.List;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.TableInfo;

public interface IDbOperations {
	/**
	 * 获取服务器所有数据库列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<String> getDbList() throws SQLException;

	/**
	 * 获取指定数据库所有的表名
	 * 
	 * @param dbName
	 * @return
	 */
	List<String> getTables(String dbName) throws SQLException;

	/**
	 * 获取指定数据库所有的表信息。
	 * 
	 * @param dbName
	 * @return
	 */
	List<TableInfo> getTablesInfo(String dbName) throws SQLException;
	
	/**
	 * 获取指定数据库指定表的列信息
	 * @param dbName
	 * @param dbTable
	 * @return
	 * @throws SQLException
	 */
	List<ColumnInfo> getColumnsInfo(String dbName, String dbTable) throws SQLException;
}
