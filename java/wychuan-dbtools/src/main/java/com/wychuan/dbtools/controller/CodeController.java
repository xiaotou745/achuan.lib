package com.wychuan.dbtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("code")
public class CodeController {
	
	@RequestMapping("")
	public ModelAndView index(){
		ModelAndView model = new ModelAndView("dbtoolsView");
		
		model.addObject("currenttitle", "代码生成");
		model.addObject("viewPath", "code/index");
		
		return model;
	}
}
