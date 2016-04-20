package com.wychuan.code;

/**
 * 列信息类
 * 
 * @author wychuan
 *
 */
public class ColumnInfo {
	private String order;
	private String name;
	private String type;
	private String length;
	private String preci;
	private String scale;
	private boolean isIdentity;
	private boolean isPK;
	private boolean isNull;
	private String defaultValue;
	private String description;
	/**
	 * mysql用得着，其他数据库就算了，mysql更改列的comment太弱了。哎。
	 */
	private String colType;

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the length
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * @return the preci
	 */
	public String getPreci() {
		return preci;
	}

	/**
	 * @param preci
	 *            the preci to set
	 */
	public void setPreci(String preci) {
		this.preci = preci;
	}

	/**
	 * @return the scale
	 */
	public String getScale() {
		return scale;
	}

	/**
	 * @param scale
	 *            the scale to set
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}

	/**
	 * @return the isIdentity
	 */
	public boolean isIdentity() {
		return isIdentity;
	}

	/**
	 * @param isIdentity
	 *            the isIdentity to set
	 */
	public void setIdentity(boolean isIdentity) {
		this.isIdentity = isIdentity;
	}

	/**
	 * @return the isPK
	 */
	public boolean isPK() {
		return isPK;
	}

	/**
	 * @param isPK
	 *            the isPK to set
	 */
	public void setPK(boolean isPK) {
		this.isPK = isPK;
	}

	/**
	 * @return the isNull
	 */
	public boolean isNull() {
		return isNull;
	}

	/**
	 * @param isNull
	 *            the isNull to set
	 */
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the remark
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setDescription(String remark) {
		this.description = remark;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("[colOrder]:%s\n[isPk]:%s\n", getOrder(), isPK()));
		sb.append(String.format("[colName]:%s\n[isNull]:%s\n", getName(), isNull()));
		sb.append(String.format("[colType]:%s\n[defaultValue]:%s\n[desc]:%s\n", getType(), getDefaultValue(),
				getDescription()));
		sb.append(String.format("[colLength]:%s\n", getLength()));
		sb.append(String
				.format("[colPreci]:%s\n[colScale]:%s\n[isIdentity]:%s\n", getPreci(), getScale(), isIdentity()));
		return sb.toString();
	}

	/**
	 * @return the colType
	 */
	public String getColType() {
		return colType;
	}

	/**
	 * @param colType the colType to set
	 */
	public void setColType(String colType) {
		this.colType = colType;
	}
}
