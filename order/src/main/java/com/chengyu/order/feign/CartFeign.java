package com.chengyu.order.feign;

import com.chengyu.common.utils.E3Result;
import com.chengyu.order.feign.fallback.CartFeignFallback;
import com.chengyu.order.feign.fallback.SsoFeignFallback;
import com.chengyu.order.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "cart",fallback = CartFeignFallback.class )
public interface CartFeign {

    @RequestMapping(value = "/mergeCart", method = RequestMethod.POST)
    public E3Result mergeCart(@RequestParam(value = "userId") Long userId, @RequestBody List<TbItem> cartList);
}
