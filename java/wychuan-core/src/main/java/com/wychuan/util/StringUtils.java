package com.wychuan.util;

public class StringUtils {
	/**
	 * 检测指定的字符串是否为空（{@code null} or "")
	 */
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}
}
