package com.chengyu.order.feign.fallback;

import com.chengyu.common.utils.E3Result;
import com.chengyu.order.feign.SsoFeign;

public class SsoFeignFallback implements SsoFeign {
    @Override
    public E3Result getUserByToken(String token) {
        return null;
    }
}
