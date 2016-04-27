package com.wychuan.code.builder.java;

import java.util.Date;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.builder.CodeGeneratorBase;
import com.wychuan.code.builder.IServiceImplGenerator;
import com.wychuan.code.common.CodeCommon;
import com.wychuan.code.common.StringPlus;
import com.wychuan.code.conf.GenerateConf;
import com.wychuan.util.ParseHelper;

public class ServiceImplGenerator extends CodeGeneratorBase implements IServiceImplGenerator {

	public ServiceImplGenerator() {
		// TODO Auto-generated constructor stub
	}

	public ServiceImplGenerator(GenerateConf conf) {
		super(conf);
	}

	@Override
	public String getServiceImplCode() {
		StringPlus strclass = new StringPlus();

		strclass.append(generateHead());

		// 生成方法开始
		strclass.append(generateCreateMethod());
		strclass.append(generateModifyMethod());
		strclass.append(generateRemoveMethod());
		strclass.append(generateGetByIdMethod());
		strclass.append(generateQueryMethod());
		// 生成方法结束

		strclass.append("}");

		return strclass.toString();
	}

	private String generateHead() {
		StringPlus strclass = new StringPlus();

		strclass.appendLine("package " + codeNameConf.getNameSpaceOfServiceImpl() + ";");
		strclass.appendLine();

		strclass.appendLine("import java.util.List");
		strclass.appendLine("import org.springframework.beans.factory.annotation.Autowired;");
		strclass.appendLine("import org.springframework.stereotype.Service");
		strclass.appendLine();
		strclass.appendLine(String.format("import %s;", codeNameConf.getFullNameOfModel()));
		strclass.appendLine(String.format("import %s;", codeNameConf.getFullNameOfService()));
		strclass.appendLine(String.format("import %s;", codeNameConf.getFullNameOfDomain()));
		strclass.appendLine();

		strclass.appendLine("/**");
		strclass.appendLine(" * 服务提供对象 {@code " + codeNameConf.getNameOfServiceImpl() + "}");
		strclass.appendLine(" * @author " + conf.getAuthor());
		strclass.appendLine(" * @date " + ParseHelper.toDateString(new Date()));
		strclass.appendLine(" */");
		strclass.appendLine("@Service");

		strclass.appendLine("public class " + codeNameConf.getNameOfServiceImpl() + " implements "
				+ codeNameConf.getNameOfService() + " {");
		strclass.appendLine();
		strclass.appendSpaceLine(1, "@Autowired");
		strclass.appendSpaceLine(1,
				"private " + codeNameConf.getNameOfDomain() + " " + codeNameConf.getServiceImplDepedencyObj() + ";");
		strclass.appendLine();
		return strclass.toString();
	}

	@Override
	public String generateCreateMethod() {
		StringPlus strclass = new StringPlus();

		strclass.appendSpaceLine(1, "/**");
		strclass.appendSpaceLine(1, " * 新增一条记录");
		strclass.appendSpaceLine(1, " * @param " + codeNameConf.getParam() + " 要新增的对象.");
		if (hasIdentity) {
			strclass.appendSpaceLine(1, " * @return 返回新增对象的自增Id");
		}
		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @author " + this.conf.getAuthor());
		strclass.appendSpaceLine(1, " * @date " + ParseHelper.toDateString(new Date()));

		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @see " + codeNameConf.getFullNameOfModel());
		strclass.appendSpaceLine(1, " */");

		String returnType = "void";
		if (hasIdentity) {
			returnType = identityType;
		}

		strclass.appendSpaceLine(1, "@Override");
		strclass.appendSpaceLine(1, "public " + returnType + " create(" + codeNameConf.getParamWithType() + ") {");
		if ("void".equals(returnType)) {
			strclass.appendSpaceLine(2,
					codeNameConf.getServiceImplDepedencyObj() + ".insert(" + codeNameConf.getParam() + ");");
		} else {
			strclass.appendSpaceLine(2, "return " + codeNameConf.getServiceImplDepedencyObj() + ".insert("
					+ codeNameConf.getParam() + ");");
		}

		strclass.appendSpaceLine(1, "}");
		strclass.appendLine();
		return strclass.toString();
	}

