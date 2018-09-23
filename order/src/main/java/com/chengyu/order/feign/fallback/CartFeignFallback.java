package com.chengyu.order.feign.fallback;

import com.chengyu.common.utils.E3Result;
import com.chengyu.order.feign.CartFeign;
import com.chengyu.order.pojo.TbItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartFeignFallback implements CartFeign {
    @Override
    public E3Result mergeCart(Long userId, List<TbItem> cartList) {
        System.out.println("合并购物车失败！");
        return E3Result.build(808, "删除购物车失败!");
    }

    @Override
    public List<TbItem> getCartList(Long userId) {
        System.out.println("获取购物车列表失败！");
        return null;
    }

    @Override
    public E3Result clearCartItem(Long userId) {
        System.out.println("删除购物车失败！");
        throw new RuntimeException("删除购物车失败！");
//        return E3Result.build(808,"删除购物车失败!");
    }
}
