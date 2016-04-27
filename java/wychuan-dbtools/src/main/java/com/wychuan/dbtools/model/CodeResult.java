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
