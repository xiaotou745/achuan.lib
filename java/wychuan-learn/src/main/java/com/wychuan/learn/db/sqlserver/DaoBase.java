package com.wychuan.learn.db.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoBase {
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DRIVEROFMYSQL = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "Vancl@123";
	private static final String URL = "jdbc:sqlserver://172.18.10.113:1433;databaseName=WYC";

	static {
		try {
			Class.forName(DRIVER);
			Class.forName(DRIVEROFMYSQL);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	protected Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("获取connection失败。");
			e.printStackTrace();
		}
		System.out.println("获取connection成功.");
		return con;
	}
}
