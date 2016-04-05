package com.wychuan.code.db;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.QueryLoader;

import com.wychuan.code.conf.DbSetting;
import com.wychuan.util.StringUtils;

public abstract class DaoBase {
	protected DbSetting dbSetting;
	protected final String SQL_COLUMNS = "sqlOfGetColumns";
	protected Map<String, String> mapSqls = new HashMap<String, String>();

	protected abstract String getSqlPath();

	public DaoBase() {
	}

	public DaoBase(DbSetting dbSetting) {
		this.dbSetting = dbSetting;
		QueryLoader loader = QueryLoader.instance();
		try {
			mapSqls = loader.load(getSqlPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DRIVEROFMYSQL = "com.mysql.jdbc.Driver";

	static {
		try {
			Class.forName(DRIVER);
			Class.forName(DRIVEROFMYSQL);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 检测dbsetting是否设置
	 * 
	 * @return
	 */
	private boolean checkDbSetting() {
		return dbSetting != null && !StringUtils.isEmpty(dbSetting.getUrl())
				&& !StringUtils.isEmpty(dbSetting.getUserName()) && !StringUtils.isEmpty(dbSetting.getPassword());
	}

	protected Connection getConnection() throws SQLException {
		if (!checkDbSetting()) {
			throw new SQLException("dbsetting没有设置。");
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbSetting.getUrl(), dbSetting.getUserName(), dbSetting.getPassword());
		} catch (SQLException e) {
			System.out.println("获取connection失败。");
			e.printStackTrace();
			throw e;
		}
		System.out.println("获取connection成功.");
		return con;
	}

	/**
	 * 增删改[insert,update,delete]
	 * 
	 * @param sql
	 * @return
	 */
	protected int executeNonQuery(String sql) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			result = stmt.executeUpdate(sql);
		} catch (SQLException err) {
			err.printStackTrace();
			free(null, stmt, conn);
		} finally {
			free(null, stmt, conn);
		}
		return result;
	}

	/**
	 * 增删改[insert,update,delete]
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	protected int executeNoQuery(String sql, Object... obj) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}

			result = pstmt.executeUpdate();
		} catch (SQLException err) {
			err.printStackTrace();
			free(null, pstmt, conn);
		} finally {
			free(null, pstmt, conn);
		}
		return result;
	}

	/**
	 * 查[query]
	 * 
	 * @param sql
	 * @return
	 */
	protected ResultSet executeQuery(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException err) {
			err.printStackTrace();
			free(rs, stmt, conn);
		} finally {
			// free(null, stmt, conn);
		}
		return rs;
	}

	/**
	 * 查【query]
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	protected ResultSet executeQuery(String sql, Object... objects) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				pstmt.setObject(i + 1, objects[i]);
			}
			rs = pstmt.executeQuery();
		} catch (SQLException err) {
			err.printStackTrace();
			free(rs, pstmt, conn);
		} finally {
			free(rs, pstmt, conn);
		}
		return rs;
	}

	/**
	 * 判断纪录是否存在
	 * 
	 * @param sql
	 * @return
	 */
	protected Boolean isExist(String sql) {
		ResultSet rs = null;
		try {
			rs = executeQuery(sql);
			rs.last();
			int count = rs.getRow();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException err) {
			err.printStackTrace();
			free(rs);
			return false;
		} finally {
			free(rs);
		}
	}

	/**
	 * 判断纪录是否存在
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	protected Boolean isExists(String sql, Object... objects) {
		ResultSet rs = null;
		try {
			rs = executeQuery(sql, objects);
			rs.last();
			int count = rs.getRow();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException err) {
			err.printStackTrace();
			free(rs);
			return false;
		} finally {
			free(rs);
		}
	}

	/**
	 * 获取查询纪录的总行数
	 * 
	 * @param sql
	 * @return
	 */
	protected int getCount(String sql) {
		int result = 0;
		ResultSet rs = null;
		try {
			rs = executeQuery(sql);
			rs.last();
			result = rs.getRow();
		} catch (SQLException err) {
			err.printStackTrace();
			free(rs);
		} finally {
			free(rs);
		}
		return result;
	}

	/**
	 * 获取查询纪录的总行数
	 * 
	 * @param sql
	 * @return
	 */
	protected int getCount(String sql, Object... objects) {
		int result = 0;
		ResultSet rs = null;
		try {
			rs = executeQuery(sql);
			rs.last();
			result = rs.getRow();
		} catch (SQLException err) {
			err.printStackTrace();
			free(rs);
		} finally {
			free(rs);
		}
		return result;
	}

	protected void free(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	protected void free(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	protected void free(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	protected void free(ResultSet rs, Statement st, Connection conn) {
		free(rs);
		free(st);
		free(conn);
	}
}
