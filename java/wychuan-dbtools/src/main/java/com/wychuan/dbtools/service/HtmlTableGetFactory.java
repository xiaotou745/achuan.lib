package com.wychuan.dbtools.service;

import com.wychuan.code.conf.DbSetting;

public class HtmlTableGetFactory {
	private HtmlTableGetFactory() {
	}

	private int viewType;
	private DbSetting dbSetting;
	private String tableName = "";
	private boolean hasEdit = false;
	private boolean hasGenerateCode = false;
	private boolean useSection = false;
	private boolean useStripped = true;

	public static HtmlTableGetFactory instance(int viewType) {
		HtmlTableGetFactory htmlTableGetFactory = new HtmlTableGetFactory();
		htmlTableGetFactory.viewType = viewType;
		return htmlTableGetFactory;
	}

	public HtmlTableGetFactory setDbSetting(DbSetting dbSetting) {
		this.dbSetting = dbSetting;
		return this;
	}

	public HtmlTableGetFactory setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public HtmlTableGetFactory setHasEdit(boolean hasEdit) {
		this.hasEdit = hasEdit;
		return this;
	}

	public HtmlTableGetFactory setHasCodeGenerate(boolean hasCodeGenerate) {
		this.hasGenerateCode = hasCodeGenerate;
		return this;
	}

	public HtmlTableGetFactory setUseSection(boolean useSection) {
		this.useSection = useSection;
		return this;
	}

	public HtmlTableGetFactory setUseStrippened(boolean useStripped) {
		this.useStripped = useStripped;
		return this;
	}

	public HtmlTableGetService getService() {
		AbstractHtmlTable result = null;
		switch (viewType) {
		case 1:
			result = new GetHtmlTableOfDbNames();
			break;
		case 2:
			result = new GetHtmlTableOfTableNames();
			break;
		case 3:
			result = new GetHtmlTableOfColumns();
			break;
		default:
			result = new GetHtmlTableOfDbNames();
			break;
		}
		result.setDbSetting(this.dbSetting);
		result.setTableName(tableName);
		result.setHasEdit(this.hasEdit);
		result.setHasGenerateCode(hasGenerateCode);
		result.setUseSection(useSection);
		result.setUseStripped(useStripped);
		return result;
	}
}
