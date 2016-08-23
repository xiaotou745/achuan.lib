package com.wychuan.learn.spring.core.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wychuan.learn.spring.hello.MessagePrinter;
import com.wychuan.learn.spring.hello.MessageService;

public class XmlApplication {
	public static void main(String[] args){
		ApplicationContext appContext = new FileSystemXmlApplicationContext("classpath:app.xml");
//		ApplicationContext appContext = new ClassPathXmlApplicationContext("app.xml");
		MessagePrinter printer = appContext.getBean("messagePrinter", MessagePrinter.class);
		printer.printMessage();
		
		//<import/>可以组合xml配置文件。此示例主要展示 hello.xml配置文件中的 <import/>用法。
		MessageService messageServiceImpl2 = appContext.getBean("messageService", MessageService.class);
		System.out.println(messageServiceImpl2.getMessage());
		
		//<alias/>可以指定别名。ms2是messageSerivce2的别名，详细信息可以参考配置文件 hello.xml
		MessageService ms2 = appContext.getBean("ms2", MessageService.class);
		System.out.println(ms2.getMessage());
	}
}
