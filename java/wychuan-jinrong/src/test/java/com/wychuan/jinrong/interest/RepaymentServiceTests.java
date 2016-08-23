package com.wychuan.jinrong.interest;

import java.text.DecimalFormat;

import org.junit.Test;

public class RepaymentServiceTests {
	@Test
	public void equalityCorpusRepaymentServiceTest() {
		RepaymentService repaymentService = new EqualityCorpusRepaymentService();

		RepaymentParams params = new RepaymentParams();
		params.setRepaymentCorpus(10000);
		params.setRepaymentMouthCounts(10);
		params.setRepaymentRate(0.12);

		RepaymentResult result = repaymentService.calculate(params);
		System.out.println("共偿还利息：" + result.getTotalInterest());
		System.out.println("共偿还本息：" + result.getTotalCorpusAndInterest());
		System.out.println(String.format("%s	%s	%s	%s	%s", "期次", "偿还本金", "偿还利息", "偿还本息", "剩余本金"));
		for (RepaymentItem item : result.getItems()) {
			System.out.println(String.format("%d	%s	%s	%s	%s", item.getNo(), item.getCorpus(), item.getInterest(),
					item.getCorpusAndInterest(), item.getRemainCorpus()));
		}
		System.out.println(String.format("%s	%s	%s	%s	%s", "合计", params.getRepaymentCorpus(),
				result.getTotalInterest(), result.getTotalCorpusAndInterest(), ""));
	}

	@Test
	public void equalityCorpusAndInterestRepaymentServiceTest() {
		RepaymentService repaymentService = new EqualityCorpusAndInterestRepaymentService();

		RepaymentParams params = new RepaymentParams();
		params.setRepaymentCorpus(11000);
		params.setRepaymentMouthCounts(12);
		params.setRepaymentRate(0.1095);
		DecimalFormat df = new DecimalFormat("#.00");
		RepaymentResult result = repaymentService.calculate(params);
		System.out.println("共偿还利息：" + df.format(result.getTotalInterest()));
		System.out.println("共偿还本息：" + df.format(result.getTotalCorpusAndInterest()));
		System.out.println(String.format("%s	%s	%s	%s	%s", "期次", "偿还本金", "偿还利息", "偿还本息", "剩余本金"));
		for (RepaymentItem item : result.getItems()) {
			System.out.println(String.format("%d	%s	%s	%s	%s", item.getNo(), df.format(item.getCorpus()),
					df.format(item.getInterest()), df.format(item.getCorpusAndInterest()),
					df.format(item.getRemainCorpus())));
		}
		System.out.println(String.format("%s	%s	%s	%s	%s", "合计", params.getRepaymentCorpus(),
				df.format(result.getTotalInterest()), df.format(result.getTotalCorpusAndInterest()), ""));
	}
}
