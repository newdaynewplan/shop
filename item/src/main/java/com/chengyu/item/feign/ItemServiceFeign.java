package com.chengyu.item.feign;


import com.chengyu.item.feign.fallback.ItemServiceFeignFallback;
import com.chengyu.item.pojo.TbItem;
import com.chengyu.item.pojo.TbItemDesc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "manager", fallback = ItemServiceFeignFallback.class)
public interface ItemServiceFeign {
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem getItemById(@PathVariable(value = "itemId") Long itemId);

    @RequestMapping(value = "/itemDesc/{itemId}", method = RequestMethod.GET)
    public TbItemDesc getItemDescById(@PathVariable(value = "itemId") Long itemId);
}
