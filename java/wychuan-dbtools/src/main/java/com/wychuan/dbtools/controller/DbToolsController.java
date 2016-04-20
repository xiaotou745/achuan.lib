package com.wychuan.dbtools.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wychuan.code.conf.DbSetting;
import com.wychuan.code.conf.DbSettingBuilder;
import com.wychuan.code.db.DbOperationFactory;
import com.wychuan.code.db.IDbOperations;
import com.wychuan.dbtools.common.ModelViewUtils;
import com.wychuan.dbtools.htmltables.HtmlTableInfo;
import com.wychuan.dbtools.model.TableDescModifyParams;
import com.wychuan.dbtools.model.TableViewModel;
import com.wychuan.dbtools.model.TableViewParams;
import com.wychuan.dbtools.service.HtmlTableGetFactory;

@Controller
@RequestMapping("dbtools")
public class DbToolsController {

	/**
	 * 表结构查看器
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("tableview")
	public ModelAndView tableView() throws SQLException {
		ModelViewUtils viewUtils = ModelViewUtils.build().setTitle("表结构查看").setViewPath("dbtools/tableview")
				.setFooterJs("dbtools/_tableviewJs");

		TableViewModel model = new TableViewModel();
		List<DbSetting> dbSettings = DbSettingBuilder.getDbSettings();
		
		//默认取第一个服务器；
		DbSetting defaultDb = dbSettings.get(0);
		
		//取第一个服务器的数据库列表
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
				.setTableName(viewParams.getDbTable());

		return htmlGetter.getService().getHtmlTable();
	}
	
	@RequestMapping("modifydesc")
	@ResponseBody
	public boolean updateDesc(TableDescModifyParams params) throws SQLException{
		DbSetting dbSetting = DbSettingBuilder.getById(params.getDbServerId());
		IDbOperations dbOperation = DbOperationFactory.build(dbSetting);
		dbOperation.updateProperty(params.getDbName(), params.getTableName(), params.getColumnName(), params.getDesc());
		return true;
	}
}
