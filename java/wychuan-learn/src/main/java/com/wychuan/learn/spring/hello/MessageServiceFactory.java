package com.wychuan.learn.spring.hello;

public class MessageServiceFactory {
	/**
	 * spring可以使用静态工厂方法装载一个对象
	 * @return
	 */
	public static MessageService getStaticMessageService(){
		return new MessageServiceImpl();
	}
	
	/**
	 * spring也可以使用实例工厂方法装载一个对象
	 * @return
	 */
	public MessageService getInstanceMessageService(){
		return new MessageServiceImpl();
	}
}
