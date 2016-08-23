package com.wychuan.learn;

import java.sql.Connection;
import java.sql.SQLException;

import com.wychuan.learn.interview.B;
import com.wychuan.learn.interview.Question1;

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
//    	try {
//			Connection connection = java.sql.DriverManager.getConnection("");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();ß
//		}
//        System.out.println( "Hello World!" );
    	
//    	Question1 question1 = new Question1();
//    	question1.answer();
    	
    	B temp = new B();
    	temp = new B();
    	System.out.println(temp.getValue());
    }
}
