package com.wychuan.code.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
 * Mysql 数据库操作类
 * @author wangyuchuan
 *
 */
public class MySqlDbOperations extends DaoBase implements IDbOperations {

	public MySqlDbOperations() {
	}

	public MySqlDbOperations(DbSetting dbSetting) {
		super(dbSetting);
	}

	/**
	 * 获取数据库名称列表
	 */
	@Override
	public List<String> getDbList() throws SQLException {
		final String strSql = mapSqls.get(SQL_GET_DBLIST);

		List<String> dblist = new ArrayList<String>();

		Connection conn = null;

		try {
			QueryRunner runner = new QueryRunner();
			conn = getConnection();
			dblist = runner.query(conn, strSql, new ColumnListHandler<String>());
		} catch (SQLException err) {
			err.printStackTrace();
			throw err;
		} finally {
			DbUtils.closeQuietly(conn);
		}

		return dblist;
	}

	/**
	 * 获取指定数据库的所有表名列表
	 */
	@Override
	public List<String> getTables(String dbName) throws SQLException {
		dbSetting.setDbName(dbName);
		final String strSql = mapSqls.get(SQL_GET_TABLE_NAMES);

		List<String> tables = new ArrayList<String>();
		Connection conn = null;

		try {
			conn = getConnection();
			QueryRunner runner = new QueryRunner();
			tables = runner.query(conn, strSql, new ColumnListHandler<String>());
		} catch (SQLException err) {
			err.printStackTrace();
			throw err;
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return tables;
	}

	@Override
	public List<TableInfo> getTablesInfo(String dbName) {
		dbSetting.setDbName(dbName);

		final String strSql = mapSqls.get(SQL_GET_TABLE_INFOS);

		List<TableInfo> tables = new ArrayList<TableInfo>();

		Connection conn = null;
		try {
			conn = getConnection();

			QueryRunner runner = new QueryRunner();
			tables = runner.query(conn, strSql, new BeanListHandler<>(TableInfo.class), dbName);
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return tables;
	}

	public void test() {
		Connection conn = null;
		try {
			conn = getConnection();

			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet tableTypes = metaData.getTableTypes();
			if (tableTypes != null) {
				ResultSetMetaData msmd = tableTypes.getMetaData();
				int columnCount = msmd.getColumnCount();
				while (tableTypes.next()) {
					StringBuffer sb = new StringBuffer();
					for (int i = 1; i <= columnCount; i++) {
						String columnLabel = msmd.getColumnLabel(i);
						if (StringUtils.isEmpty(columnLabel)) {
							columnLabel = msmd.getColumnName(i);
						}
						Object value = tableTypes.getObject(i);
						sb.append(String.format("[%s]:[%s] ", columnLabel, value));
					}
					System.out.println(sb.toString());
				}
			}

			ResultSet tables = metaData.getTables(null, null, null, null);
			if (tables != null) {
				ResultSetMetaData metaData2 = tables.getMetaData();
				int columnCount = metaData2.getColumnCount();
				while (tables.next()) {
					StringBuffer sb = new StringBuffer();
					for (int i = 1; i <= columnCount; i++) {
						String columnLabel = metaData2.getColumnLabel(i);
						if (StringUtils.isEmpty(columnLabel)) {
							columnLabel = metaData2.getColumnName(i);
						}
						Object value = tables.getObject(i);
						sb.append(String.format("[%s]:[%s] ", columnLabel, value));
					}
					System.out.println(sb.toString());
				}
			}
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public List<ColumnInfo> getColumnsInfo(String dbName, String dbTable) throws SQLException {
		dbSetting.setDbName(dbName);
		String strSql = mapSqls.get(SQL_GET_COLUMN_INFOS);
		Connection conn = null;
		List<ColumnInfo> columnInfos = null;

		try {
			conn = getConnection();

			QueryRunner runner = new QueryRunner();
			columnInfos = runner.query(conn, strSql, new BeanListHandler<>(ColumnInfo.class), dbName, dbTable);
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return columnInfos;
	}

	/**
	 * 获取mysql数据库sql文件路径
	 */
	@Override
	protected String getSqlPath() {
		return "/mysql.xml";
	}

	@Override
	public void updateProperty(String dbName, String tableName, String columnName, String desc) throws SQLException {
		dbSetting.setDbName(dbName);

		String strSql = null;
		if (StringUtils.isEmpty(columnName)) {
			strSql = String.format("ALTER TABLE `%s` COMMENT ?;", tableName);
		} else {
			List<ColumnInfo> columnsInfo = getColumnsInfo(dbName, tableName);
			if (columnsInfo == null) {
				return;
			}
			for (ColumnInfo col : columnsInfo) {
				if (columnName.toLowerCase().equals(col.getName().toLowerCase())) {
					StringBuffer sb = new StringBuffer();
					sb.append("ALTER TABLE `");
					sb.append(tableName);
					sb.append("` MODIFY COLUMN `");
					sb.append(columnName);
					sb.append("` ");
					sb.append(col.getColType());
					if (col.isIdentity()) {
						sb.append(" auto_increment");
					}
					if (!col.isNull()) {
						sb.append(" not null");
					} else {
						sb.append(" null");
					}
					if (!StringUtils.isEmpty(col.getDefaultValue())) {
						switch (col.getType()) {
						case "char":
						case "varchar":
							sb.append(" default '");
							sb.append(col.getDefaultValue());
							sb.append("'");
							break;
						case "timestamp":
							sb.append(" default now()");
							break;
						default:
							sb.append(" default ");
							sb.append(col.getDefaultValue());
							break;
						}
					}
					sb.append(" COMMENT ?");
					strSql = sb.toString();
					break;
				}
			}
		}

		Connection conn = null;

		try {
			conn = getConnection();

			QueryRunner runner = new QueryRunner();
			runner.update(conn, strSql, desc);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(conn);
		}
	}
}
