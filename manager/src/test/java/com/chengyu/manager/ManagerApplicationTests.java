package com.chengyu.manager;

import com.chengyu.manager.dao.TbItemMapper;
import com.chengyu.manager.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerApplicationTests {

	@Resource
	private TbItemMapper itemMapper;
	@Resource
	private ItemService itemService;
	@Autowired
	private JmsMessagingTemplate jmsTemplate;
	@Autowired
	private Queue queue;

	@Autowired
	private Topic topic;
	@Test
	public void contextLoads() {
		System.out.println(itemMapper.selectByExample(null));
	}

	@Test
	public void redis(){
		System.out.println(itemService.getItemById(562379));
	}

	@Test
	public void queue(){
		jmsTemplate.convertAndSend(queue,"test111");
	}

	@Test
	public void topic(){
		jmsTemplate.convertAndSend(topic,"topic-msg");
	}
}
