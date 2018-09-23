package com.chengyu.cart.feign;

import com.chengyu.cart.config.FeignConfig;
import com.chengyu.cart.feign.fallback.ManagerFeignFallback;
import com.chengyu.cart.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "manager",configuration = FeignConfig.class,fallback = ManagerFeignFallback.class )
public interface ManagerFeign {

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem getItemById(@PathVariable(value = "itemId") Long itemId);

}
