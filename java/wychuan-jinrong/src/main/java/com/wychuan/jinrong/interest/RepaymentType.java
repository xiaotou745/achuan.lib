package com.wychuan.jinrong.interest;

/**
 * 还贷方式
 * 
 * @author wangyuchuan
 *
 */
public enum RepaymentType {
	EqualityCorpus(1, "等额本金"), EqualityCorpusAndInterest(2, "等额本息");
	private int value;
	private String desc;

	private RepaymentType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static RepaymentType valueOf(int value) {
		for (RepaymentType type : RepaymentType.values()) {
			if (type.value() == value) {
				return type;
			}
		}
		return null;
	}
}
