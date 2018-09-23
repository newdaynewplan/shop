package com.chengyu.cart.feign.fallback;

import com.chengyu.cart.feign.SsoFeign;
import com.chengyu.common.utils.E3Result;
import org.springframework.stereotype.Component;

@Component
public class SsoFeignFallback implements SsoFeign {
    @Override
    public E3Result getUserByToken(String token) {
        return E3Result.build(808,"获取token");
    }
}
