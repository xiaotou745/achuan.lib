package com.wychuan.code.builder.java;

import java.util.Date;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.builder.CodeGeneratorBase;
import com.wychuan.code.builder.IDaoGenerator;
import com.wychuan.code.common.CodeCommon;
import com.wychuan.code.common.StringPlus;
import com.wychuan.code.conf.GenerateConf;
import com.wychuan.util.ParseHelper;

public class DaoGenerator extends CodeGeneratorBase implements IDaoGenerator {

	public DaoGenerator() {
	}

	public DaoGenerator(GenerateConf conf) {
		super(conf);
	}

	@Override
	public String getDaoCode() {
		StringPlus strclass = new StringPlus();
		
		strclass.append(generateHeader());
		
		//生成方法
		strclass.append(generateInsertMethod());
		
		strclass.append(generateUpdateMethod());
		strclass.append(generateDeleteMethod());
		strclass.append(generateGetByIdMethod());
		strclass.append(generateQueryMethod());
		//生成方法结束
		
		strclass.appendLine("}");
		
		return strclass.toString();
	}
	
	private String generateHeader(){
		StringPlus strclass = new StringPlus();
        strclass.appendLine("package " + codeNameConf.getNameSpaceOfDao() + ";");
        strclass.appendLine();

        strclass.appendLine("import java.util.List;");
        strclass.appendLine("import java.util.HashMap;");
        strclass.appendLine("import java.util.Map;");
        strclass.appendLine("import org.springframework.stereotype.Repository;");
        /*StringBuilder sss = new StringBuilder();
        String[] strings = codeNameConf.getNameSpaceOfDao().split(".");
        for (int i = 0; i < strings.length-2; i++)
        {
            sss.AppendFormat("{0}.", strings[i]);
        }
        var namespaces = sss.ToString().Substring(0, sss.Length - 1);
        strclass.AppendLine("import " + namespaces + ".common.DaoBase;");*/
        /*string requestBase = CodeName.getRequestBaseOfJava();
        if (!string.IsNullOrEmpty(requestBase))
        {
            strclass.AppendLine(string.Format("import {0}", requestBase));
        }*/
        strclass.appendLine(String.format("import %s;", codeNameConf.getFullNameOfModel()));
        strclass.appendLine(String.format("import %s;", codeNameConf.getFullNameOfDomain()));
        strclass.appendLine();

        strclass.appendLine("/**");
        strclass.appendLine(" * 数据访问对象 " + codeNameConf.getNameOfDao() + "");
        strclass.appendLine(" * @author " + conf.getAuthor());
        strclass.appendLine(" * @date " + ParseHelper.toDateString(new Date()));
        strclass.appendLine(" *");
        strclass.appendLine(" */");
        strclass.appendLine("@Repository");

        strclass.appendLine("public class " + codeNameConf.getNameOfDao() + " extends DaoBase implements " + codeNameConf.getNameOfDomain() + " {");
        return strclass.toString();
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

		strclass.appendSpaceLine(1, "@Override");
		strclass.appendSpaceLine(1, "public " + returnType + " insert(" + codeNameConf.getParamWithType() + ") {");
		if ("void".equals(returnType)) {
			strclass.appendSpaceLine(2, "getMasterSqlSessionUtil.insert(\"" + codeNameConf.getNameOfDomain()
					+ ".insert\", " + codeNameConf.getParam() + ");");
		} else {
			strclass.appendSpaceLine(2, "return getMasterSqlSessionUtil.insert(\"" + codeNameConf.getNameOfDomain()
					+ ".insert\", " + codeNameConf.getParam() + ");");
		}
		strclass.appendSpaceLine(1, "}");
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

		strclass.appendSpaceLine(1, "@Override");
		strclass.appendSpaceLine(1, "public void update(" + codeNameConf.getParamWithType() + ") {");
		strclass.appendSpaceLine(2, "getMasterSqlSessionUtil().update(\"" + codeNameConf.getNameOfDomain()
				+ ".update\", " + codeNameConf.getParam() + ");");
		strclass.appendSpaceLine(1, "}");
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

		strclass.appendSpaceLine(1, "@Override");
		strclass.appendSpaceLine(1, "public void delete(" + CodeCommon.getKeyParamsOfJava(keys) + ") {");
		strclass.appendSpaceLine(2, "getMasterSqlSessionUtil().delete(\"" + codeNameConf.getNameOfDomain()
				+ ".delete\", " + CodeCommon.getKeyParams(keys) + ");");
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
		strclass.appendSpaceLine(1, " * @return {@code " + codeNameConf.getFullNameOfModel() + "}");
		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @author " + this.conf.getAuthor());
		strclass.appendSpaceLine(1, " * @date " + ParseHelper.toDateString(new Date()));
		strclass.appendSpaceLine(1, " *");
		strclass.appendSpaceLine(1, " * @see " + codeNameConf.getFullNameOfModel());
		strclass.appendSpaceLine(1, " */");

		strclass.appendSpaceLine(1, "@Override");
		strclass.appendSpaceLine(1,
				"public " + codeNameConf.getNameOfModel() + " getById(" + CodeCommon.getKeyParamsOfJava(keys) + ") {");
		strclass.appendSpaceLine(2, "return getMasterSqlSessionUtil().selectOne(\"" + codeNameConf.getNameOfDomain()
				+ ".getById\", " + CodeCommon.getKeyParams(keys) + ");");
		strclass.appendSpaceLine(1, "}");
		strclass.appendLine();

		return strclass.toString();
	}

	@Override
	public String generateQueryMethod() {
		return "";
	}

}
