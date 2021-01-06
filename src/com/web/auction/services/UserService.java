package com.web.auction.services;

import com.web.auction.pojo.User;

//接口类
public interface UserService {
	
//	登录的方法
	 public User doLogin(String username,String password);
	 
//	 注册的方法
	 public void register (User user);

}
