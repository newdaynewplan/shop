package com.chengyu.manager.feign.fallback;

import com.chengyu.common.utils.E3Result;
import com.chengyu.manager.feign.SearchItemServiceFeign;

public class SearchItemServiceFeignFallback implements SearchItemServiceFeign {
    @Override
    public E3Result importAllItems() {
        System.out.println("失败！");
        return E3Result.build(404,"远程调用失败！");
    }
}
