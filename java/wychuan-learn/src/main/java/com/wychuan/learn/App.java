package com.wychuan.learn;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
	private final String jdbcDriver = "com.mysql.jdbc.Driver";
	private final String dbName = "";
	private final String dbUserName = "";
	private final String dbPassword = "";
    public static void main( String[] args )
    {
    	try {
			Connection connection = java.sql.DriverManager.getConnection("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println( "Hello World!" );
    }
}
