package com.chengyu.sso.feign.fallback;


import com.chengyu.sso.feign.ManagerFeign;
import com.chengyu.sso.pojo.TbUser;
import com.chengyu.sso.pojo.TbUserExample;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagerFeignFallback implements ManagerFeign {

    @Override
    public List<TbUser> getUserByExample(TbUserExample example) {
        return null;
    }

    @Override
    public Integer insert(TbUser user) {
        return null;
    }
}
