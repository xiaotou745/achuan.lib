package com.wychuan.code.db;

import com.wychuan.code.conf.DbSetting;

public class DbOperationFactory {
	public static IDbOperations build(DbSetting dbSetting) {
		switch (dbSetting.getDbType()) {
		case MySql:
			return new MySqlDbOperations(dbSetting);
		case SqlServer:
			return new SqlServerDbOperations(dbSetting);
		default:
			return new SqlServerDbOperations(dbSetting);
		}
	}
}
