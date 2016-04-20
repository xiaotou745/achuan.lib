package com.wychuan.dbtools.htmltables;

import java.util.ArrayList;
import java.util.List;

public class HtmlTableInfo {

	public HtmlTableInfo() {
		this.useSection = false;
		this.rows = new ArrayList<HtmlTableTrItem>();
		this.columnNames = new ColumnNames();
	}

	private List<HtmlTableTrItem> rows;

	private boolean useSection;
	private String sectionId;
	private String sectionName;
	private boolean useStripped;
	private ColumnNames columnNames;

	public List<HtmlTableTrItem> getRows() {
		return rows;
	}

	public void setRows(List<HtmlTableTrItem> rows) {
		this.rows = rows;
	}

	public boolean isUseSection() {
		return useSection;
	}

	public void setUseSection(boolean useSection) {
		this.useSection = useSection;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public boolean isUseStripped() {
		return useStripped;
	}

	public void setUseStripped(boolean useStripped) {
		this.useStripped = useStripped;
	}

	public ColumnNames getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(ColumnNames columnNames) {
		this.columnNames = columnNames;
	}

	public boolean hasColumns() {
		return columnNames != null && columnNames.size() > 0;
	}

	public void addRow(HtmlTableTrItem tr) {
		rows.add(tr);
	}

	public HtmlTableTrItem newRow() {
		HtmlTableTrItem tr = new HtmlTableTrItem();
		addRow(tr);
		return tr;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("<table class=\"table table-bordered table-condensed %s\">",
				this.useStripped ? "table-striped" : ""));
		sb.append("<thead><tr>");
		for (String colName : getColumnNames()) {
			sb.append(String.format("<th>%s</th>", colName));
		}
		sb.append("</tr></thead><tbody>");
		for (HtmlTableTrItem tableRow : rows) {
			sb.append(tableRow.toString());
		}
		sb.append("</tbody></table>");
		return sb.toString();
	}
}
