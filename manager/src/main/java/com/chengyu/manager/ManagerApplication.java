package com.chengyu.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.chengyu.manager.dao")
@EnableTransactionManagement  // 开启事务
public class ManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}
}
