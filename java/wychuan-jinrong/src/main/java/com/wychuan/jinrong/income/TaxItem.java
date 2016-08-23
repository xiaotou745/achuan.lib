package com.wychuan.jinrong.income;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 个人薪资交费项
 * @author wangyuchuan
 *
 */
public class TaxItem {
	static final Logger Logger = LoggerFactory.getLogger(TaxItem.class);
	/**
	 * 项目
	 */
	private TaxType type;
	/**
	 * 基数
	 */
	private double payBase;

	/**
	 * 公司支出
	 */
	private double payOfCompany;

	/**
	 * 公司上交比例
	 */
	private double payRateOfCompany;

	/**
	 * 个人比例
	 */
	private double payRateOfPersonal;

	/**
	 * 个人支出
	 */
	private double payOfPersonal;

	public double getPayBase() {
		return payBase;
	}

	public void setPayBase(double payBase) {
		this.payBase = payBase;
	}

	public double getPayOfCompany() {
		if (this.getType() == null) {
			return payOfCompany;
		}
		double payResult = getPayBase() * getPayRateOfCompany();
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		if (this.getType().equals(TaxType.GongJiJin)) {
			decimalFormat = new DecimalFormat("#");
		}
		return Double.parseDouble(decimalFormat.format(payResult));
	}

	public void setPayOfCompany(double payOfCompany) {
		this.payOfCompany = payOfCompany;
	}

	public double getPayOfPersonal() {
		if (this.getType() == null) {
			return payOfPersonal;
		}
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		if (this.getType().equals(TaxType.GongJiJin)) {
			decimalFormat = new DecimalFormat("#");
		}

		double payResult = getPayBase() * getPayRateOfPersonal();
		if (TaxType.YiLiao.equals(this.getType())) {
			payResult += 3;
		}

		return Double.parseDouble(decimalFormat.format(payResult));
	}

	public void setPayOfPersonal(double payOfPersonal) {
		this.payOfPersonal = payOfPersonal;
	}

	public double getPayRateOfCompany() {
		return payRateOfCompany;
	}

	public void setPayRateOfCompany(double payRateOfCompany) {
		this.payRateOfCompany = payRateOfCompany;
	}

	public double getPayRateOfPersonal() {
		return payRateOfPersonal;
	}

	public void setPayRateOfPersonal(double payRateOfPersonal) {
		this.payRateOfPersonal = payRateOfPersonal;
	}

	public TaxType getType() {
		return type;
	}

	public void setType(TaxType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		Logger.debug("---------计算{}--------", getType().desc());
		Logger.debug("交费基数：{}", getPayBase());
		Logger.debug("个人比例：{}	", getPayRateOfPersonal());
		Logger.debug("个人缴费：{}", getPayOfPersonal());
		return super.toString();
	}
}
