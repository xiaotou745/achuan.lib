package com.wychuan.code.common;

import com.wychuan.util.StringUtils;

public class StringPlus {
	private StringBuilder str;

	public String getValue() {
		return str.toString();
	}

	public StringPlus() {
		str = new StringBuilder();
	}

	/**
	 * 增加tab缩进或间隔
	 * 
	 * @param spaceNo
	 *            几个tab缩进
	 * @return
	 */
	public String addSpace(int spaceNo) {
		StringBuilder strspace = new StringBuilder();
		for (int i = 0; i < spaceNo; i++) {
			strspace.append("\t");
		}
		return strspace.toString();
	}

	/**
	 * 追加文本
	 * 
	 * @param text
	 * @return
	 */
	public String append(String text) {
		str.append(text);
		return str.toString();
	}

	/**
	 * 追加回车换行
	 * 
	 * @return
	 */
	public String appendLine() {
		str.append("\r\n");
		return str.toString();
	}

	/**
	 * 追加一行文本，带回车换行
	 * 
	 * @param text
	 * @return
	 */
	public String appendLine(String text) {
		str.append(text + "\r\n");
		return str.toString();
	}

	/**
	 * 追加一行文本，前面加缩进
	 * 
	 * @param spaceNum
	 * @param text
	 * @return
	 */
	public String appendSpace(int spaceNum, String text) {
		str.append(addSpace(spaceNum));
		str.append(text);
		return str.toString();
	}

	/**
	 * 追加一行文本，前面加空格缩进，后面带回车换行
	 * 
	 * @param spaceNum
	 * @param text
	 * @return
	 */
	public String appendSpaceLine(int spaceNum, String text) {
		str.append(addSpace(spaceNum));
		str.append(text);
		str.append("\r\n");
		return str.toString();
	}

	/**
	 * 删除最后一个逗号
	 */
	public void deleteLastComma() {
		String strtemp = str.toString();
		int indexOfComma = strtemp.lastIndexOf(",");
		if (indexOfComma > 0) {
			str = new StringBuilder();
			str.append(strtemp.substring(0, indexOfComma));
		}
		System.out.println(str.toString());
	}
	
	/**
	 * 删除最后一个指定字符串
	 * @param strchar
	 */
	public void deleteLastChar(String strchar){
		if(StringUtils.isEmpty(strchar)){
			return;
		}
		String strtemp = str.toString();
		int n = strtemp.lastIndexOf(strchar);
		if(n>0){
			str = new StringBuilder();
			str.append(strtemp.substring(0,n));
		}
	}

	@Override
	public String toString() {
		return str.toString();
	}
}
