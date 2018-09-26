package com.chengyu.manager.feign.fallback;

import com.chengyu.common.utils.E3Result;
import com.chengyu.manager.feign.SearchFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SearchFeignFallback implements SearchFeign {
    private static Logger logger = LoggerFactory.getLogger(SearchFeignFallback.class);
    @Override
    public E3Result importAllItems() {
        logger.error("远程调用 Search 失败!");
        return E3Result.build(404,"远程调用失败！");
    }
}
