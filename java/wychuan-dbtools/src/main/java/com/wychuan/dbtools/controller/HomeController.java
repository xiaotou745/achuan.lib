package com.wychuan.dbtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
	@RequestMapping("")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView("dbtoolsView");
		view.addObject("currenttitle", "首页");
		view.addObject("viewPath", "home/index");
		return view;
	}
}
