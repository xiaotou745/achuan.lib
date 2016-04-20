package com.wychuan.dbtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("table")
public class TableController {
	@RequestMapping("")
	public ModelAndView tableView(){
		ModelAndView model = new ModelAndView("dbtoolsView");
		model.addObject("currenttitle","表结构查看");
		model.addObject("viewPath", "table/index");
		return model;
	}
}
