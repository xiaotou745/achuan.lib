package com.wychuan.code.conf;

import com.wychuan.code.db.DbType;

public class DbSetting {
	private String id;
	private DbType dbType = DbType.MySql;
	private String userName;
	private String password;
	private String dbName;
	private String dbServer;
	private String dbPort;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getDbServer() {
		return dbServer;
	}

	public void setDbServer(String dbServer) {
		this.dbServer = dbServer;
	}

	/**
	 * @return the dbType
	 */
	public DbType getDbType() {
		return dbType;
	}

	/**
	 * @param dbType
	 *            the dbType to set
	 */
	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		if (dbType.equals(DbType.SqlServer)) {
			return String.format("jdbc:sqlserver://%s:%s;databaseName=%s", getDbServer(), getDbPort(), getDbName());
		} else if (dbType.equals(DbType.MySql)) {
			return String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=gb2312", getDbServer(),
					getDbPort(), getDbName());
		}
		return null;
	}

	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * @param dbName
	 *            the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * @return the dbPort
	 */
	public String getDbPort() {
		return dbPort;
	}

	/**
	 * @param dbPort
	 *            the dbPort to set
	 */
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}
}
