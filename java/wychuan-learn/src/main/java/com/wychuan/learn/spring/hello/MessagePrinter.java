package com.wychuan.learn.spring.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {
	final private MessageService messageService;
	
	@Autowired
	public MessagePrinter(MessageService service){
		this.messageService = service;
	}
	
	public void printMessage(){
		System.out.println(this.messageService.getMessage());
	}
}
