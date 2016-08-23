package com.wychuan.apache.common.beanutils;

public class Person {
	private int age;
	private String name;
	private String email;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static void main(String[] args) {
		Person person1 = new Person();
		person1.setAge(12);
		person1.setName("test");
		person1.setEmail("test@gmail.com");

	}
}

class Employee {
	private int age;
	private String name;
	private String email;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
