package com.chengyu.manager.controller;

import com.chengyu.manager.dao.TbUserMapper;
import com.chengyu.manager.pojo.TbUser;
import com.chengyu.manager.pojo.TbUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private TbUserMapper userMapper;

    @RequestMapping(value = "/user/list")
    public List<TbUser> getUserByExample(TbUserExample example){
        return userMapper.selectByExample(example);
    }

    @RequestMapping(value = "/user/insert")
    public Integer insert(TbUser user){
        return userMapper.insert(user);
    }
}
