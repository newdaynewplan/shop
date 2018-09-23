package com.chengyu.manager.feign.fallback;

import com.chengyu.common.utils.E3Result;
import com.chengyu.manager.feign.SearchFeign;
import org.springframework.stereotype.Component;

@Component
public class SearchFeignFallback implements SearchFeign {
    @Override
    public E3Result importAllItems() {
        System.out.println("失败！");
        return E3Result.build(404,"远程调用失败！");
    }
}
