package com.wychuan.dbtools.model;

import java.util.List;

import com.wychuan.code.conf.DbSetting;
import com.wychuan.dbtools.htmltables.HtmlTableInfo;

public class TableViewModel {

	/**
	 * 当前显示的内容类型：1数据库列表 2表列表 3表详细信息
	 */
	private int currentViewType;
	/**
	 * dbsettings
	 */
	private List<DbSetting> dbSettings;
	/**
	 * 服务器列表
	 */
	private List<String> dbServers;
	/**
	 * 当前选择的服务器
	 */
	private String currentServer;
	
	/**
	 * 当前选择的dbServerID
	 */
	private String currentServerId;

	/**
	 * 当前选择数据库名称
	 */
	private String currentDbName;

	/**
	 * 当前选择的表名称
	 */
	private String currentTableName;

	/**
	 * HtmlTable信息
	 */
	private HtmlTableInfo htmlResult;

	/**
	 * 是否含有编辑按钮
	 */
	private String tableHasEditOper;

	/**
	 * 是否含有生成代码按钮
	 */
	private String tableHasGenerateCodeOper;

	/**
	 * 代码生成结果
	 */
	private CodeResult codeResult = CodeResult.empty;

	private String dbNameSource;

	private String dbTableSource;

	public int getCurrentViewType() {
		return currentViewType;
	}

	public void setCurrentViewType(int currentViewType) {
		this.currentViewType = currentViewType;
	}

	public List<String> getDbServers() {
		return dbServers;
	}

	public void setDbServers(List<String> dbServers) {
		this.dbServers = dbServers;
	}

	public String getCurrentServer() {
		return currentServer;
	}

	public void setCurrentServer(String currentServer) {
		this.currentServer = currentServer;
	}

	public String getCurrentDbName() {
		return currentDbName;
	}

	public void setCurrentDbName(String currentDbName) {
		this.currentDbName = currentDbName;
	}

	public String getCurrentTableName() {
		return currentTableName;
	}

	public void setCurrentTableName(String currentTableName) {
		this.currentTableName = currentTableName;
	}

	public HtmlTableInfo getHtmlResult() {
		return htmlResult;
	}

	public void setHtmlResult(HtmlTableInfo htmlResult) {
		this.htmlResult = htmlResult;
	}

	public String getTableHasEditOper() {
		return tableHasEditOper;
	}

	public void setTableHasEditOper(String tableHasEditOper) {
		this.tableHasEditOper = tableHasEditOper;
	}

	public String getTableHasGenerateCodeOper() {
		return tableHasGenerateCodeOper;
	}

	public void setTableHasGenerateCodeOper(String tableHasGenerateCodeOper) {
		this.tableHasGenerateCodeOper = tableHasGenerateCodeOper;
	}

	public CodeResult getCodeResult() {
		return codeResult;
	}

	public void setCodeResult(CodeResult codeResult) {
		this.codeResult = codeResult;
	}

	public String getDbNameSource() {
		return dbNameSource;
	}

	public void setDbNameSource(String dbNameSource) {
		this.dbNameSource = dbNameSource;
	}

	public String getDbTableSource() {
		return dbTableSource;
	}

	public void setDbTableSource(String dbTableSource) {
		this.dbTableSource = dbTableSource;
	}

	/**
	 * @return the dbSettings
	 */
	public List<DbSetting> getDbSettings() {
		return dbSettings;
	}

	/**
	 * @param dbSettings
	 *            the dbSettings to set
	 */
	public void setDbSettings(List<DbSetting> dbSettings) {
		this.dbSettings = dbSettings;
	}

	/**
	 * @return the currentServerId
	 */
	public String getCurrentServerId() {
		return currentServerId;
	}

	/**
	 * @param currentServerId the currentServerId to set
	 */
	public void setCurrentServerId(String currentServerId) {
		this.currentServerId = currentServerId;
	}
}
