package com.chengyu.manager.feign;


import com.chengyu.common.utils.E3Result;
import com.chengyu.manager.feign.fallback.SearchFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "search", fallback = SearchFeignFallback.class)
public interface SearchFeign {

    @RequestMapping("/index/item/import")
    @ResponseBody
    public E3Result importAllItems();
}
