package com.chengyu.manager;

import com.chengyu.manager.dao.TbItemMapper;
import com.chengyu.manager.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerApplicationTests {

	@Resource
	private TbItemMapper itemMapper;
	@Resource
	private ItemService itemService;

	@Test
	public void contextLoads() {
		System.out.println(itemMapper.selectByExample(null));
	}

	@Test
	public void redis(){
		System.out.println(itemService.getItemById(562379));
	}

}
