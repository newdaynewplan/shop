package com.chengyu.order.feign;

import com.chengyu.common.utils.E3Result;
import com.chengyu.order.feign.fallback.SsoFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "sso",fallback = SsoFeignFallback.class )
public interface SsoFeign {

    @RequestMapping(value="/user/token/e3result/{token}")
    @ResponseBody
    public E3Result getUserByToken(@PathVariable(value = "token") String token);
}
