package com.wychuan.dbtools.service;

import java.sql.SQLException;
import java.util.List;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.TableInfo;
import com.wychuan.code.conf.DbSetting;
import com.wychuan.code.db.DbOperationFactory;
import com.wychuan.code.db.IDbOperations;
import com.wychuan.dbtools.htmltables.ColumnNames;
import com.wychuan.dbtools.htmltables.HtmlTableInfo;
import com.wychuan.dbtools.htmltables.HtmlTableTdLinkItem;
import com.wychuan.dbtools.htmltables.HtmlTableTrItem;
import com.wychuan.dbtools.htmltables.LinkItem;

public interface HtmlTableGetService {
	/**
	 * 获取table的列名
	 * 
	 * @return
	 */
	abstract ColumnNames getColumnNames();

	/**
	 * 获取html table info
	 * 
	 * @return
	 */
	abstract HtmlTableInfo getHtmlTable() throws SQLException;
}

abstract class AbstractHtmlTable implements HtmlTableGetService {
	protected DbSetting dbSetting;

	protected String tableName;

	protected boolean hasEdit;

	protected boolean hasGenerateCode;

	protected boolean useSection;

	protected boolean useStripped;

	@Override
	public HtmlTableInfo getHtmlTable() throws SQLException {
		HtmlTableInfo htmlTable = new HtmlTableInfo();

		htmlTable.setUseSection(isUseSection());
		htmlTable.setUseStripped(isUseStripped());
		htmlTable.setColumnNames(getColumnNames());

		setHtmlRowInfo(htmlTable);

		return htmlTable;
	}

	abstract void setHtmlRowInfo(HtmlTableInfo table) throws SQLException;

	protected LinkItem getEditLinkItem() {
		if (isHasEdit()) {
			LinkItem editLink = new LinkItem();
			editLink.setText("编辑");
			editLink.setHref("#modalDescEdit");
			editLink.setProperties("data-toggle='modal'");
			return editLink;
		}
		return null;
	}

	protected LinkItem getCodeGenerateLinkItem() {
		if (isHasGenerateCode()) {
			LinkItem codeLink = new LinkItem();
			codeLink.setText("生成代码");
			codeLink.setHref("#generateCodeSettings");
			codeLink.setProperties("data-toggle='modal'");
			return codeLink;
		}
		return null;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public DbSetting getDbSetting() {
		return dbSetting;
	}

	public void setDbSetting(DbSetting dbSetting) {
		this.dbSetting = dbSetting;
	}

	public boolean isUseSection() {
		return useSection;
	}

	public void setUseSection(boolean useSection) {
		this.useSection = useSection;
	}

	public boolean isUseStripped() {
		return useStripped;
	}

	public void setUseStripped(boolean useStripped) {
		this.useStripped = useStripped;
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
}

class GetHtmlTableOfDbNames extends AbstractHtmlTable {

	@Override
	public ColumnNames getColumnNames() {
		ColumnNames columnNames = new ColumnNames();
		columnNames.add("DbName");
		return columnNames;
	}

	@Override
	void setHtmlRowInfo(HtmlTableInfo htmlTable) throws SQLException {
		IDbOperations dbOperation = DbOperationFactory.build(dbSetting);
		List<String> dbList = dbOperation.getDbList();// 获取数据库名称列表

		for (String dbname : dbList) {
			HtmlTableTrItem row = htmlTable.newRow();
			row.newLinkTd("dbname", "javascript:;", dbname);
		}
	}

}

class GetHtmlTableOfTableNames extends AbstractHtmlTable {

	@Override
	public ColumnNames getColumnNames() {
		ColumnNames colNames = new ColumnNames();
		colNames.add("TableName");
		colNames.add("Owner");
		colNames.add("CreateTime");
		colNames.add("Desc");
		colNames.add("Operation");
		return colNames;
	}

	@Override
	void setHtmlRowInfo(HtmlTableInfo htmlTableInfo) throws SQLException {
		IDbOperations dbOperation = DbOperationFactory.build(dbSetting);
		// 获取当前数据库的表名列表
		List<TableInfo> lstTables = dbOperation.getTablesInfo(dbSetting.getDbName());

		for (TableInfo table : lstTables) {
			HtmlTableTrItem trItem = htmlTableInfo.newRow();
			trItem.newLinkTd("tableName", "javascript:;", table.getTableName(), "J_tableName");// 表名称
			trItem.newTd("", table.getTableUser());// 表拥有者
			trItem.newTd("", table.getTableDate());// 表创建日期
			trItem.newTd("", table.getTableDesc());// 表描述

			HtmlTableTdLinkItem linkTd = new HtmlTableTdLinkItem();
			linkTd.setclass("J_editTableDesc");
			linkTd.setProperties(String.format("data-table='%s' data-desc='%s'", table.getTableName(),
					table.getTableDesc()));

			LinkItem editLinkItem = getEditLinkItem();
			if (editLinkItem != null) {
				linkTd.getLinkItems().add(editLinkItem);
			}

			LinkItem codeGenerateLinkItem = getCodeGenerateLinkItem();
			if (codeGenerateLinkItem != null) {
				linkTd.getLinkItems().add(codeGenerateLinkItem);
			}

			trItem.addTd(linkTd);
		}
	}

}

class GetHtmlTableOfColumns extends AbstractHtmlTable {

	@Override
	public ColumnNames getColumnNames() {
		ColumnNames colNames = new ColumnNames();
		colNames.add("No");
		colNames.add("列名");
		colNames.add("类型");
		colNames.add("长度");
		colNames.add("精度");
		colNames.add("Scale");
		colNames.add("自增");
		colNames.add("主键");
		colNames.add("Null");
		colNames.add("默认值");
		colNames.add("描述");
		colNames.add("Oper");
		return colNames;
	}

	@Override
	void setHtmlRowInfo(HtmlTableInfo htmlTable) throws SQLException {
		IDbOperations dbOperation = DbOperationFactory.build(dbSetting);
		List<ColumnInfo> lstColumns = dbOperation.getColumnsInfo(dbSetting.getDbName(), getTableName());

		for (ColumnInfo col : lstColumns) {
			HtmlTableTrItem trItem = htmlTable.newRow();
			trItem.newTd("", col.getOrder());
			trItem.newTd("", col.getName());
			trItem.newTd("", col.getType());
			trItem.newTd("", col.getLength()==null?"":col.getLength());
			trItem.newTd("", col.getPreci()==null?"":col.getPreci());
			trItem.newTd("", col.getScale()==null?"":col.getScale());
			trItem.newTd("", col.isIdentity() ? "√" : "");
			trItem.newTd("", col.isPK() ? "√" : "");
			trItem.newTd("", col.isNull() ? "√" : "");
			trItem.newTd("", col.getDefaultValue()==null?"":col.getDefaultValue());
			trItem.newTd("", col.getDescription());

			if (hasEdit) {
				LinkItem editLink = new LinkItem();
				editLink.setText("编辑");
				editLink.setHref("#modalDescEdit");
				editLink.setProperties("data-toggle='modal'");

				HtmlTableTdLinkItem linkTd = new HtmlTableTdLinkItem();
				linkTd.setclass("J_editColumnDesc");
				linkTd.setProperties(String.format("data-col='%s' data-desc='%s'", col.getName(), col.getDescription()));

				linkTd.getLinkItems().add(editLink);
				trItem.addTd(linkTd);
			}
		}
	}
}
