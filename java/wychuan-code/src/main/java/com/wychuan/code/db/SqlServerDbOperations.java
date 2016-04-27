package com.wychuan.code.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.TableInfo;
import com.wychuan.code.conf.DbSetting;
import com.wychuan.util.StringUtils;

/**
 * SQL Server数据库操作类
 * @author wangyuchuan
 *
 */
public class SqlServerDbOperations extends DaoBase implements IDbOperations {

	private final String PATH = "/sqlserver.xml";

	public SqlServerDbOperations() {
	}

	public SqlServerDbOperations(DbSetting dbSetting) {
		super(dbSetting);
	}

	/**
	 * 获取指定服务器的数据库列表
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<String> getDbList() throws SQLException {
		dbSetting.setDbName("master");
		String strSql = mapSqls.get(SQL_GET_DBLIST);

		QueryRunner query = new QueryRunner();
		List<String> dblist = new ArrayList<String>();
		Connection connection = null;
		try {
			connection = getConnection();
			dblist = query.query(connection, strSql, new ColumnListHandler<String>());
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DbUtils.closeQuietly(connection);
		}
		return dblist;
	}

	/**
	 * 获取指定数据库表名称列表
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<String> getTables(String dbName) throws SQLException {
		dbSetting.setDbName(dbName);
		final String strSql = mapSqls.get(SQL_GET_TABLE_NAMES);

		Connection conn = null;
		List<String> tablelist = new ArrayList<String>();
		try {
			conn = getConnection();
			QueryRunner query = new QueryRunner();
			tablelist = query.query(conn, strSql, new ColumnListHandler<String>());
		} catch (SQLException err) {
			err.printStackTrace();
			throw err;
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return tablelist;
	}

	/**
	 * 获取指定数据库的表信息列表
	 */
	@Override
	public List<TableInfo> getTablesInfo(String dbName) {
		dbSetting.setDbName(dbName);
		String strSql = mapSqls.get(SQL_GET_TABLE_INFOS);
		System.out.println(strSql);
		List<TableInfo> tableinfolist = new ArrayList<TableInfo>();
		Connection conn = null;

		try {
			conn = getConnection();
			QueryRunner query = new QueryRunner();
			tableinfolist = query.query(conn, strSql, new BeanListHandler<TableInfo>(TableInfo.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return tableinfolist;
	}

	/**
	 * 获取指定数据库指定表的列信息
	 */
	@Override
	public List<ColumnInfo> getColumnsInfo(String dbName, String dbTable) throws SQLException {

		dbSetting.setDbName(dbName);

		Connection conn = null;
		String strSql = mapSqls.get(SQL_GET_COLUMN_INFOS);
		strSql = String.format(strSql, dbTable);
		List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
		try {
			conn = getConnection();
			QueryRunner query = new QueryRunner();
			columns = query.query(conn, strSql, new BeanListHandler<ColumnInfo>(ColumnInfo.class));
		} catch (SQLException err) {
			err.printStackTrace();
			throw err;
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return columns;
	}

	/**
	 * sql文件路径
	 */
	@Override
	protected String getSqlPath() {
		return PATH;
	}
	
	@Override
	public void updateProperty(String dbName, String tableName, String columnName, String desc) throws SQLException {
		dbSetting.setDbName(dbName);
		
		ProcedureParameterBean paramName = new ProcedureParameterBean("name", "MS_Description");
		ProcedureParameterBean paramDesc = new ProcedureParameterBean("value", desc);
		ProcedureParameterBean param3 = new ProcedureParameterBean("level0type", "SCHEMA");
		ProcedureParameterBean param4 = new ProcedureParameterBean("level0name", "dbo");
		ProcedureParameterBean param5 = new ProcedureParameterBean("level1type", "TABLE");
		ProcedureParameterBean param6 = new ProcedureParameterBean("level1name", tableName);
		
		List<ProcedureParameterBean> params = new ArrayList<DaoBase.ProcedureParameterBean>();
		params.add(paramName);
		params.add(paramDesc);
		params.add(param3);
		params.add(param4);
		params.add(param5);
		params.add(param6);
		if(!StringUtils.isEmpty(columnName)){
			ProcedureParameterBean param7 = new ProcedureParameterBean("level2type", "COLUMN");
			ProcedureParameterBean param8 = new ProcedureParameterBean("level2name", columnName);
			params.add(param7);
			params.add(param8);
		}
		
		try{
			runProcedure("sp_updateextendedproperty", params);
		}catch(Exception e){
			runProcedure("sp_addextendedproperty", params);
		}
		
	}
}
