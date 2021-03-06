package com.chengyu.sso.service.impl;

import com.chengyu.common.utils.E3Result;
import com.chengyu.common.utils.JsonUtils;
import com.chengyu.sso.feign.ManagerFeign;
import com.chengyu.sso.pojo.TbUser;
import com.chengyu.sso.pojo.TbUserExample;
import com.chengyu.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户登录处理
 * <p>Title: LoginServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private ManagerFeign userMapper;
	@Autowired
	private StringRedisTemplate jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	
	@Override
	public E3Result userLogin(String username, String password) {
		// 1、判断用户和密码是否正确
		//根据用户名查询用户信息
		TbUserExample example = new TbUserExample();
		TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		//执行查询
		List<TbUser> list = userMapper.getUserByExample(example);
		if (list == null || list.size() == 0) {
			//返回登录失败
			return E3Result.build(400, "用户名或密码错误");
		}
		//取用户信息
		TbUser user = list.get(0);
		//判断密码是否正确
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			// 2、如果不正确，返回登录失败
			return E3Result.build(400, "用户名或密码错误");
		}
		// 3、如果正确生成token。
		String token = UUID.randomUUID().toString();
		// 4、把用户信息写入redis，key：token value：用户信息
		user.setPassword(null);
		ValueOperations<String, String> ops = jedisClient.opsForValue();
		ops.set("SESSION:" + token, JsonUtils.objectToJson(user),SESSION_EXPIRE, TimeUnit.SECONDS);
		// 5、设置Session的过期时间
//		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		// 6、把token返回
		 
		return E3Result.ok(token);
	}

}
