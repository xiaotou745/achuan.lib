package com.wychuan.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseHelper {
	public static String toDateString(Date date){
		return toDateString(date, null);
	}
	public static String toDateString(Date date, String format) {
		if (date == null) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		DateFormat formater = new SimpleDateFormat(format);
		return formater.format(date);
	}
}
