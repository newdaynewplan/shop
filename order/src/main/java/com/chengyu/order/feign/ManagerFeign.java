package com.chengyu.order.feign;

import com.chengyu.order.config.FeignConfig;
import com.chengyu.order.feign.fallback.ManagerFeignFallback;
import com.chengyu.order.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "manager",configuration = FeignConfig.class,fallback = ManagerFeignFallback.class )
public interface ManagerFeign {

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem getItemById(@PathVariable(value = "itemId") Long itemId);

}
