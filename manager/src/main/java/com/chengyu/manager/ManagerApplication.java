package com.chengyu.manager;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.jms.Queue;
import javax.jms.Topic;


@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
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
