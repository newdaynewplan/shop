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

    @RequestMapping(value = "/getCartList", method = RequestMethod.POST)
    public List<TbItem> getCartList(@RequestParam(value = "userId") Long userId);

    /**
     * 	清空购物车商品
     */
    @RequestMapping("/clearCartItem")
    @ResponseBody
    public E3Result clearCartItem(@RequestParam(value = "userId") Long userId);
}
