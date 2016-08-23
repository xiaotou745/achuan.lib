package com.wychuan.jinrong.interest;

/**
 * 每月还款结果
 * @author wangyuchuan
 *
 */
public class RepaymentItem {
	/**
	 * 期次
	 */
	private int no;
	
	/**
	 * 偿还利息
	 */
	private double interest;
	
	/**
	 * 偿还本金
	 */
	private double corpus;
	
	/**
	 * 剩余本金
	 */
	private double remainCorpus;
	
	/**
	 * 偿还本息
	 */
	private double corpusAndInterest;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getCorpus() {
		return corpus;
	}

	public void setCorpus(double corpus) {
		this.corpus = corpus;
	}

	public double getRemainCorpus() {
		return remainCorpus;
	}

	public void setRemainCorpus(double remainCorpus) {
		this.remainCorpus = remainCorpus;
	}

	public double getCorpusAndInterest() {
		return this.corpusAndInterest;
	}

	public void setCorpusAndInterest(double corpusAndInterest) {
		this.corpusAndInterest = corpusAndInterest;
	}
}
