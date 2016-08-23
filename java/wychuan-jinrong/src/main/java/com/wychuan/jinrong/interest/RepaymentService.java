package com.wychuan.jinrong.interest;

/**
 * 贷款工具类
 * 
 * @author wangyuchuan
 *
 */
public interface RepaymentService {
	RepaymentResult calculate(RepaymentParams params);
}
