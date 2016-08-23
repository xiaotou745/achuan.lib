package com.wychuan.learn.interview;

class A {
	static {
		System.out.println("static a");
	}
	public A() {
		System.out.println("classA");
	}
	{
		System.out.println("I'm A class");
	}
	protected String value = "valuea";
	public String getValue() {
		return value;
	}
}
public class B extends A {
	static {
		System.out.println("static b");
	}
	public B() {
		System.out.println("classB");
	}
	{
		System.out.println("I'm B class");
	}
	protected String value = "valueb";
	public static void main(String[] args){
		System.out.println("====main start====");
		A temp = new B();
		temp = new B();
		System.out.println(temp.getValue());
		System.out.println("====main end====");
	}
}

