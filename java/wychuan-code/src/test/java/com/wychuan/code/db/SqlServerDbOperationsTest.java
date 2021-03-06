package com.wychuan.code.db;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.TableInfo;
import com.wychuan.code.conf.DbSetting;

public class SqlServerDbOperationsTest {
	private static DbSetting dbSetting;

	@BeforeClass
	public static void beforeClass() {
		dbSetting = new DbSetting();
		dbSetting.setDbType(DbType.SqlServer);
		// dbSetting.setDbServer("172.18.10.113");
		dbSetting.setDbServer("127.0.0.1");
		dbSetting.setUserName("sa");
		dbSetting.setPassword("Vancl@123");
	}

	SqlServerDbOperations db = null;

	@Before
	public void before() {
		db = new SqlServerDbOperations(dbSetting);
	}

	@Test
	public void getDbListTest() throws SQLException {
		List<String> dbList = db.getDbList();
		assertNotNull(dbList);
		for (String dbname : dbList) {
			System.out.println(dbname);
		}
	}

	@Test
	public void getTableNameListTest() throws SQLException {
		List<String> tables = db.getTables("WYC");
		assertNotNull(tables);
		for (String tablename : tables) {
			System.out.println(tablename);
		}
	}

	@Test
	public void getTablesInfoTest() {
		List<TableInfo> tablesInfo = db.getTablesInfo("WYC");
		assertNotNull(tablesInfo);
		for (TableInfo table : tablesInfo) {
			System.out.println(table);
		}
	}

	@Test
	public void propertytest() {
		String strTest = "wangychuan\ntestsfsdf";
		Properties properties = new Properties();
		properties.put("test", strTest);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("properties2.xml");
			properties.storeToXML(fos, "key-value");
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileInputStream in;
		try {
			in = new FileInputStream("properties2.xml");
			Properties df = new Properties();
			df.loadFromXML(in);
			df.list(System.out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getColumnInfoTest() throws SQLException {
		List<ColumnInfo> columnsInfo = db.getColumnsInfo("WYC", "User");
		assertNotNull(columnsInfo);
		for(ColumnInfo column : columnsInfo){
			System.out.println(column);
		}
	}
}
