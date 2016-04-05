package com.wychuan.code.common;

public enum ConvertType {
	DbToCS(0, "DbToCS"), 
	DbToJava(1, "DbToJava"),
	DbToJdbc(2, "DbToJdbc");

	private int value = 0;
	private String desc;

	ConvertType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public ConvertType valueOf(int value) {
		for (ConvertType type : ConvertType.values()) {
			if (type.value() == value) {
				return type;
			}
		}
		return null;
	}
}
