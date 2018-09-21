package com.chengyu.sso.feign;

import com.chengyu.sso.feign.fallback.ManagerFeignFallback;
import com.chengyu.sso.pojo.TbUser;
import com.chengyu.sso.pojo.TbUserExample;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "manager",fallback = ManagerFeignFallback.class )
public interface ManagerFeign {

    @ResponseBody
    @RequestMapping(value = "/user/list")
    public List<TbUser> getUserByExample(TbUserExample example);

    @RequestMapping(value = "/user/insert")
    public Integer insert(TbUser user);
}
