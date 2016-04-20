package com.wychuan.dbtools.htmltables;

import com.wychuan.util.StringUtils;

/**
 * html table tr ->td数据项
 * <p>
 * <td name="${name}" id="${id}" class="${class}" rowspan="${rowspan}" * colspan="${colspan}" ${properties}>
 * <//td>
 * 
 * @author wangyuchuan
 *
 */
public abstract class HtmlTableTdItem {
	/**
	 * 名称,对应td标签的name属性 <td name="${name}">...</td>
	 */
	protected String name;
	/**
	 * td的id属性值 <td id="${id}">...</td>
	 */
	protected String id;
	/**
	 * td数据项的class属性 <td class="${_class}">...</td>
	 */
	protected String _class;
	/**
	 * td 标签的rowspan属性值 <td rowspan="${rowspan}">...</td>
	 */
	protected String rowspan;
	/**
	 * td 标签的colspan属性值
	 * <p>
	 * <td colspan="${colspan}">...<//td>
	 */
	protected String colspan;
	/**
	 * td标签的其它属性 <td ${properties}>...</td>
	 */
	protected String properties;
	/**
	 * 是否显示，如果为true，则不显示此项
	 */
	protected String isDisplay;

	public abstract String getTextHtml();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getclass() {
		return _class;
	}

	public void setclass(String _class) {
		this._class = _class;
	}

	public String getRowspan() {
		return rowspan;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public String getColspan() {
		return colspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isEmpty(getIsDisplay()) && "false".equals(getIsDisplay().toLowerCase())) {
			_class += " hide";
		}
		if (!StringUtils.isEmpty(getName())) {
			sb.append(String.format(" name=\"%s\"", getName()));
		}
		if (!StringUtils.isEmpty(getId())) {
			sb.append(String.format(" id=\"%s\"", getId()));
		}
		if (!StringUtils.isEmpty(getclass())) {
			sb.append(String.format(" class=\"%s\"", getclass()));
		}
		if (!StringUtils.isEmpty(getRowspan())) {
			sb.append(String.format(" rowspan=\"%s\"", getRowspan()));
		}
		if (!StringUtils.isEmpty(getColspan())) {
			sb.append(String.format(" colspan=\"%s\"", getColspan()));
		}
		if (!StringUtils.isEmpty(getProperties())) {
			sb.append(String.format(" %s", getProperties()));
		}
		return String.format("<td %s>%s</td>", sb.toString(), getTextHtml());
	}
}

