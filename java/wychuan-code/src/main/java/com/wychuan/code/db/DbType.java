package com.wychuan.code.db;

public enum DbType {
	SqlServer(0, "SqlServer"), MySql(1, "MySql"), Oracle(2, "Oracle");

	private int value = 0;
	private String desc;

	DbType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public DbType valueOf(int value) {
		for (DbType dbtype : DbType.values()) {
			if (dbtype.value() == value) {
				return dbtype;
			}
		}
		return null;
	}
}
