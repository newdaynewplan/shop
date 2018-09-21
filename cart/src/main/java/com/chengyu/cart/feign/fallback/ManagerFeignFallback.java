package com.chengyu.cart.feign.fallback;


import com.chengyu.cart.feign.ManagerFeign;
import com.chengyu.cart.pojo.TbItem;

import java.util.List;

public class ManagerFeignFallback implements ManagerFeign {
    @Override
    public TbItem getItemById(Long itemId) {
        return null;
    }
}