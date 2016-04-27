package com.wychuan.dbtools.model;

/**
 * 代码类
 * 
 * @author wangyuchuan
 *
 */
public class CodeFileItem {
	/**
	 * Id主要用来作为tab的ID
	 */
	private String id;

	/**
	 * tab标签显示的文本
	 */
	private String tabText;

	/**
	 * 代码
	 */
	private String code;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTabText() {
		return tabText;
	}

	public void setTabText(String tabText) {
		this.tabText = tabText;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
