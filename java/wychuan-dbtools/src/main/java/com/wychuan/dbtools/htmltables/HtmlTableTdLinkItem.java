package com.wychuan.dbtools.htmltables;

import java.util.ArrayList;
import java.util.List;

public class HtmlTableTdLinkItem extends HtmlTableTdItem {
	private List<LinkItem> linkItems = new ArrayList<LinkItem>();

	@Override
	public String getTextHtml() {
		StringBuffer sb = new StringBuffer();

		if (getLinkItems() != null && getLinkItems().size() > 0) {
			for (LinkItem item : getLinkItems()) {
				sb.append(item.toString());
				sb.append("&nbsp;&nbsp;");
			}
		}

		return sb.toString();
	}

	/**
	 * @return the linkItems
	 */
	public List<LinkItem> getLinkItems() {
		return linkItems;
	}

	/**
	 * @param linkItems
	 *            the linkItems to set
	 */
	public void setLinkItems(List<LinkItem> linkItems) {
		this.linkItems = linkItems;
	}
	
	public LinkItem addLinkItem(String text, String href){
		return addLinkItem(text, href, null);
	}
	
	public LinkItem addLinkItem(String text, String href, String properties){
		LinkItem linkItem = new LinkItem();
		linkItem.setText(text);
		linkItem.setHref(href);
		linkItem.setProperties(properties);
		linkItems.add(linkItem);
		return linkItem;
	}
}
