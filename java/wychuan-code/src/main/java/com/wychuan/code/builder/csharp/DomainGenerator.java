package com.wychuan.code.builder.csharp;

import java.util.Date;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.builder.CodeGeneratorBase;
import com.wychuan.code.builder.IDaoGenerator;
import com.wychuan.code.common.CodeCommon;
import com.wychuan.code.common.StringPlus;
import com.wychuan.code.conf.GenerateConf;
import com.wychuan.util.ParseHelper;

public class DomainGenerator extends CodeGeneratorBase implements IDaoGenerator {

	public DomainGenerator() {
	}

	public DomainGenerator(GenerateConf conf) {
		super(conf);
	}

	@Override
	public String generateInsertMethod() {
		StringPlus strclass = new StringPlus();

		strclass.appendSpaceLine(1, "/**");
		strclass.appendSpaceLine(1, " * 新增一条记录");
		strclass.appendSpaceLine(1, " * @param " + codeNameConf.getParam() + " 要新增的对象.");
		if (hasIdentity) {
			strclass.appendSpaceLine(1, " * @return 返回新增对象的自增ID");
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

		strclass.appendSpaceLine(1, returnType + " insert(" + codeNameConf.getParamWithType() + ");");
		strclass.appendLine();
		return strclass.toString();
	}

	@Override
	public String generateUpdateMethod() {
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

		strclass.appendSpaceLine(1, "void update(" + codeNameConf.getParamWithType() + ");");
		strclass.appendLine();
		return strclass.toString();
	}

	@Override
	public String generateDeleteMethod() {
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

		strclass.appendSpaceLine(1, "void delete(" + CodeCommon.getKeyParamsOfJava(keys) + ");");
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
		strclass.appendSpaceLine(1, " * @return {@code " + codeNameConf.getFullNameOfModel() + "}");
		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @author " + this.conf.getAuthor());
		strclass.appendSpaceLine(1, " * @date " + ParseHelper.toDateString(new Date()));
		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @see " + codeNameConf.getFullNameOfModel());
		strclass.appendSpaceLine(1, " */");

		strclass.appendSpaceLine(1, codeNameConf.getNameOfModel() + " getById(" + CodeCommon.getKeyParamsOfJava(keys)
				+ ");");
		strclass.appendLine();

		return strclass.toString();
	}

	@Override
	public String generateQueryMethod() {
		return "";
	}

	private String generateClassHeader() {
		StringPlus strclass = new StringPlus();
		strclass.appendLine("package " + codeNameConf.getNameSpaceOfDomain() + ";");
		strclass.appendLine();

		strclass.appendLine("import java.util.List;");
		strclass.appendLine(String.format("import %s;", codeNameConf.getFullNameOfModel()));
		// string requestBase = CodeName.getRequestBaseOfJava();
		// if (!string.IsNullOrEmpty(requestBase))
		// {
		// strclass.AppendLine(string.Format("import {0}", requestBase));
		// }
		strclass.appendLine();

		strclass.appendLine("/**");
		strclass.appendLine(" * 数据访问接口 " + codeNameConf.getNameOfDomain() + "");
		strclass.appendLine(" * @author " + conf.getAuthor());
		strclass.appendLine(" * @date " + ParseHelper.toDateString(new Date()));
		strclass.appendLine(" *");
		strclass.appendLine(" * @see " + codeNameConf.getFullNameOfModel());
		strclass.appendLine(" */");

		strclass.appendLine("public interface " + codeNameConf.getNameOfDomain() + " {");
		strclass.appendLine();
		return strclass.toString();
	}

	@Override
	public String getDaoCode() {
		StringPlus strclass = new StringPlus();

		strclass.append(generateClassHeader());

		/** 生成方法开始 */
		strclass.append(generateInsertMethod());
		strclass.append(generateUpdateMethod());
		strclass.append(generateDeleteMethod());
		strclass.append(generateGetByIdMethod());
		strclass.append(generateQueryMethod());
		/** 生成方法结束 */

		strclass.append("}");

		return strclass.toString();
	}

}
