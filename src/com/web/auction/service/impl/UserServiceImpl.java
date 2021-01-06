package com.web.auction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.auction.mapper.UserMapper;
import com.web.auction.pojo.User;
import com.web.auction.pojo.UserExample;
import com.web.auction.services.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User doLogin(String username, String password) {
		// TODO Auto-generated method stub
		
		UserExample example=new UserExample();
		UserExample.Criteria criteria=example.createCriteria();
//		添加查询条件
		criteria.andUsernameEqualTo(username);
		criteria.andUserpasswordEqualTo(password);
//		返回一个集合对象
		//System.out.println(username+password+"账号密码");
		List<User> user=userMapper.selectByExample(example);
		if(user!=null&&user.size()>0) {
			return user.get(0);
		}else {
			return null;
		}
		
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		user.setUserisadmin(0);
		userMapper.insert(user);
	}

}
