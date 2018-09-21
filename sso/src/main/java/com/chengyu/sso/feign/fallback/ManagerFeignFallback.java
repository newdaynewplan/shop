package com.chengyu.sso.feign.fallback;


import com.chengyu.sso.feign.ManagerFeign;
import com.chengyu.sso.pojo.TbUser;
import com.chengyu.sso.pojo.TbUserExample;

import java.util.List;

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
