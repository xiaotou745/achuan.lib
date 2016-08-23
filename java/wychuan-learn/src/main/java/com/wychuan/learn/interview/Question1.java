package com.wychuan.learn.interview;

/**
 * 面试题001
 * @author wangyuchuan
 * @题目：
 * 你面前有10级台阶，每次你可以走一步或是走二步，请问：
 * 1. 一共有多少种走法？
 * 2. 能否把所有的走法打印出来？
 * 
 * 解题思路：
 * 1级台阶共有1种走法；
 * 2级台阶共有2种走法（【11】，【2】）
 * 3级台阶共有3种走法（【111】，【12】，【21】）
 * 4级台阶共有5种走法（【1111】，【112】，【121】，【211】，【22】）
 * 5级台阶共有8种走法（【11111】，【1112】，【1211】，【1121】，【122】，【2111】，【221】，【212】）
 * 。。。。
 * 斐波那契数列：1，2，3，5，8。。。。。。
 */
public class Question1 {
	/**
	 * 参考答案一
	 */
	public static void main(String[] args) {
		System.out.println("登上10级台阶共有" + getAnswer(10) + "种走法。");
	}

	public static int getAnswer(int n) {
		if (n <= 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		return getAnswer(n - 1) + getAnswer(n - 2);
	}
}
