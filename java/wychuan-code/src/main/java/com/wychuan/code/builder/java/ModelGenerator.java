package com.wychuan.code.builder.java;

import java.util.Date;
import com.wychuan.code.ColumnInfo;
import com.wychuan.code.builder.CodeGeneratorBase;
import com.wychuan.code.builder.IModelGenerator;
import com.wychuan.code.common.CodeCommon;
import com.wychuan.code.common.StringPlus;
import com.wychuan.code.conf.GenerateConf;
import com.wychuan.util.ParseHelper;

public class ModelGenerator extends CodeGeneratorBase implements IModelGenerator {

	public ModelGenerator() {
	}

	public ModelGenerator(GenerateConf conf) {
		super(conf);
	}

	/**
	 * 获取model层代码
	 */
	@Override
	public String getModelCode() {
		StringPlus strclass = new StringPlus();

		strclass.appendLine("package " + codeNameConf.getNameSpaceOfModel() + ";");
		strclass.appendLine();

		strclass.appendLine("import java.util.Date;");
		strclass.appendLine();

		strclass.appendLine("/**");
		strclass.appendLine(" * 实体类" + codeNameConf.getNameOfModel() + ". (属性说明自动提取数据库字段的描述信息)");
		strclass.appendLine(" *");
		strclass.appendLine(" * @author " + this.conf.getAuthor());
		strclass.appendLine(" * @date " + ParseHelper.toDateString(new Date()));
		strclass.appendLine(" *");
		strclass.appendLine(" */");

		strclass.appendLine("public class " + codeNameConf.getNameOfModel() + " {");
		strclass.appendLine(generateFieldsCode());
		strclass.appendLine(generatePropertiesCode());
		strclass.appendLine("}");

		return strclass.toString();
	}

	private String generateFieldsCode() {
		StringPlus strclass = new StringPlus();
		for (ColumnInfo field : fields) {
			String columnName = field.getName();// 列名
			String columnType = field.getType();// 列类型
			String deText = field.getDescription();// 列描述
			columnType = CodeCommon.dbTypeToJava(columnType);
			strclass.appendSpaceLine(1, "/**");
			strclass.appendSpaceLine(1, " * " + deText);
			strclass.appendSpaceLine(1, " */");
			strclass.appendSpaceLine(1,
					String.format("private %s %s;", columnType, CodeCommon.setFirstCharLower(columnName)));
		}
		return strclass.toString();
	}

	@Override
	public String generatePropertiesCode() {
		StringPlus strclass2 = new StringPlus();
		for (ColumnInfo field : fields) {
			String columnName = field.getName();
			String columnType = field.getType();
			String deText = field.getDescription();
			columnType = CodeCommon.dbTypeToJava(columnType);
			strclass2.appendSpaceLine(1, "/**");
			strclass2.appendSpaceLine(1, " * 获取" + deText);
			strclass2.appendSpaceLine(1, " */");

			strclass2.appendSpaceLine(1,
					String.format("public %s get%s() {", columnType, CodeCommon.setFirstCharUpper(columnName)));
			strclass2.appendSpaceLine(2, "return " + CodeCommon.setFirstCharLower(columnName) + ";");
			strclass2.appendSpaceLine(1, "}");
			strclass2.appendLine();

			strclass2.appendSpaceLine(1, "/**");
			strclass2.appendSpaceLine(1, " * 设置" + deText);
			strclass2.appendSpaceLine(1, " * @param " + CodeCommon.setFirstCharLower(columnName) + " " + deText);
			strclass2.appendSpaceLine(1, " */");
			strclass2.appendSpaceLine(1, String.format("public void set%s(%s, %s) {",
					CodeCommon.setFirstCharUpper(columnName), columnType, CodeCommon.setFirstCharLower(columnName)));
			strclass2.appendSpaceLine(
					2,
					String.format("this.%s = %s", CodeCommon.setFirstCharLower(columnName),
							CodeCommon.setFirstCharLower(columnName)));
			strclass2.appendSpace(1, "}");
		}
		return strclass2.toString();
	}

}
