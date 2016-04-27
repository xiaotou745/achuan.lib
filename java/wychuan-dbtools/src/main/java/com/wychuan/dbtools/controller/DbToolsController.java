package com.wychuan.dbtools.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wychuan.code.ColumnInfo;
import com.wychuan.code.builder.GeneratorFactory;
import com.wychuan.code.builder.IDaoGenerator;
import com.wychuan.code.builder.IModelGenerator;
import com.wychuan.code.builder.IServiceGenerator;
import com.wychuan.code.builder.IServiceImplGenerator;
import com.wychuan.code.conf.CodeLanguage;
import com.wychuan.code.conf.CodeNameConf;
import com.wychuan.code.conf.DbSetting;
import com.wychuan.code.conf.DbSettingBuilder;
import com.wychuan.code.conf.GenerateConf;
import com.wychuan.code.db.DbOperationFactory;
import com.wychuan.code.db.IDbOperations;
import com.wychuan.dbtools.common.ModelViewUtils;
import com.wychuan.dbtools.htmltables.HtmlTableInfo;
import com.wychuan.dbtools.model.CodeFileItem;
import com.wychuan.dbtools.model.CodeGenerateParams;
import com.wychuan.dbtools.model.CodeResult;
import com.wychuan.dbtools.model.TableDescModifyParams;
import com.wychuan.dbtools.model.TableViewModel;
import com.wychuan.dbtools.model.TableViewParams;
import com.wychuan.dbtools.service.HtmlTableGetFactory;

@Controller
@RequestMapping("dbtools")
public class DbToolsController {

	/**
	 * 表结构查看器
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("tableview")
	public ModelAndView tableView() throws SQLException {
		ModelViewUtils viewUtils = ModelViewUtils.build().setTitle("表结构查看").setViewPath("dbtools/tableview")
				.setFooterJs("dbtools/_tableviewJs");

		TableViewModel model = new TableViewModel();
		List<DbSetting> dbSettings = DbSettingBuilder.getDbSettings();

		// 默认取第一个服务器；
		DbSetting defaultDb = dbSettings.get(0);

		// 取第一个服务器的数据库列表
		HtmlTableInfo htmlTable = HtmlTableGetFactory.instance(1).setDbSetting(defaultDb).getService().getHtmlTable();

		model.setDbSettings(dbSettings);
		model.setCurrentServerId(defaultDb.getId());

		model.setCurrentDbName("");
		model.setCurrentTableName("");
		model.setCurrentViewType(1);
		model.setHtmlResult(htmlTable);

		model.setTableHasEditOper(String.valueOf(true));
		model.setTableHasGenerateCodeOper(String.valueOf(false));

		viewUtils.setData(model);

		return viewUtils.get();
	}

	/**
	 * 刷新页面，即点击服务器显示数据库，点击数据库显示表名，点击表名查询列信息等
	 * 
	 * @param viewParams
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("refresh")
	public ModelAndView refresh(TableViewParams viewParams) throws SQLException {
		TableViewModel model = new TableViewModel();
		model.setCurrentServerId(viewParams.getDbServerId());
		model.setCurrentViewType(viewParams.getViewType());

		switch (viewParams.getViewType()) {
		case 1:// 当前显示的是数据库列表
			break;
		case 2:// 当前显示的是表列表
			model.setCurrentDbName(viewParams.getDbName());
			break;
		case 3:// 当前显示的是列信息
			model.setCurrentDbName(viewParams.getDbName());
			model.setCurrentTableName(viewParams.getDbTable());
			break;
		}
		model.setHtmlResult(getHtmlTableInfo(viewParams));

		ModelAndView modelAndView = ModelViewUtils.build("dbtools/_tableviewcontents").setData(model).get();

		return modelAndView;
	}

	/**
	 * 获取html的数据
	 * 
	 * @param viewParams
	 * @return
	 * @throws SQLException
	 */
	private HtmlTableInfo getHtmlTableInfo(TableViewParams viewParams) throws SQLException {
		DbSetting dbSetting = DbSettingBuilder.getById(viewParams.getDbServerId());
		dbSetting.setDbName(viewParams.getDbName());

		HtmlTableGetFactory htmlGetter = HtmlTableGetFactory.instance(viewParams.getViewType());
		htmlGetter.setDbSetting(dbSetting).setHasEdit(viewParams.isHasEdit())
				.setHasCodeGenerate(viewParams.isHasGenerateCode()).setUseSection(false).setUseStrippened(true)
				.setTableName(viewParams.getDbTable())
				.setOrderByName(viewParams.isOrderByName());

		return htmlGetter.getService().getHtmlTable();
	}

	@RequestMapping("modifydesc")
	@ResponseBody
	public boolean updateDesc(TableDescModifyParams params) throws SQLException {
		DbSetting dbSetting = DbSettingBuilder.getById(params.getDbServerId());
		IDbOperations dbOperation = DbOperationFactory.build(dbSetting);
		dbOperation.updateProperty(params.getDbName(), params.getTableName(), params.getColumnName(), params.getDesc());
		return true;
	}

