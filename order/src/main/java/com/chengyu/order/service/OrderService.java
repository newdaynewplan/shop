package com.chengyu.order.service;


import com.chengyu.common.utils.E3Result;
import com.chengyu.order.pojo.OrderInfo;

public interface OrderService {

	E3Result createOrder(OrderInfo orderInfo);
}
