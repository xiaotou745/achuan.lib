package com.wychuan.jinrong.income;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 个税计算结果信息类
 * 
 * @author wangyuchuan
 *
 */
public class TaxCalcResult {
	static final Logger logger = LoggerFactory.getLogger(TaxCalcResult.class);

	/**
	 * 用户名
	 */
	private String userName = "客户";
	/**
	 * 基本薪资
	 */
	private double basicSalary;
	/**
	 * 奖金
	 */
	private double bonus;

	/**
	 * 交纳各种费用项
	 */
	private List<TaxItem> taxItems = new ArrayList<TaxItem>();

	/**
	 * 个人所得税
	 */
	private double personalTax;

	/**
	 * 获取应纳税所得额
	 * 
	 * @return
	 */
	public double getShouldTaxSalary() {
		double totalSalary = getBasicSalary() + getBonus();
		return totalSalary - getWXYJOfPersonal();
	}

	/**
	 * 
	 * @return
	 */
	public double getWXYJOfPersonal() {
		return getTaxItemSum(true, true);
	}

	public double getSheBaoOfPersonal() {
		return getTaxItemSum(false, true);
	}

	public double getWXYJOfCompany() {
		return getTaxItemSum(true, false);
	}

	public double getSheBaoOfCompany() {
		return getTaxItemSum(false, false);
	}

	private double getTaxItemSum(boolean includeGjj, boolean personal) {
		if (getTaxItems() == null || getTaxItems().size() == 0) {
			return 0;
		}
		double total = 0;
		for (TaxItem item : getTaxItems()) {
			// 求社保的和，不包括公积金
			if (TaxType.GongJiJin.equals(item.getType()) && !includeGjj) {
				continue;
			}
			if (personal) {
				total += item.getPayOfPersonal();
			} else {
				total += item.getPayOfCompany();
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		return Double.parseDouble(decimalFormat.format(total));
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double preTaxSalary) {
		this.basicSalary = preTaxSalary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getPersonalTax() {
		return personalTax;
	}

	public void setPersonalTax(double personalTax) {
		this.personalTax = personalTax;
	}

	public double getAfterTaxSalary() {
		double totalSalary = getBasicSalary() + getBonus();
		double afterSalary = totalSalary - getWXYJOfPersonal() - getPersonalTax();
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.parseDouble(df.format(afterSalary));
	}

	public List<TaxItem> getTaxItems() {
		return this.taxItems;
	}

	public void setTaxItems(List<TaxItem> taxItems) {
		this.taxItems = taxItems;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("基本薪资：%f", getBasicSalary()));
		sb.append(String.format("所得奖金：%f", getBonus()));
		sb.append(String.format("社保公积金扣款：%f", getWXYJOfPersonal()));
		sb.append(String.format("应纳税所得额：%f", getShouldTaxSalary()));
		sb.append(String.format("个人所得税扣款：%f", getPersonalTax()));
		sb.append(String.format("税后薪资:%f", getAfterTaxSalary()));

		return sb.toString();
	}

	public void print() {
		logger.info("基本薪资：{}", getBasicSalary());
		logger.info("个人奖金：{}", getBonus());
		logger.info("------------五险一金扣款--------");
		logger.info("				个人			单位");
		for (TaxItem item : getTaxItems()) {
			logger.info("{}[基数{}]:		{}({}%)		{}({}%)", item.getType().desc(), item.getPayBase(),
					item.getPayOfPersonal(), item.getPayRateOfPersonal() * 100, item.getPayOfCompany(),
					item.getPayRateOfCompany() * 100);
		}
		logger.info("				--汇总一下---");
		logger.info("社保：				{}			{}", getSheBaoOfPersonal(), getSheBaoOfCompany());
		logger.info("总计:				{}			{}", getWXYJOfPersonal(), getWXYJOfCompany());
		logger.info("------------五险一金扣款--------");
		logger.info("应纳税所得额：{}", getShouldTaxSalary());
		logger.info("交纳个税：{}", getPersonalTax());
		logger.info("税后收入：{}", getAfterTaxSalary());
	}
}
