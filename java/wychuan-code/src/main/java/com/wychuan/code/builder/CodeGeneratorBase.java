package com.wychuan.code.builder;

import java.util.List;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.common.CodeCommon;
import com.wychuan.code.conf.CodeNameConf;
import com.wychuan.code.conf.GenerateConf;

public abstract class CodeGeneratorBase {
	/**
	 * 代码生成配置项
	 */
	protected GenerateConf conf;

	/**
	 * 获取代码生成配置信息
	 * 
	 * @return
	 */
	public GenerateConf getConf() {
		return conf;
	}

	/**
	 * 设置代码生成配置信息
	 * 
	 * @param generateConf
	 */
	public void setConf(GenerateConf generateConf) {
		this.conf = generateConf;
	}

	/**
	 * 构造函数
	 */
	public CodeGeneratorBase() {

	}

	/**
	 * 构造函数
	 * 
	 * @param generateConf
	 */
	public CodeGeneratorBase(GenerateConf generateConf) {
		this.conf = generateConf;
		this.fields = generateConf.getFields();
		this.codeNameConf = generateConf.getCodeNameConf();
		this.keys = generateConf.getKeys();
		if (this.keys != null && this.keys.size() > 0) {
			for (ColumnInfo key : this.keys) {
				if (key.isIdentity()) {
					this.hasIdentity = true;
					identityType = CodeCommon.dbTypeToJava(key.getType());
				}
			}
		}
	}

	/**
	 * 主键字段列表
	 */
	protected List<ColumnInfo> keys;

	/**
	 * 表字段列表
	 */
	protected List<ColumnInfo> fields;
	/**
	 * 命名规则信息
	 */
	protected CodeNameConf codeNameConf;

	/**
	 * 是否含有自增主键
	 */
	protected boolean hasIdentity = false;

	/**
	 * 主键类型
	 */
	protected String identityType;
}
