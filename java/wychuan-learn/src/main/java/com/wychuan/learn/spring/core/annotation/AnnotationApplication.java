package com.wychuan.learn.spring.core.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationApplication {
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("app.xml");
		MessagePrinterAutowired printerAutowired = appContext.getBean(MessagePrinterAutowired.class);
		printerAutowired.printMessage();
	}
}