	@Override
	public String generateModifyMethod() {
		StringPlus strclass = new StringPlus();

		strclass.appendSpaceLine(1, "/**");
		strclass.appendSpaceLine(1, " * 修改一条记录");
		strclass.appendSpaceLine(1, " * @param " + codeNameConf.getParam() + " 要修改的对象.");
		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @author " + this.conf.getAuthor());
		strclass.appendSpaceLine(1, " * @date " + ParseHelper.toDateString(new Date()));
		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @see " + codeNameConf.getFullNameOfModel());
		strclass.appendSpaceLine(1, " */");

		strclass.appendSpaceLine(1, "@Override");
		strclass.appendSpaceLine(1, "public void modify(" + codeNameConf.getParamWithType() + ") {");
		strclass.appendSpaceLine(2, codeNameConf.getServiceImplDepedencyObj() + ".update(" + codeNameConf.getParam()
				+ ");");
		strclass.appendSpaceLine(1, "}");
		strclass.appendLine();
		return strclass.toString();
	}

	@Override
	public String generateRemoveMethod() {
		if (keys == null || keys.size() == 0) {
			return "";
		}
		StringPlus strclass = new StringPlus();

		strclass.appendSpaceLine(1, "/**");
		strclass.appendSpaceLine(1, " * 删除一条记录");
		for (ColumnInfo key : keys) {
			strclass.appendSpaceLine(1,
					" * @param " + CodeCommon.setFirstCharLower(key.getName()) + " " + key.getDescription());
		}
		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @author " + this.conf.getAuthor());
		strclass.appendSpaceLine(1, " * @date " + ParseHelper.toDateString(new Date()));
		strclass.appendSpaceLine(1, " */");

		strclass.appendSpaceLine(1, "@Override");
		strclass.appendSpaceLine(1, "public void remove(" + CodeCommon.getKeyParamsOfJava(keys) + ") {");
		strclass.appendSpaceLine(2,
				codeNameConf.getServiceImplDepedencyObj() + ".delete(" + CodeCommon.getKeyParams(keys) + ");");
		strclass.appendSpaceLine(1, "}");
		strclass.appendLine();
		return strclass.toString();
	}

	@Override
	public String generateGetByIdMethod() {
		if (keys == null || keys.size() == 0) {
			return "";
		}
		StringPlus strclass = new StringPlus();

		strclass.appendSpaceLine(1, "/**");
		strclass.appendSpaceLine(1, " * 根据Id得到一个对象实体");
		for (ColumnInfo key : keys) {
			strclass.appendSpaceLine(1,
					" * @param " + CodeCommon.setFirstCharLower(key.getName()) + " " + key.getDescription());
		}
		strclass.appendSpaceLine(1, " * @return {code " + codeNameConf.getFullNameOfModel() + "}");
		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @author " + this.conf.getAuthor());
		strclass.appendSpaceLine(1, " * @date " + ParseHelper.toDateString(new Date()));
		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @see " + codeNameConf.getFullNameOfModel());
		strclass.appendSpaceLine(1, " */");

		strclass.appendSpaceLine(1, "@Override");
		strclass.appendSpaceLine(1,
				"public " + codeNameConf.getNameOfModel() + " getById(" + CodeCommon.getKeyParamsOfJava(keys) + ") {");
		strclass.appendSpaceLine(2,
				"return " + codeNameConf.getServiceImplDepedencyObj() + ".getById(" + CodeCommon.getKeyParams(keys)
						+ ");");
		strclass.appendSpaceLine(1, "}");
		strclass.appendLine();

		return strclass.toString();
	}

	@Override
	public String generateQueryMethod() {
		return "";
	}
}
