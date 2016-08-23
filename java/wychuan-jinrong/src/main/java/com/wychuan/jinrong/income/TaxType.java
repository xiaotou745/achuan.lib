package com.wychuan.jinrong.income;

/**
 * 收入项类型
 * @author wangyuchuan
 *
 */
public enum TaxType {
	GongJiJin(1, "公积金"), YangLao(2, "养老"), YiLiao(3, "医疗"), GongShang(4, "工伤"), ShiYe(5, "失业"), ShengYu(6, "生育");
	private int value = 0;
	private String desc;

	TaxType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static TaxType valueOf(int value) {
		for (TaxType taxType : TaxType.values()) {
			if (taxType.value() == value) {
				return taxType;
			}
		}
		return null;
	}
}
