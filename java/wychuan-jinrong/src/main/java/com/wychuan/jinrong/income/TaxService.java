package com.wychuan.jinrong.income;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 个税计算器
 * 
 * @author wangyuchuan
 *
 */
public class TaxService {

	static final Logger Logger = LoggerFactory.getLogger(TaxService.class);

	double maxBaseOfBeiJing = 21258;

	/**
	 * 公积金基数
	 */
	private double baseOfGongJiJin = maxBaseOfBeiJing;
	/**
	 * 单位公积金比例
	 */
	private double companyRateOfGongJiJin = 0.12;

	/**
	 * 个人公积金比例
	 */
	private double personalRateOfGongJiJin = 0.12;

	/**
	 * 养老保险基数
	 */
	private double baseOfYanglao = maxBaseOfBeiJing;

	/**
	 * 公司养老保险比例
	 */
	private double companyRateOfYanglao = 0.19;

	/**
	 * 个人养老保险比例
	 */
	private double personalRateOfYanglao = 0.08;

	/**
	 * 失业保险基数
	 */
	private double baseOfShiye = maxBaseOfBeiJing;

	/**
	 * 公司失业保险比例
	 */
	private double companyRateOfShiye = 0.008;

	/**
	 * 个人失业保险比例
	 */
	private double personalRateOfShiye = 0.002;

	/**
	 * 工伤保险基数
	 */
	private double baseOfGongshang = maxBaseOfBeiJing;

	/**
	 * 公司工作保险比例
	 */
	private double companyRateOfGongshang = 0.004;

	/**
	 * 个人工伤保险比例
	 */
	private double personalRateOfGongshang = 0;

	/**
	 * 医疗保险基数
	 */
	private double baseOfYiliao = maxBaseOfBeiJing;

	/**
	 * 公司医疗保险比例
	 */
	private double companyRateOfYiliao = 0.1;

	/**
	 * 个人医疗保险比例
	 */
	private double personalRateOfYiliao = 0.02;

	/**
	 * 生育保险基数
	 */
	private double baseOfShengyu = maxBaseOfBeiJing;

	/**
	 * 公司生育保险比例
	 */
	private double companyRateOfShengyu = 0.008;

	/**
	 * 个人生育保险比例
	 */
	private double personalRateOfShengyu = 0;

	/**
	 * 基本薪资
	 */
	private double basicSalary;
	/**
	 * 奖金
	 */
	private double bonus;

	private TaxItem getTaxItem(TaxType taxType) {
		TaxItem result = new TaxItem();
		result.setType(taxType);
		switch (taxType) {
		case GongJiJin:
			double baseGjj = getSalary() <= getBaseOfGongJiJin() ? getSalary() : getBaseOfGongJiJin();// 公积金基数
			result.setPayBase(baseGjj);
			result.setPayRateOfCompany(getCompanyRateOfGongJiJin());
			result.setPayRateOfPersonal(getPersonalRateOfGongJiJin());
			break;
		case GongShang:
			double baseGongShang = getSalary() <= getBaseOfGongshang() ? getSalary() : getBaseOfGongshang();// 工伤基数
			result.setPayBase(baseGongShang);
			result.setPayRateOfCompany(getCompanyRateOfGongshang());
			result.setPayRateOfPersonal(getPersonalRateOfGongshang());
			break;
		case ShengYu:
			double baseShengYu = getSalary() <= getBaseOfShengyu() ? getSalary() : getBaseOfShengyu();// 生育基数
			result.setPayBase(baseShengYu);
			result.setPayRateOfCompany(getCompanyRateOfShengyu());
			result.setPayRateOfPersonal(getPersonalRateOfShengyu());
			break;
		case ShiYe:
			double baseShiYe = getSalary() <= getBaseOfShiye() ? getSalary() : getBaseOfShiye();// 失业基数
			result.setPayBase(baseShiYe);
			result.setPayRateOfCompany(getCompanyRateOfShiye());
			result.setPayRateOfPersonal(getPersonalRateOfShiye());
			break;
		case YangLao:
			double baseYangLao = getSalary() <= getBaseOfYanglao() ? getSalary() : getBaseOfYanglao();// 养老基数
			result.setPayBase(baseYangLao);
			result.setPayRateOfCompany(getCompanyRateOfYanglao());
			result.setPayRateOfPersonal(getPersonalRateOfYanglao());
			break;
		case YiLiao:
			double baseYiLiao = getSalary() <= getBaseOfYiliao() ? getSalary() : getBaseOfYiliao(); // 医疗基数
			result.setPayBase(baseYiLiao);
			result.setPayRateOfCompany(getCompanyRateOfYiliao());
			result.setPayRateOfPersonal(getPersonalRateOfYiliao());
			break;
		default:

			break;
		}
		return result;
	}

