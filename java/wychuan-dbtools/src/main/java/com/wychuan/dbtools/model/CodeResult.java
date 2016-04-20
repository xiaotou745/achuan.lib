package com.wychuan.dbtools.model;

import java.util.ArrayList;
import java.util.List;

import com.wychuan.code.conf.CodeLanguage;

public class CodeResult {
	public static final CodeResult empty = new CodeResult();
	private CodeLanguage language;
	private List<CodeFileItem> codes;

	public CodeResult() {
		codes = new ArrayList<CodeFileItem>();
	}

	public CodeLanguage getLanguage() {
		return language;
	}

	public void setLanguage(CodeLanguage language) {
		this.language = language;
	}

	public List<CodeFileItem> getCodes() {
		return codes;
	}

	public void setCodes(List<CodeFileItem> codes) {
		this.codes = codes;
	}
}

/**
 * 代码类
 * 
 * @author wangyuchuan
 *
 */
class CodeFileItem {
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
