package com.wychuan.learn.spring.core.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wychuan.learn.spring.hello.MessageService;

@Component
public class MessagePrinterAutowired {
	private MessageService messageService;
	
	@Autowired
	public MessagePrinterAutowired(MessageService messageSerice){
		this.messageService = messageSerice;
	}

	public MessageService getMessageService() {
		return messageService;
	}
	
//	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public void printMessage(){
		System.out.println(messageService.getMessage());
	}
}
