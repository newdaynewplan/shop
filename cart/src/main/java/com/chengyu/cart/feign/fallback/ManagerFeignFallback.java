package com.chengyu.cart.feign.fallback;


import com.chengyu.cart.feign.ManagerFeign;
import com.chengyu.cart.pojo.TbItem;
import org.springframework.stereotype.Component;

@Component
public class ManagerFeignFallback implements ManagerFeign {
    @Override
    public TbItem getItemById(Long itemId) {
        throw new RuntimeException("远程调用Manager失败！");
    }
}
