package com.wychuan.code.builder.java;

import java.util.List;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.builder.CodeGeneratorBase;
import com.wychuan.code.common.CodeCommon;
import com.wychuan.code.common.StringPlus;
import com.wychuan.code.conf.GenerateConf;
import com.wychuan.util.StringUtils;

public class MyBatisXmlMapperGenerator extends CodeGeneratorBase {
	public MyBatisXmlMapperGenerator() {
	}

	public MyBatisXmlMapperGenerator(GenerateConf conf) {
		super(conf);
	}

	public String getXml() {
		StringPlus strclass = new StringPlus();
		strclass.append(generateHeader());

		strclass.append(generateBaseResultMap());

		strclass.append(generateBaseColumnSql());

		strclass.append(generateInsertSql());

		strclass.append(generateUpdateSql());

		strclass.append(generateDeleteSql());

		strclass.append(generateGetByIdSql());

		strclass.append(generateSelectSql());

		strclass.appendLine("</mapper>");

		return strclass.toString();
	}

	/**
	 * 生成xml文件头。
	 * 
	 * @return
	 */
	private String generateHeader() {
		StringPlus strclass = new StringPlus();

		strclass.appendLine("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		strclass.appendLine();

		strclass.appendLine("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
		strclass.appendLine();
		strclass.appendLine(String.format("<mapper namespace=\"%s\">", codeNameConf.getFullNameOfDomain()));
		strclass.appendLine();

		return strclass.getValue();
	}

	/**
	 * 生成resultMap
	 * 
	 * @return
	 */
	private String generateBaseResultMap() {
		StringPlus strclass = new StringPlus();

		strclass.appendSpaceLine(1,
				String.format("<resultMap type=\"%s\" id=\"baseResultMap\">", codeNameConf.getFullNameOfModel()));
		for (ColumnInfo field : fields) {
			if (field.isIdentity()) {
				strclass.appendSpaceLine(
						2,
						String.format("<id property=\"%s\" column=\"%s\" jdbcType=\"%s\"></id>",
								CodeCommon.setFirstCharLower(field.getName()), field.getName(),
								CodeCommon.dbTypeToJdbc(field.getType())));
				continue;
			}
			strclass.appendSpaceLine(
					2,
					String.format("<result property=\"%s\" column=\"%s\" jdbcType=\"%s\" />",
							CodeCommon.setFirstCharLower(field.getName()), field.getName(),
							CodeCommon.dbTypeToJdbc(field.getType())));
		}
		strclass.appendSpaceLine(1, "</resultMap>");
		strclass.appendLine();
		return strclass.toString();
	}

	/**
	 * 生成字段列表sql
	 * 
	 * @return
	 */
	private String generateBaseColumnSql() {
		StringPlus strclass = new StringPlus();
		StringPlus strFields = new StringPlus();
		String tableAlias = CodeCommon.getFirstChars(conf.getTableName()).toLowerCase();
		strclass.appendSpaceLine(1, "<sql id=\"baseColumnList\">");
		for (ColumnInfo field : fields) {
			strFields.append(tableAlias + "." + field.getName() + ",");
		}
		strFields.deleteLastComma();
		strclass.appendSpaceLine(2, strFields.toString());
		strclass.appendSpaceLine(1, "</sql>");
		strclass.appendLine();
		return strclass.toString();
	}

	/**
	 * 生成insert sql
	 * 
	 * @return
	 */
	private String generateInsertSql() {
		StringPlus strclass = new StringPlus(); // insert sql
		StringPlus strInsertFields = new StringPlus(); // insert 字段列表
		StringPlus strInsertValues = new StringPlus(); // insert value列表
		StringPlus strIndentity = new StringPlus(); // 自增主键

		for (ColumnInfo field : fields) {
			if (field.isIdentity()) {
				strIndentity.append(String.format("useGeneratedKeys=\"true\" keyProperty=\"%s\"",
						CodeCommon.setFirstCharLower(field.getName())));
				continue;
			}
			String columnName = field.getName();
			strInsertFields.append(columnName + ",");
			strInsertValues.appendSpaceLine(3, "#{" + CodeCommon.setFirstCharLower(columnName) + ",jdbcType="
					+ CodeCommon.dbTypeToJdbc(field.getType()) + "},");
		}
		strclass.appendSpaceLine(1, String.format("<insert id=\"insert\" parameterType=\"%s\" %s>",
				codeNameConf.getFullNameOfModel(), StringUtils.isEmpty(strIndentity) ? "" : strIndentity.toString()));
		// 去掉最后的逗号
		strInsertFields.deleteLastComma();
		strInsertValues.deleteLastComma();

		strclass.appendSpaceLine(2, "insert into " + conf.getTableName() + "(" + strInsertFields + ")");
		strclass.appendSpaceLine(2, "values(");
		strclass.appendLine(strInsertValues.toString());
		
		strclass.appendSpaceLine(2, ")");

		strclass.appendSpaceLine(1, "</insert>");
		strclass.appendLine();

		return strclass.toString();
	}

	/**
	 * 生成where条件语句sql
	 * 
	 * @param paramKeys
	 * @return
	 */
	private String generateWhereExpression(List<ColumnInfo> paramKeys) {
		StringPlus strclass = new StringPlus();
		for (ColumnInfo key : paramKeys) {
			strclass.append(key.getName() + "=#{" + CodeCommon.setFirstCharLower(key.getName()) + ",jdbcType="
					+ CodeCommon.dbTypeToJdbc(key.getType()) + "} and ");
		}
		strclass.deleteLastChar("and");
		return strclass.getValue();
	}

	/**
	 * 生成update sql
	 * 
	 * @return
	 */
	private String generateUpdateSql() {
		StringPlus strclass = new StringPlus();
		StringPlus strSetValue = new StringPlus();
		strclass.appendSpaceLine(1,
				String.format("<update id=\"update\" parameterType=\"%s\">", codeNameConf.getFullNameOfModel()));

		int index = 0;
		for (ColumnInfo field : fields) {
			String columnName = field.getName();
			if (field.isIdentity() || field.isPK()) {
				continue;
			}
			if (index == 0) {
				strSetValue.appendLine(columnName + "=#{" + CodeCommon.setFirstCharLower(columnName) + ",jdbcType="
						+ CodeCommon.dbTypeToJdbc(field.getType()) + "},");
			} else {
				strSetValue.appendSpaceLine(3, columnName + "=#{" + CodeCommon.setFirstCharLower(columnName)
						+ ",jdbcType=" + CodeCommon.dbTypeToJdbc(field.getType()) + "},");
			}
			index++;
		}

		// 去掉最后的逗号
		strSetValue.deleteLastComma();

		strclass.appendSpaceLine(2, "update " + conf.getTableName());
		strclass.appendSpaceLine(2, "set " + strSetValue);
		strclass.appendSpaceLine(2, "where " + generateWhereExpression(keys));

		strclass.appendSpaceLine(1, "</update>");
		strclass.appendLine();

		return strclass.getValue();
	}

	/**
	 * 生成delete sql语句
	 * 
	 * @return
	 */
	private String generateDeleteSql() {
		StringPlus strclass = new StringPlus();

		strclass.appendSpaceLine(1, "<delete id=\"delete\" parameterType=\"java.lang.Integer\">");
		strclass.appendSpaceLine(2, "delete from " + conf.getTableName() + " where " + generateWhereExpression(keys));
		strclass.appendSpaceLine(1, "</delete>");
		strclass.appendLine();

		return strclass.toString();
	}

	/**
	 * 生成getbyid sql语句
	 * 
	 * @return
	 */
	private String generateGetByIdSql() {
		StringPlus strclass = new StringPlus();

		String tableAlias = CodeCommon.getFirstChars(conf.getTableName()).toLowerCase();

		strclass.appendSpaceLine(1,
				"<select id=\"getById\" resultMap=\"baseResultMap\" parameterType=\"java.lang.Integer\">");
		strclass.appendSpaceLine(2, "select <include refid=\"baseColumnList\"></include> from " + conf.getTableName()
				+ " " + tableAlias + "(nolock) where " + generateWhereExpression(keys));
		strclass.appendSpaceLine(1, "</select>");
		strclass.appendLine();

		return strclass.toString();
	}

	/**
	 * 生成select 语句
	 * 
	 * @return
	 */
	private String generateSelectSql() {
		StringPlus strclass = new StringPlus();

		String tableAlias = CodeCommon.getFirstChars(conf.getTableName()).toLowerCase();

		strclass.appendSpaceLine(1, "<select id=\"select\" resultMap=\"baseResultMap\">");
		strclass.appendSpaceLine(2, "select <include refid=\"baseColumnList\"></include> from " + conf.getTableName()
				+ " " + tableAlias + "(nolock) where " + generateWhereExpression(keys));
		strclass.appendSpaceLine(1, "</select>");
		strclass.appendLine();

		return strclass.toString();
	}
}
