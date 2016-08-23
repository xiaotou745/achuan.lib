package com.wychuan.jinrong.interest;

import java.util.List;

public class RepaymentResult {
	private double totalInterest;

	private double totalCorpusAndInterest;

	private List<RepaymentItem> items;

	public double getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(double totalInterest) {
		this.totalInterest = totalInterest;
	}

	public double getTotalCorpusAndInterest() {
		return totalCorpusAndInterest;
	}

	public void setTotalCorpusAndInterest(double totalCorpusAndInterest) {
		this.totalCorpusAndInterest = totalCorpusAndInterest;
	}

	public List<RepaymentItem> getItems() {
		return items;
	}

	public void setItems(List<RepaymentItem> items) {
		this.items = items;
	}
}
