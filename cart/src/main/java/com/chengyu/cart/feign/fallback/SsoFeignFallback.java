package com.chengyu.cart.feign.fallback;

import com.chengyu.cart.feign.SsoFeign;
import com.chengyu.common.utils.E3Result;

public class SsoFeignFallback implements SsoFeign {
    @Override
    public E3Result getUserByToken(String token) {
        return null;
    }
}