	/**
	 * 获取应纳税金额
	 * 
	 * @return 应纳税金额 = 薪资 - 五险一金
	 */
	public TaxCalcResult getBeforeTaxSalary() {
		TaxCalcResult result = new TaxCalcResult();
		Logger.debug("------计算五险一金---------");
		Logger.debug("基本薪资：{}",getBasicSalary());
		Logger.debug("个人奖金：{}", getBonus());
		
		result.setBasicSalary(getBasicSalary());
		result.setBonus(getBonus());

		for (TaxType type : TaxType.values()) {
			TaxItem taxItem = getTaxItem(type);
			result.getTaxItems().add(taxItem);
			taxItem.toString();
		}
		
		double shouldTaxSalary = result.getShouldTaxSalary(); //应纳税所得额
		
		TaxCalculator calu = new TaxCalculator();
		calu.setBeforeTaxSalary(shouldTaxSalary);
		double tax = calu.calcTax();//计算个人所得税
		
		result.setPersonalTax(tax);

		return result;
	}

	public double getBaseOfGongJiJin() {
		return baseOfGongJiJin;
	}

	public void setBaseOfGongJiJin(double baseOfGongJiJin) {
		this.baseOfGongJiJin = baseOfGongJiJin;
	}

	public double getCompanyRateOfGongJiJin() {
		return companyRateOfGongJiJin;
	}

	public void setCompanyRateOfGongJiJin(double companyRateOfGongJiJin) {
		this.companyRateOfGongJiJin = companyRateOfGongJiJin;
	}

	public double getPersonalRateOfGongJiJin() {
		return personalRateOfGongJiJin;
	}

	public void setPersonalRateOfGongJiJin(double personalRateOfGongJiJin) {
		this.personalRateOfGongJiJin = personalRateOfGongJiJin;
	}

	public double getCompanyRateOfYanglao() {
		return companyRateOfYanglao;
	}

	public void setCompanyRateOfYanglao(double companyRateOfYanglao) {
		this.companyRateOfYanglao = companyRateOfYanglao;
	}

	public double getPersonalRateOfYanglao() {
		return personalRateOfYanglao;
	}

	public void setPersonalRateOfYanglao(double personalRateOfYanglao) {
		this.personalRateOfYanglao = personalRateOfYanglao;
	}

	public double getCompanyRateOfShiye() {
		return companyRateOfShiye;
	}

	public void setCompanyRateOfShiye(double companyRateOfShiye) {
		this.companyRateOfShiye = companyRateOfShiye;
	}

	public double getPersonalRateOfShiye() {
		return personalRateOfShiye;
	}

	public void setPersonalRateOfShiye(double personalRateOfShiye) {
		this.personalRateOfShiye = personalRateOfShiye;
	}

	public double getCompanyRateOfGongshang() {
		return companyRateOfGongshang;
	}

	public void setCompanyRateOfGongshang(double companyRateOfGongshang) {
		this.companyRateOfGongshang = companyRateOfGongshang;
	}

	public double getPersonalRateOfGongshang() {
		return personalRateOfGongshang;
	}

	public void setPersonalRateOfGongshang(double personalRateOfGongshang) {
		this.personalRateOfGongshang = personalRateOfGongshang;
	}

	public double getCompanyRateOfYiliao() {
		return companyRateOfYiliao;
	}

	public void setCompanyRateOfYiliao(double companyRateOfYiliao) {
		this.companyRateOfYiliao = companyRateOfYiliao;
	}

	public double getPersonalRateOfYiliao() {
		return personalRateOfYiliao;
	}

	public void setPersonalRateOfYiliao(double personalRateOfYiliao) {
		this.personalRateOfYiliao = personalRateOfYiliao;
	}

	public double getCompanyRateOfShengyu() {
		return companyRateOfShengyu;
	}

	public void setCompanyRateOfShengyu(double companyRateOfShengyu) {
		this.companyRateOfShengyu = companyRateOfShengyu;
	}

	public double getPersonalRateOfShengyu() {
		return personalRateOfShengyu;
	}

	public void setPersonalRateOfShengyu(double personalRateOfShengyu) {
		this.personalRateOfShengyu = personalRateOfShengyu;
	}

	public double getBaseOfYanglao() {
		return baseOfYanglao;
	}

	public void setBaseOfYanglao(double baseOfYanglao) {
		this.baseOfYanglao = baseOfYanglao;
	}

	public double getBaseOfShiye() {
		return baseOfShiye;
	}

	public void setBaseOfShiye(double baseOfShiye) {
		this.baseOfShiye = baseOfShiye;
	}

	public double getBaseOfGongshang() {
		return baseOfGongshang;
	}

	public void setBaseOfGongshang(double baseOfGongshang) {
		this.baseOfGongshang = baseOfGongshang;
	}

	public double getBaseOfYiliao() {
		return baseOfYiliao;
	}

	public void setBaseOfYiliao(double baseOfYiliao) {
		this.baseOfYiliao = baseOfYiliao;
	}

	public double getBaseOfShengyu() {
		return baseOfShengyu;
	}

	public void setBaseOfShengyu(double baseOfShengyu) {
		this.baseOfShengyu = baseOfShengyu;
	}

	public double getSalary() {
		return getBasicSalary() + getBonus();
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
}
