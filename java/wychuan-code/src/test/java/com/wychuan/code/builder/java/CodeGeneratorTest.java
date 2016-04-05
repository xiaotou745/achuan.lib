package com.wychuan.code.builder.java;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.conf.CodeLanguage;
import com.wychuan.code.conf.CodeNameConf;
import com.wychuan.code.conf.GenerateConf;
import com.wychuan.code.db.DbType;

public class CodeGeneratorTest {
	protected static GenerateConf conf;
	@BeforeClass
	public static void BeforeClass() {
		conf = new GenerateConf();
		conf.setAuthor("wangyuchuan");
		conf.setCodeLanguage(CodeLanguage.Java);
		conf.setDbType(DbType.MySql);
		conf.setCodeNameConf(getCodeName());
		conf.setFields(getColumnList());
		conf.setKeys(getKeys());
		conf.setTableName("User");
	}
	
	private static CodeNameConf getCodeName() {
		CodeNameConf result = new CodeNameConf();
		result.setNameOfModel("User");
		result.setNameSpaceOfModel("com.wychuan.model");
		result.setNameSpaceOfService("com.wychuan.service.inter");
		result.setNameSpaceOfDomain("com.wychuan.dao.inter");
		return result;
	}

	private static List<ColumnInfo> getColumnList() {
		List<ColumnInfo> result = new ArrayList<ColumnInfo>();

		List<ColumnInfo> keys = getKeys();
		for(ColumnInfo key:keys){
			result.add(key);
		}
		
		ColumnInfo name = new ColumnInfo();
		name.setDescription("用户名");
		name.setLength("50");
		name.setName("UserName");
		name.setNull(true);
		name.setType("VARCHAR");
		result.add(name);

		return result;
	}
	
	private static List<ColumnInfo> getKeys(){
		List<ColumnInfo> result = new ArrayList<ColumnInfo>();

		ColumnInfo id = new ColumnInfo();
		id.setDescription("自增id");
		id.setIdentity(true);
		id.setName("id");
		id.setType("int");

		result.add(id);
		return result;
	}
}
