package com.wychuan.dbtools.common;

import com.wychuan.spring.PropertyUtils;

public class UrlGet {
	private static String basePath;
	static {
		basePath = PropertyUtils.getProperty("dbtools.basepath");
	}

	public static String get(String url) {
		if (url.startsWith("/")) {
			return String.format("%s%s", basePath, url);
		}
		return String.format("%s/%s", basePath, url);
	}
	
	public static String getContextPath(){
		return basePath;
	}
}
