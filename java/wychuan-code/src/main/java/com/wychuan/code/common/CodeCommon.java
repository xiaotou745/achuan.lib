package com.wychuan.code.common;

import java.util.List;

import com.wychuan.code.ColumnInfo;
import com.wychuan.util.StringUtils;

/**
 * 公共辅助类库
 * 
 * @author wychuan
 * @date 2016-03-10
 */
public class CodeCommon {
	/**
	 * 首字母小写
	 * 
	 * @param str
	 * @return
	 */
	public static String setFirstCharLower(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String setFirstCharUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 数据库类型转换成java类型
	 * 
	 * @param dbtype
	 * @return
	 */
	public static String dbTypeToJava(String dbtype) {
		String value = TypeConverter.getValue(ConvertType.DbToJava.desc(), dbtype);
		if (value == null) {
			return "String";
		}
		return value;
	}
	
	/**
	 * 数据库类型转换成jdbc类型
	 * @param dbtype
	 * @return
	 */
	public static String dbTypeToJdbc(String dbtype){
		String value = TypeConverter.getValue(ConvertType.DbToJdbc.desc(), dbtype.toUpperCase());
		if(StringUtils.isEmpty(value)){
			return dbtype.toUpperCase();
		}
		return value;
	}

	/**
	 * 数据库类型转换成csharp类型
	 * 
	 * @param dbtype
	 * @return
	 */
	public static String dbTypeToCS(String dbtype) {
		String value = TypeConverter.getValue(ConvertType.DbToCS.desc(), dbtype);
		if (value == null) {
			return "string";
		}
		return value;
	}
	
	/**
	 * 获取主键组成的参数列表，带参数类型。
	 * <p>如：{@code Integer id, String user}
	 * @param keys
	 * @return
	 */
	public static String getKeyParamsOfJava(List<ColumnInfo> keys){
		StringPlus strclass = new StringPlus();
		for(ColumnInfo key : keys){
			strclass.append(dbTypeToJava(key.getType())+" " +setFirstCharLower(key.getName())+",");
		}
		strclass.deleteLastComma();
		return strclass.toString();
	}
	
	/**
	 * 获取首字母缩写
	 * <p>如：UserName 的缩写为un
	 * @param text
	 * @return
	 */
	public static String getFirstChars(String text){
		if(StringUtils.isEmpty(text)){
			return "";
		}
		StringPlus str = new StringPlus();
		//TODO 实现
		str.append(text.substring(0,1).toLowerCase());
		for(int i=1;i<text.length();i++){
			char charAt = text.charAt(i);
			if(Character.isUpperCase(charAt)){
				str.append(String.valueOf(charAt).toLowerCase());
			}
		}
		return str.toString();
	}
	
	/**
	 * 获取主键参数值字符串，不带参数类型
	 * <p> 如：{@code id,name}
	 * @param keys
	 * @return
	 */
	public static String getKeyParams(List<ColumnInfo> keys){
		StringPlus strclass = new StringPlus();
		for(ColumnInfo key : keys){
			strclass.append(CodeCommon.setFirstCharLower(key.getName())+",");
		}
		strclass.deleteLastComma();
		return strclass.getValue();
	}
}