	@RequestMapping("code")
	public ModelAndView code() throws SQLException {
		ModelViewUtils viewUtils = ModelViewUtils.build().setTitle("代码生成").setViewPath("dbtools/code")
				.setFooterJs("dbtools/_codeJs");

		TableViewModel model = new TableViewModel();
		List<DbSetting> dbSettings = DbSettingBuilder.getDbSettings();

		// 默认取第一个服务器；
		DbSetting defaultDb = dbSettings.get(0);

		// 取第一个服务器的数据库列表
		HtmlTableInfo htmlTable = HtmlTableGetFactory.instance(1).setDbSetting(defaultDb).getService().getHtmlTable();

		model.setDbSettings(dbSettings);
		model.setCurrentServerId(defaultDb.getId());

		model.setCurrentDbName("");
		model.setCurrentTableName("");
		model.setCurrentViewType(1);
		model.setHtmlResult(htmlTable);

		model.setTableHasEditOper(String.valueOf(true));
		model.setTableHasGenerateCodeOper(String.valueOf(true));

		viewUtils.setData(model);

		return viewUtils.get();
	}

	@RequestMapping("generate")
	public ModelAndView generate(CodeGenerateParams params) throws SQLException {
		GenerateConf conf = getGenerateConf(params);
		GeneratorFactory generator = GeneratorFactory.instance(conf);
		
		TableViewModel model = new TableViewModel();
		
		CodeResult codeResult = new CodeResult();
		
		codeResult.setLanguage(CodeLanguage.valueOf(params.getLanguage()));
		
		IModelGenerator modelGenerator = generator.getModelGenerator();
		CodeFileItem modelCode = new CodeFileItem();
		modelCode.setId("tabModelCode");
		modelCode.setTabText("Model");
		modelCode.setCode(modelGenerator.getModelCode());
		codeResult.getCodes().add(modelCode);
		
		IServiceGenerator serviceGenerator = generator.getServiceGenerator();
		CodeFileItem serviceCode = new CodeFileItem();
		serviceCode.setId("tabServiceCode");
		serviceCode.setTabText("Service");
		serviceCode.setCode(serviceGenerator.getServiceCode());
		codeResult.getCodes().add(serviceCode);
		
		IServiceImplGenerator serviceImplGenerator = generator.getServiceImplGenerator();
		CodeFileItem serviceImpl = new CodeFileItem();
		serviceImpl.setId("tabServiceImplCode");
		serviceImpl.setTabText("ServiceImpl");
		serviceImpl.setCode(serviceImplGenerator.getServiceImplCode());
		codeResult.getCodes().add(serviceImpl);
		
		
		IDaoGenerator domainGenerator = generator.getDomainGenerator();
		CodeFileItem domainCode = new CodeFileItem();
		domainCode.setId("tabDomainCode");
		domainCode.setTabText("Domain");
		domainCode.setCode(domainGenerator.getDaoCode());
		codeResult.getCodes().add(domainCode);
		
		IDaoGenerator daoGenerator = generator.getDaoGenerator();
		CodeFileItem daoCode = new CodeFileItem();
		daoCode.setId("tabDaoCode");
		daoCode.setTabText("Dao");
		daoCode.setCode(daoGenerator.getDaoCode());
		codeResult.getCodes().add(daoCode);
		
		model.setCodeResult(codeResult);
		
		ModelViewUtils modelViewUtils = ModelViewUtils.build("/dbtools/_coderesult").setData(model);
		
		return modelViewUtils.get();
	}
	
	

	private GenerateConf getGenerateConf(CodeGenerateParams params) throws SQLException {
		GenerateConf conf = new GenerateConf();

		DbSetting dbSetting = DbSettingBuilder.getById(params.getServerId());
		IDbOperations dbOperation = DbOperationFactory.build(dbSetting);
		List<ColumnInfo> lstColumns = dbOperation.getColumnsInfo(params.getDbName(), params.getTableName());
		List<ColumnInfo> lstKeys = new ArrayList<ColumnInfo>();
		for (ColumnInfo col : lstColumns) {
			if (col.isPK()) {
				lstKeys.add(col);
			}
		}

		conf.setFields(lstColumns);
		conf.setKeys(lstKeys);

		conf.setAuthor("wangyuchuan");
		conf.setCodeLanguage(CodeLanguage.valueOf(params.getLanguage()));
		conf.setTableName(params.getTableName());
		conf.setDbType(dbSetting.getDbType());
		conf.setCodeNameConf(getCodeNameConf(params.getTableName()));

		return conf;
	}

	private CodeNameConf getCodeNameConf(String tableName) {
		CodeNameConf conf = new CodeNameConf();
		conf.setNameOfModel(tableName);

		conf.setNameSpaceOfDao("com.wychuan.dao.impl");
		conf.setNameSpaceOfDomain("com.wychuan.dao.inter");
		conf.setNameSpaceOfModel("com.wychuan.model");
		conf.setNameSpaceOfService("com.wychuan.service.inter");
		conf.setNameOfServiceImpl("com.wychuan.service.impl");
		return conf;
	}
}
