package com.chengyu.order.service.impl;

import com.chengyu.common.utils.E3Result;
import com.chengyu.order.dao.TbOrderItemMapper;
import com.chengyu.order.dao.TbOrderMapper;
import com.chengyu.order.dao.TbOrderShippingMapper;
import com.chengyu.order.feign.CartFeign;
import com.chengyu.order.pojo.OrderInfo;
import com.chengyu.order.pojo.TbOrderItem;
import com.chengyu.order.pojo.TbOrderShipping;
import com.chengyu.order.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 订单处理服务
 * <p>Title: OrderServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CartFeign cartService;
	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	@Autowired
	private StringRedisTemplate jedisClient;
	
	@Value("${ORDER_ID_GEN_KEY}")
	private String ORDER_ID_GEN_KEY;
	@Value("${ORDER_ID_START}")
	private String ORDER_ID_START;
	@Value("${ORDER_DETAIL_ID_GEN_KEY}")
	private String ORDER_DETAIL_ID_GEN_KEY;
	
	@Override
	public E3Result createOrder(OrderInfo orderInfo) {
		//生成订单号。使用redis的incr生成。
		ValueOperations<String, String> ops = jedisClient.opsForValue();
		if (StringUtils.isBlank(ops.get(ORDER_ID_GEN_KEY))) {
			ops.set(ORDER_ID_GEN_KEY, ORDER_ID_START);
		}
		String orderId = ops.increment(ORDER_ID_GEN_KEY,1L).toString();
		//补全orderInfo的属性
		orderInfo.setOrderId(orderId);
		//1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		orderInfo.setStatus(1);
		orderInfo.setCreateTime(new Date());
		orderInfo.setUpdateTime(new Date());
		//插入订单表
		orderMapper.insert(orderInfo);
		//向订单明细表插入数据。
		List<TbOrderItem> orderItems = orderInfo.getOrderItems();
		for (TbOrderItem tbOrderItem : orderItems) {
			//生成明细id
			String odId = ops.increment(ORDER_DETAIL_ID_GEN_KEY, 1L).toString();
			//补全pojo的属性
			tbOrderItem.setId(odId);
			tbOrderItem.setOrderId(orderId);
			//向明细表插入数据
			orderItemMapper.insert(tbOrderItem);
		}
		//向订单物流表插入数据
		TbOrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		orderShippingMapper.insert(orderShipping);
		//返回E3Result，包含订单号
		//如果订单生成成功，需要删除购物车
		//清空购物车
		cartService.clearCartItem(orderInfo.getUserId());
		return E3Result.ok(orderId);
	}

}
