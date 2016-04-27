package com.wychuan.code.conf;

import java.util.List;
import java.util.Map;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.db.DbType;

public class GenerateConf {
	/**
	 * 数据库类型，默认为SqlServer
	 */
	private DbType dbType = DbType.SqlServer;

	/**
	 * 编程语言，默认为Java
	 */
	private CodeLanguage codeLanguage = CodeLanguage.Java;

	/**
	 * 生成作者，如果为空，则使用系统用户名
	 */
	private String author;

	/**
	 * 表字段列表；
	 */
	private List<ColumnInfo> fields;

	/**
	 * 命名相关配置信息
	 */
	private CodeNameConf codeNameConf;

	/**
	 * 主键字段列表
	 */
	private List<ColumnInfo> keys;
	
	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * @return the dbType
	 */
	public DbType getDbType() {
		return dbType;
	}

	/**
	 * @param dbType
	 *            the dbType to set
	 */
	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}

	/**
	 * @return the codeLanguage
	 */
	public CodeLanguage getCodeLanguage() {
		return codeLanguage;
	}

	/**
	 * @param codeLanguage
	 *            the codeLanguage to set
	 */
	public void setCodeLanguage(CodeLanguage codeLanguage) {
		this.codeLanguage = codeLanguage;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		if (this.author == null || "".equals(this.author)) {
			Map<String, String> env = System.getenv();
			author = env.get("USERNAME");
		}
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the fields
	 */
	public List<ColumnInfo> getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(List<ColumnInfo> fields) {
		this.fields = fields;
	}

	/**
	 * @return the codeNameConf
	 */
	public CodeNameConf getCodeNameConf() {
		return codeNameConf;
	}

	/**
	 * @param codeNameConf
	 *            the codeNameConf to set
	 */
	public void setCodeNameConf(CodeNameConf codeNameConf) {
		this.codeNameConf = codeNameConf;
	}
	
	/**
	 * @return the keys
	 */
	public List<ColumnInfo> getKeys() {
		return keys;
	}

	/**
	 * @param keys
	 *            the keys to set
	 */
	public void setKeys(List<ColumnInfo> keys) {
		this.keys = keys;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
