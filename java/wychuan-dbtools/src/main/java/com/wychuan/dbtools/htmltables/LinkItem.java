package com.wychuan.dbtools.htmltables;

import com.wychuan.util.StringUtils;

/**
 * 链接项 <a>标签
 * <a ${properties} href="${href}">${text}</a>
 * @author wangyuchuan
 * 
 */
public class LinkItem {
	/**
	 * a text
	 */
	private String text;

	/**
	 * href
	 */
	private String href;

	/**
	 * a 的属性
	 */
	private String properties;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("<a href=\"%s\"", getHref()));
		if (!StringUtils.isEmpty(getProperties())) {
			sb.append(String.format(" %s", getProperties()));
		}
		sb.append(String.format(">%s</a>", getText()));
		return sb.toString();
	}
}
