package com.chengyu.sso.service;


import com.chengyu.common.utils.E3Result;
import com.chengyu.sso.pojo.TbUser;

public interface RegisterService {

	E3Result checkData(String param, int type);
	E3Result register(TbUser user);
}
