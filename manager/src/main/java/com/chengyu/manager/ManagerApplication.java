package com.chengyu.manager;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.command.ActiveMQTopic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.jms.Queue;
import javax.jms.Topic;


@SpringBootApplication
@MapperScan("com.chengyu.manager.dao")
@EnableTransactionManagement  // 开启事务
public class ManagerApplication {

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("sample.queue");
	}

	@Bean
	public Topic topicDestination(){
		return new ActiveMQTopic("itemAddTopic");
	}
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}
}
