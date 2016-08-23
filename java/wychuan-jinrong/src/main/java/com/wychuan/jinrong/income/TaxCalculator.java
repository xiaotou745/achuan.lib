/**
 * 
 */
package com.wychuan.jinrong.income;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangyuchuan 个税计算器
 * @remark
 * 缴税=全月应纳税所得额*税率－速算扣除数
 * 应纳税所得额			税率	速算扣除数
 * salary<1455			3%	0
 * 1455<salary<4155 	10%	105
 * 4155<salary<7755		20%	555
 * 7755<salary<27255	25%	1055
 * 27255<salary<41255	30%	2775
 * 41255<salary<57505	35%	5505
 * salary>57505			45%	13505
 */
public class TaxCalculator {
	
	private static final Logger logger = LoggerFactory.getLogger(TaxCalculator.class);
	
	/**
	 * 扣除社保公积金后金额
	 */
	private double beforeTaxSalary;
	
	/**
	 * 获取应税薪资
	 * @return 应税薪资
	 * 全月应纳税所得额=(应发工资－社保公积金)－3500
	 */
	public double getBaseTaxSalary(){
		return beforeTaxSalary - 3500;
	}

	/**
	 * 计算个税
	 * @return
	 * 缴税=全月应纳税所得额*税率－速算扣除数
	 * 
	 * 应纳税所得额			税率	速算扣除数
	 * salary<=1500			3%	0
	 * 1500<salary<=4500 	10%	105
	 * 4500<salary<=9000	20%	555
	 * 9000<salary<=35000	25%	1005
	 * 35000<salary<=55000	30%	2755
	 * 55000<salary<=80000	35%	5505
	 * salary>80000			45%	13505
	 */
	public double calcTax(){
		logger.debug("---------计算个人所得税开始----");
		logger.debug("个税计算公式：个税=应纳税所得额*适合税率-速算扣除数");
		logger.debug("应纳税所得额：{}", getBeforeTaxSalary());
		//应税工资
		double baseTaxSalary = getBaseTaxSalary();
		double taxRate =0; //税率
		int quickDeduction = 0; //速算扣除数
		
		if(baseTaxSalary<=0){ //低于个税起征点，不用交税
			logger.info("应纳税所得额低于个税起片点，不用交税哦。");
			return 0;
		}else if(baseTaxSalary<=1500){
			taxRate = 0.03;
			quickDeduction = 0;
		}else if(baseTaxSalary<=4500){
			taxRate = 0.1;
			quickDeduction = 105;
		}else if(baseTaxSalary<=9000){
			taxRate = 0.2;
			quickDeduction=555;
		}else if(baseTaxSalary<=35000){
			taxRate = 0.25;
			quickDeduction=1005;
		}else if(baseTaxSalary<=55000){
			taxRate = 0.3;
			quickDeduction =2755;
		}else if(baseTaxSalary<=80000){
			taxRate = 0.35;
			quickDeduction = 5505;
		}else{
			taxRate = 0.45;
			quickDeduction = 13505;
		}
		logger.debug("适用税率：{}  速算扣除数：{}",taxRate,quickDeduction);
		double taxSalary = baseTaxSalary*taxRate-quickDeduction;
		logger.debug("个税计算过程：({}-3500)*{}-{}",getBeforeTaxSalary(),taxRate,quickDeduction);
		logger.debug("应交纳个税：{}",taxSalary);
		logger.debug("---------计算个人所得税开始完毕------");
		DecimalFormat deFormat = new DecimalFormat("#.00");
		return Double.parseDouble(deFormat.format(taxSalary));
	}

	public double getBeforeTaxSalary() {
		return beforeTaxSalary;
	}

	public void setBeforeTaxSalary(double beforeTaxSalary) {
		this.beforeTaxSalary = beforeTaxSalary;
	}
}
