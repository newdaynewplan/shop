package com.chengyu.cart.feign;

import com.chengyu.cart.config.FeignConfig;
import com.chengyu.cart.feign.fallback.SsoFeignFallback;
import com.chengyu.common.utils.E3Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "sso",configuration = FeignConfig.class,fallback = SsoFeignFallback.class )
public interface SsoFeign {

    @RequestMapping(value="/user/token/e3result/{token}")
    @ResponseBody
    public E3Result getUserByToken(@PathVariable(value = "token") String token);
}
