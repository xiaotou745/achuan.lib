package com.wychuan.code.builder.java;

import org.junit.Test;

public class JavaCodeGeneratorTest extends CodeGeneratorTest {

	@Test
	public void generateServiceCodeTest(){
		ServiceGenerator codeGenerator  = new ServiceGenerator(conf);
		String serviceCode = codeGenerator.getServiceCode();
		System.out.println(serviceCode);
	}
	
	@Test
	public void generateServiceImplCodeTest(){
		ServiceImplGenerator codeGenerator = new ServiceImplGenerator(conf);
		String serviceImplCode = codeGenerator.getServiceImplCode();
		System.out.println(serviceImplCode);
	}
	
	@Test
	public void getModelCodeTest(){
		ModelGenerator builder = new ModelGenerator(conf);
		String modelCode = builder.getModelCode();
		System.out.println(modelCode);
	}
	
	@Test
	public void generateDomainCodeTest(){
		DomainGenerator generator = new DomainGenerator(conf);
		String daoCode = generator.getDaoCode();
		System.out.println(daoCode);
	}
	
	@Test
	public void generateDaoCodeTest(){
		DaoGenerator daoGenerator = new DaoGenerator(conf);
		String daoCode = daoGenerator.getDaoCode();
		System.out.println(daoCode);
	}
	
	@Test
	public void generateXmlMapper(){
		MyBatisXmlMapperGenerator generator = new MyBatisXmlMapperGenerator(conf);
		String xml = generator.getXml();
		System.out.println(xml);
	}
}
