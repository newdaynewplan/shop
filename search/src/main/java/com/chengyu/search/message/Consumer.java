package com.chengyu.search.message;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination = "itemAddTopic",containerFactory = "jmsListenerContainerTopic")
	public void receiveTopic(String text) throws JmsException {
		System.out.println("商品id： "+text);

	}

}
