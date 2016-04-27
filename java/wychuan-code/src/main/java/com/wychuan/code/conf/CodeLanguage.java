package com.wychuan.code.conf;

public enum CodeLanguage {
	Java(0, "Java"), CSharp(1, "C#");
	
	private int value = 0;
	private String desc = "";

	CodeLanguage(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static CodeLanguage valueOf(int value) {
		for (CodeLanguage codel : CodeLanguage.values()) {
			if (codel.value() == value) {
				return codel;
			}
		}
		return null;
	}
}
