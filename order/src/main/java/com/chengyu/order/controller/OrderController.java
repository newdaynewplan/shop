package com.chengyu.order.controller;

import com.chengyu.common.utils.E3Result;
import com.chengyu.order.feign.CartFeign;
import com.chengyu.order.pojo.OrderInfo;
import com.chengyu.order.pojo.TbItem;
import com.chengyu.order.pojo.TbOrderItem;
import com.chengyu.order.pojo.TbUser;
import com.chengyu.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单管理Controller
 * <p>Title: OrderController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p>
 * @version 1.0
 */
@Controller
public class OrderController {

	@Autowired
	private CartFeign cartService;
	@Autowired
	private OrderService orderService;

	@RequestMapping("/order/order-cart.html")
	public String showOrderCart(HttpServletRequest request) {
		//取用户id
//		TbUser user= (TbUser) request.getAttribute("user");
		//根据用户id取收货地址列表
		//使用静态数据。。。
		//取支付方式列表
		//静态数据
		//根据用户id取购物车列表
//		List<TbItem> cartList = cartService.getCartList(user.getId());
		//把购物车列表传递给jsp
//		request.setAttribute("cartList", cartList);
		//返回页面
		return "order-cart";
	}

	@RequestMapping(value="/order/create.html", method= RequestMethod.POST)
	public String createOrder(OrderInfo orderInfo, HttpServletRequest request) {
		//取用户信息
		TbUser user = (TbUser) request.getAttribute("user");
		//把用户信息添加到orderInfo中。
		orderInfo.setUserId(user.getId());
		orderInfo.setBuyerNick(user.getUsername());
		// 直接在这里获取购物车列表
		List<TbItem> cartList = cartService.getCartList(user.getId());
		List<TbOrderItem> orderItems = new ArrayList<>();
		for (TbItem item : cartList) {
			TbOrderItem orderItem = new TbOrderItem();
			orderItem.setItemId(item.getId().toString());
			orderItem.setNum(item.getNum());
			orderItem.setPrice(item.getPrice());
			orderItem.setTotalFee(item.getPrice()*item.getNum());
			orderItem.setTitle(item.getTitle());
			orderItem.setPicPath(item.getImage());
			orderItems.add(orderItem);
		}
		orderInfo.setOrderItems(orderItems);
		//调用服务生成订单
		E3Result e3Result = orderService.createOrder(orderInfo);
		//如果订单生成成功，需要删除购物车，放到生成订单里面。
//		if (e3Result.getStatus() == 200) {
			//清空购物车
//			cartService.clearCartItem(user.getId());
//		}
		//把订单号传递给页面
		request.setAttribute("orderId", e3Result.getData());
		request.setAttribute("payment", orderInfo.getPayment());
		//返回逻辑视图
		return "success";
	}
}
