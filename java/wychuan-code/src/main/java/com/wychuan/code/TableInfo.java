package com.wychuan.code;

/**
 * 表结构信息
 * 
 * @author wychuan
 *
 */
public class TableInfo {
	private String tableName;

	private String tableDate;

	private String tableType;

	private String tableUser;

	private String tableDesc;

	/**
	 * 获取表名
	 * 
	 * @return 返回表名
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 设置表名
	 * 
	 * @param tableName
	 *            表名
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 获取表的创建日期
	 * 
	 * @return 返回表的创建日期
	 */
	public String getTableDate() {
		return tableDate;
	}

	/**
	 * 设置表的创建日期
	 * 
	 * @param tableDate
	 *            创建日期
	 */
	public void setTableDate(String tableDate) {
		this.tableDate = tableDate;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getTableUser() {
		return tableUser;
	}

	public void setTableUser(String tableUser) {
		this.tableUser = tableUser;
	}

	/**
	 * 获取表的描述信息
	 * 
	 * @return 返回表的描述信息
	 */
	public String getTableDesc() {
		return tableDesc;
	}

	/**
	 * 设置表描述
	 * 
	 * @param tableDesc
	 *            表描述信息
	 */
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("tablename:[%s]\r\n", getTableName()));
		sb.append(String.format("tableDate:[%s]\r\n", getTableDate()));
		sb.append(String.format("tableType:[%s]\r\n", getTableType()));
		sb.append(String.format("tableUser:[%s]\r\n", getTableUser()));
		sb.append(String.format("tableDesc:[%s]\r\n", getTableDesc()));
		return sb.toString();
	}
}
