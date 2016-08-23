package com.wychuan.jinrong.interest;

import java.util.ArrayList;
import java.util.List;

public class EqualityCorpusRepaymentService implements RepaymentService {

	@Override
	public RepaymentResult calculate(RepaymentParams params) {

		RepaymentResult result = new RepaymentResult();
		// 月利率
		double ratePerMonth = params.getRepaymentRate() / 12;
		// 还款总利息=（还款月数+1）*贷款额*月利率/2
		double totalInterest = (params.getRepaymentMonthCounts() + 1) * params.getRepaymentCorpus() * ratePerMonth / 2;
		result.setTotalInterest(totalInterest);
		// 还款总额=还款本金+总利息
		result.setTotalCorpusAndInterest(params.getRepaymentCorpus() + totalInterest);
		// 每期偿还本金
		double corpus = params.getRepaymentCorpus() / params.getRepaymentMonthCounts();

		List<RepaymentItem> items = new ArrayList<RepaymentItem>();
		for (int i = 1; i <= params.getRepaymentMonthCounts(); i++) {
			RepaymentItem item = new RepaymentItem();
			item.setNo(i);
			item.setCorpus(corpus);
			// 待还本金
			double interestReminCorpus = params.getRepaymentCorpus() - corpus * (i - 1);
			// 偿还利息=待还本金*月利率
			item.setInterest(interestReminCorpus * ratePerMonth);
			// 偿还本息=偿还本金+偿还利息
			item.setCorpusAndInterest(item.getCorpus() + item.getInterest());
			// 剩余偿还本金=还款本金-已还本金
			item.setRemainCorpus(params.getRepaymentCorpus() - i * corpus);
			items.add(item);
		}

		result.setItems(items);

		return result;
	}

}
