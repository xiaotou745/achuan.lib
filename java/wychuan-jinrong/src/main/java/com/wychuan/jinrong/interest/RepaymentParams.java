package com.wychuan.jinrong.interest;

public class RepaymentParams {
	private double repaymentCorpus;

	private int repaymentMonthCounts;

	private RepaymentType repaymentType;
	
	private double repaymentRate;

	public double getRepaymentCorpus() {
		return repaymentCorpus;
	}

	public void setRepaymentCorpus(double repaymentCorpus) {
		this.repaymentCorpus = repaymentCorpus;
	}

	public int getRepaymentMonthCounts() {
		return repaymentMonthCounts;
	}

	public void setRepaymentMouthCounts(int repaymentMonthCounts) {
		this.repaymentMonthCounts = repaymentMonthCounts;
	}

	public RepaymentType getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(RepaymentType repaymentType) {
		this.repaymentType = repaymentType;
	}

	public double getRepaymentRate() {
		return repaymentRate;
	}

	public void setRepaymentRate(double repaymentRate) {
		this.repaymentRate = repaymentRate;
	}
}
