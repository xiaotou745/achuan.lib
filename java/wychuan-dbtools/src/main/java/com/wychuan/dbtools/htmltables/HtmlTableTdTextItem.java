package com.wychuan.dbtools.htmltables;

import com.wychuan.util.StringUtils;

/**
 * 普通text td标签
 * 
 * @author wangyuchuan
 *
 */
public class HtmlTableTdTextItem extends HtmlTableTdItem {

	/**
	 * text文本
	 * <p>
	 * <td>${text}<//td>
	 */
	private String text;

	/**
	 * 是否使用<code></code>标签显示内容
	 * <p>
	 * if true then
	 * <p>
	 * <td><code>${text}</code></td>
	 * <p>
	 * else then
	 * <p>
	 * <td>${text}</td>
	 */
	private String useCode;

	/**
	 * 源数据是否通过DES加密,如果为<code>true</code>,则需要解密后显示
	 * <p>
	 * if true then
	 * <p>
	 * <td>DES.Decrypt3DES(${Text})</td>
	 * <p>
	 * else then
	 * <p>
	 * <td>${Text}</td>
	 */
	private String useDes;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUseCode() {
		return useCode;
	}

	public void setUseCode(String useCode) {
		this.useCode = useCode;
	}

	public String getUseDes() {
		return useDes;
	}

	public void setUseDes(String useDes) {
		this.useDes = useDes;
	}

	@Override
	public String getTextHtml() {
		String tmpText = getText();
		if(!StringUtils.isEmpty(getUseDes()) && "true".equals(getUseDes().toLowerCase())){
			///TODO 使用des加密字符串
		}
		if(!StringUtils.isEmpty(getUseCode()) && "true".equals(getUseCode().toLowerCase())){
			tmpText = String.format("<code>%s</code>", getText());
		}
		return tmpText;
	}
}
