package com.chengyu.order.feign.fallback;


import com.chengyu.order.feign.ManagerFeign;
import com.chengyu.order.pojo.TbItem;
import org.springframework.stereotype.Component;

@Component
public class ManagerFeignFallback implements ManagerFeign {
    @Override
    public TbItem getItemById(Long itemId) {
        return null;
    }
}
