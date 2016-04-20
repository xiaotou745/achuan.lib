package com.wychuan.dbtools.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public class ModelViewUtils {

	private ModelAndView modelAndView;
	private final String TITLE = "currenttitle";
	private final String VIEW_PATH = "viewPath";
	private final static String DATA = "data";
	private final String FOOTER_JS = "footerJs";

	private ModelViewUtils() {

	}

	public static ModelViewUtils build() {
		ModelViewUtils result = new ModelViewUtils();
		result.modelAndView = new ModelAndView("dbtoolsView");
		return result;
	}

	public static ModelViewUtils build(String viewName) {
		ModelViewUtils result = new ModelViewUtils();
		result.modelAndView = new ModelAndView(viewName);
		return result;
	}

	public ModelViewUtils setTitle(String title) {
		modelAndView.addObject(TITLE, title);
		return this;
	}

	public ModelViewUtils setViewPath(String viewPath) {
		modelAndView.addObject(VIEW_PATH, viewPath);
		return this;
	}

	public ModelViewUtils setData(Object obj) {
		modelAndView.addObject(DATA, obj);
		return this;
	}
	
	public ModelViewUtils setFooterJs(String footerJs){
		modelAndView.addObject(FOOTER_JS, footerJs);
		return this;
	}
	@SuppressWarnings("unchecked")
	public static <T> T getData(HttpServletRequest request){
		Object data = request.getAttribute(DATA);
		return (T) data;
	}

	public ModelAndView get() {
		return modelAndView;
	}
}
