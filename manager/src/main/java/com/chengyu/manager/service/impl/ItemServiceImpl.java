package com.chengyu.manager.service.impl;


import com.chengyu.common.pojo.EasyUIDataGridResult;
import com.chengyu.common.utils.E3Result;
import com.chengyu.common.utils.IDUtils;
import com.chengyu.common.utils.JsonUtils;
import com.chengyu.manager.dao.TbItemDescMapper;
import com.chengyu.manager.dao.TbItemMapper;
import com.chengyu.manager.pojo.TbItem;
import com.chengyu.manager.pojo.TbItemDesc;
import com.chengyu.manager.pojo.TbItemExample;
import com.chengyu.manager.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
//	@Autowired
//	private JmsTemplate jmsTemplate;
	@Autowired
	private StringRedisTemplate jedisClient;
	@Value("${REDIS_ITEM_PRE}")
	private String REDIS_ITEM_PRE;
	@Value("${ITEM_CACHE_EXPIRE}")
	private Integer ITEM_CACHE_EXPIRE;
	// 先根据id找，后根据类型找
//	@Resource
//	private Destination topicDestination;

	@Override
	public TbItem getItemById(long itemId) {
		ValueOperations<String, String> ops = this.jedisClient.opsForValue();
		//查询缓存
		try {
			String json = ops.get(REDIS_ITEM_PRE + ":" + itemId + ":BASE");
			if(StringUtils.isNotBlank(json)) {
				TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
				System.out.println("有");
				return tbItem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("没有");
		//缓存中没有，查询数据库
		//根据主键查询
		//TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdEqualTo(itemId);
		//执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			//把结果添加到缓存
			try {
				ops.set(REDIS_ITEM_PRE + ":" + itemId + ":BASE", JsonUtils.objectToJson(list.get(0)));
				//设置过期时间
				jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":BASE", ITEM_CACHE_EXPIRE, TimeUnit.SECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list.get(0);
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public E3Result addItem(TbItem item, String desc) {
		// 1、生成商品id
		final long itemId = IDUtils.genItemId();
		// 2、补全TbItem对象的属性
		item.setId(itemId);
		//商品状态，1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		// 3、向商品表插入数据
		itemMapper.insert(item);
		// 4、创建一个TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		// 5、补全TbItemDesc的属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 6、向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		// 7、E3Result.ok()
		//发送一个商品添加消息
//		jmsTemplate.send(topicDestination, new MessageCreator() {
//
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				TextMessage textMessage = session.createTextMessage(itemId + "");
//				return textMessage;
//			}
//		});

		return E3Result.ok();
	}

	@Override
	public E3Result deleteItem(String[] split) {
		for (String id : split) {
			long l = Long.parseLong(id);
			itemMapper.deleteByPrimaryKey(l);
			itemDescMapper.deleteByPrimaryKey(l);
		}
		return E3Result.ok();
	}

	@Override
	public TbItemDesc getItemDescById(long itemId) {
		ValueOperations<String, String> ops = this.jedisClient.opsForValue();
		//查询缓存
		try {
			String json = ops.get(REDIS_ITEM_PRE + ":" + itemId + ":DESC");
			if(StringUtils.isNotBlank(json)) {
				TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return tbItemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		//把结果添加到缓存
		try {
			ops.set(REDIS_ITEM_PRE + ":" + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
			//设置过期时间
			jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":DESC", ITEM_CACHE_EXPIRE, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}


}
