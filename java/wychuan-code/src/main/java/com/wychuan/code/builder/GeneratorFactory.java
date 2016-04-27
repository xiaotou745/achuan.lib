package com.wychuan.code.builder;

import com.wychuan.code.builder.java.DaoGenerator;
import com.wychuan.code.builder.java.DomainGenerator;
import com.wychuan.code.builder.java.ModelGenerator;
import com.wychuan.code.builder.java.ServiceGenerator;
import com.wychuan.code.builder.java.ServiceImplGenerator;
import com.wychuan.code.conf.GenerateConf;

public class GeneratorFactory {
	private GenerateConf conf;

	private GeneratorFactory() {

	}

	public static GeneratorFactory instance(GenerateConf conf) {
		GeneratorFactory factory = new GeneratorFactory();
		factory.conf = conf;
		return factory;
	}

	public IServiceGenerator getServiceGenerator() {
		switch (conf.getCodeLanguage()) {
		case CSharp:
			return new com.wychuan.code.builder.csharp.ServiceGenerator(conf);
		case Java:
			return new ServiceGenerator(conf);
		default:
			return new ServiceGenerator(conf);
		}
	}

	public IServiceImplGenerator getServiceImplGenerator() {
		switch (conf.getCodeLanguage()) {
		case CSharp:
			return new com.wychuan.code.builder.csharp.ServiceImplGenerator(conf);
		case Java:
			return new ServiceImplGenerator(conf);
		default:
			return new ServiceImplGenerator(conf);
		}
	}

	public IDaoGenerator getDaoGenerator() {
		switch (conf.getCodeLanguage()) {
		case CSharp:
			return new com.wychuan.code.builder.csharp.DaoGenerator(conf);
		case Java:
			return new DaoGenerator(conf);
		default:
			return new DaoGenerator(conf);
		}
	}

	public IModelGenerator getModelGenerator() {
		switch (conf.getCodeLanguage()) {
		case CSharp:
			return new com.wychuan.code.builder.csharp.ModelGenerator(conf);
		case Java:
			return new ModelGenerator(conf);
		default:
			return new ModelGenerator(conf);
		}
	}

	public IDaoGenerator getDomainGenerator() {
		switch (conf.getCodeLanguage()) {
		case CSharp:
			return new com.wychuan.code.builder.csharp.DomainGenerator(conf);
		case Java:
			return new DomainGenerator(conf);
		default:
			return new DomainGenerator(conf);
		}
	}
}
