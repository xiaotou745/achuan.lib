package com.wychuan.dbtools.model;

/**
 * 表信息查看，ajax参数对象
 * @author Sylar
 *
 */
public class TableViewParams {
	
	private int viewType;
	
	private String dbServerId;
	
	private String dbName;
	
	private String dbTable;
	
	private boolean hasEdit = true;
	
	private boolean hasGenerateCode = false;
	
	private boolean orderByName = false;

	/**
	 * @return the dbServerId
	 */
	public String getDbServerId() {
		return dbServerId;
	}

	/**
	 * @param dbServerId the dbServerId to set
	 */
	public void setDbServerId(String dbServerId) {
		this.dbServerId = dbServerId;
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbTable() {
		return dbTable;
	}

	public void setDbTable(String dbTable) {
		this.dbTable = dbTable;
	}

	public boolean isHasEdit() {
		return hasEdit;
	}

	public void setHasEdit(boolean hasEdit) {
		this.hasEdit = hasEdit;
	}

	public boolean isHasGenerateCode() {
		return hasGenerateCode;
	}

	public void setHasGenerateCode(boolean hasGenerateCode) {
		this.hasGenerateCode = hasGenerateCode;
	}

	/**
	 * @return the orderByName
	 */
	public boolean isOrderByName() {
		return orderByName;
	}

	/**
	 * @param orderByName the orderByName to set
	 */
	public void setOrderByName(boolean orderByName) {
		this.orderByName = orderByName;
	}
}


