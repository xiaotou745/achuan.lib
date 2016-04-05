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

public class SqlServerDbOperations extends DaoBase implements IDbOperations {

	private final String PATH = "/sqlserver-operations.properties";

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
		final String strSql = "select name from sysdatabases order by name";

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

		/*
		 * List<String> dblist = new ArrayList<String>(); ResultSet rs = null;
		 * try{ rs = executeQuery(strSql); while(rs.next()){ String dbname =
		 * rs.getString(1); dblist.add(dbname); } }catch(SQLException err){
		 * throw err; }finally{ free(rs); }
		 * 
		 * return dblist;
		 */
	}

	/**
	 * 获取指定数据库表名称列表
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<String> getTables(String dbName) throws SQLException {
		dbSetting.setDbName(dbName);
		final String strSql = "select [name] from sysobjects where xtype='U'and [name]<>'dtproperties' order by [name]";

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
		String strSql = mapSqls.get("sqlOfGetTables");
		/*
		 * final String strSql =
		 * "select s.name 'tableName',s2.name 'tableUser',s.xtype 'tableType',s.crdate 'tableDate',isnull(cast(ep.value as varchar),'') 'tableDesc'"
		 * + " from sys.sysobjects s(nolock)" +
		 * "     join sys.sysusers s2(nolock) on s2.uid=s.uid" +
		 * "		left join sys.extended_properties ep(nolock) on s.id = ep.major_id and ep.minor_id=0"
		 * + " where s.xtype='U' and s.name<>'dtproperties'" +
		 * " order by s.name ";
		 */
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
		String strSql = mapSqls.get(SQL_COLUMNS);
		List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
		try {
			conn = getConnection();
			QueryRunner query = new QueryRunner();
			columns = query.query(conn, strSql, new BeanListHandler<ColumnInfo>(ColumnInfo.class), dbTable);
		} catch (SQLException err) {
			err.printStackTrace();
			throw err;
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return columns;
	}

	@Override
	protected String getSqlPath() {
		return PATH;
	}
}
