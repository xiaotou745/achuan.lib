package com.wychuan.learn.spring.hello;

public class MessageServiceImpl2 implements MessageService {

	public String getMessage() {
		// TODO Auto-generated method stub
		if (name == null)
			return "MessageServiceImpl2";
		return "hello " + name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
