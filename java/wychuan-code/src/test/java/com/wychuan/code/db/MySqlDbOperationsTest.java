package com.wychuan.code.db;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wychuan.code.TableInfo;
import com.wychuan.code.conf.DbSetting;

public class MySqlDbOperationsTest {
	private static DbSetting dbSetting;

	/**
	 * 
	 */
	@BeforeClass
	public static void beforeClass() {
		dbSetting = new DbSetting();
		dbSetting.setDbType(DbType.MySql);
		dbSetting.setDbServer("10.8.8.71");
		dbSetting.setDbName("crowdsourcingdb");
		dbSetting.setUserName("root");
		dbSetting.setPassword("123456");
	}

	MySqlDbOperations db = null;

	@Before
	public void before() {
		db = new MySqlDbOperations(dbSetting);
	}
	
	@Test
	public void getDbListTest() throws SQLException{
		List<String> dbList = db.getDbList();
		assertNotNull(dbList);
		for(String dbname: dbList){
			System.out.println(dbname);
		}
	}
	
	@Test
	public void getTableListTest() throws SQLException{
		List<String> tables = db.getTables("crowdsourcingdb");
		assertNotNull(tables);
		for(String table:tables){
			System.out.println(table);
		}
		
		List<String> tablesOfMysql = db.getTables("mysql");
		assertNotNull(tablesOfMysql);
		System.out.println(tablesOfMysql.size());
	}
	
	@Test
	public void getTablesInfoTest(){
		List<TableInfo> tablesInfo = db.getTablesInfo("crowdsourcingdb");
		assertNotNull(tablesInfo);
		for(TableInfo table : tablesInfo){
			System.out.println(table);
		}
	}
	
	@Test
	public void getColumnInfosTest(){
		
	}
}
