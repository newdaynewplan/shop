package com.chengyu.order.feign.fallback;

import com.chengyu.common.utils.E3Result;
import com.chengyu.order.feign.CartFeign;
import com.chengyu.order.pojo.TbItem;

import java.util.List;

public class CartFeignFallback implements CartFeign {
    @Override
    public E3Result mergeCart(Long userId, List<TbItem> cartList) {
        return null;
    }
}
