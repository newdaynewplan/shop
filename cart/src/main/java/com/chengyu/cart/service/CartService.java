package com.chengyu.cart.service;


import com.chengyu.cart.pojo.TbItem;
import com.chengyu.common.utils.E3Result;

import java.util.List;

public interface CartService {

	E3Result addCart(long userId, long itemId, int num);
	E3Result mergeCart(long userId, List<TbItem> itemList);
	List<TbItem> getCartList(long userId);
	E3Result updateCartNum(long userId, long itemId, int num);
	E3Result deleteCartItem(long userId, long itemId);
	E3Result clearCartItem(long userId);
}
