package com.wychuan.dbtools.model;

/**
 * 表描述或列描述更改对象
 * 
 * @author Sylar
 *
 */
public class TableDescModifyParams {
	private String dbServerId;

	private String dbName;

	private String tableName;

	private String columnName;

	private String desc;

	/**
	 * @return the dbServerId
	 */
	public String getDbServerId() {
		return dbServerId;
	}

	/**
	 * @param dbServerId
	 *            the dbServerId to set
	 */
	public void setDbServerId(String dbServerId) {
		this.dbServerId = dbServerId;
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
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
