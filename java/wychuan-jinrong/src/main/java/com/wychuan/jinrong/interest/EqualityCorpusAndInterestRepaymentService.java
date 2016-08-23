package com.wychuan.jinrong.interest;

import java.util.ArrayList;
import java.util.List;

public class EqualityCorpusAndInterestRepaymentService implements RepaymentService {

	@Override
	public RepaymentResult calculate(RepaymentParams params) {
		RepaymentResult result = new RepaymentResult();

		// 月利率
		double ratePerMonth = params.getRepaymentRate() / 12;
		// (1+月利率)^贷款月数
		double temp = Math.pow((1 + ratePerMonth), params.getRepaymentMonthCounts());
		// 还款总额=还款月数*贷款额*月利率*（1+月利率）^贷款月数/【（1+月利率）^还款月数 - 1】
		double totalCorpusAndInterest = (params.getRepaymentMonthCounts() * params.getRepaymentCorpus() * ratePerMonth
				* temp) / (temp - 1);
		result.setTotalCorpusAndInterest(totalCorpusAndInterest);
		// 还款总息=还款总额-还款本金
		result.setTotalInterest(totalCorpusAndInterest - params.getRepaymentCorpus());
		// 已还本金
		double hasRepayCorpus = 0;

		List<RepaymentItem> items = new ArrayList<RepaymentItem>();
		for (int i = 1; i <= params.getRepaymentMonthCounts(); i++) {
			RepaymentItem item = new RepaymentItem();
			item.setNo(i);

			// 偿还本息：[ 本金 x 月利率 x(1+月利率)^贷款月数 ] / [(1+月利率)还款月数 - 1]
			double corpusAndInterest = (params.getRepaymentCorpus() * ratePerMonth * temp) / (temp - 1);
			item.setCorpusAndInterest(corpusAndInterest);
			// 偿还利息：剩余贷款金额 x 月利率
			double interest = (params.getRepaymentCorpus() - hasRepayCorpus) * ratePerMonth;
			item.setInterest(interest);

			// 偿还本金：偿还本息-偿还利息
			item.setCorpus(corpusAndInterest - interest);

			// 已还本金+
			hasRepayCorpus += item.getCorpus();
			// 剩余本金=总本金-已还本金
			item.setRemainCorpus(params.getRepaymentCorpus() - hasRepayCorpus);

			items.add(item);
		}

		result.setItems(items);

		return result;
	}

}
