package com.wychuan.dbtools.htmltables;

import java.util.ArrayList;
import java.util.List;

public class HtmlTableTrItem {
	private List<HtmlTableTdItem> tds = new ArrayList<HtmlTableTdItem>();

	/**
	 * 创建一个td项
	 * 
	 * @return
	 */
	public HtmlTableTdItem newTd() {
		HtmlTableTdTextItem td = new HtmlTableTdTextItem();
		tds.add(td);
		return td;
	}

	/**
	 * 创建一个td项，包含td项的name属性和text文本
	 * 
	 * @param name
	 * @param text
	 * @return
	 */
	public HtmlTableTdItem newTd(String name, String text) {
		return newTd(name, text, null);
	}

	/**
	 * 创建一个td文本项
	 * @param name
	 * @param text
	 * @param className
	 * @return
	 */
	public HtmlTableTdItem newTd(String name, String text, String className) {
		return addTd(name, text, className, null, null);
	}
	
	public HtmlTableTdItem addTd(String name, String text, String className){
		return newTd(name, text, className);
	}
	
	public HtmlTableTdItem addTd(String name, String text, String className, String colspan, String rowspan){
		HtmlTableTdTextItem td = new HtmlTableTdTextItem();
		td.setName(name);
		td.setText(text);
		td.setclass(className);
		td.setColspan(colspan);
		td.setRowspan(rowspan);
		tds.add(td);
		return td;
	}
	
	public void addTd(HtmlTableTdItem td){
		tds.add(td);
	}

	/**
	 * 创建td项，td 内容为<a>链接项
	 * @param name
	 * @param href
	 * @param text
	 * @return
	 */
	public HtmlTableTdItem newLinkTd(String name, String href, String text) {
		return newLinkTd(name, href, text, null);
	}

	/**
	 * 创建一个td项，td内容为<a>链接项
	 * @param name
	 * @param href
	 * @param text
	 * @param className
	 * @return
	 */
	public HtmlTableTdItem newLinkTd(String name, String href, String text, String className) {
		HtmlTableTdLinkItem td = new HtmlTableTdLinkItem();
		td.setName(name);
		td.setclass(className);
		td.addLinkItem(text, href);
		tds.add(td);
		return td;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		for(HtmlTableTdItem td:tds){
			sb.append(td.toString());
		}
		sb.append("</tr>");
		return sb.toString();
	}
}
