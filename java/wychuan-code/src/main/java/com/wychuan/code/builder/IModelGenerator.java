package com.wychuan.code.builder;

public interface IModelGenerator {
	/**
	 * 返回Model类代码字符串
	 * 
	 * @return
	 */
	String getModelCode();

	/**
	 * 生成实体类的属性
	 * 
	 * @return 返回实体类的属性列表
	 */
	String generatePropertiesCode();
}
