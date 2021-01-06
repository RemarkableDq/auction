package com.web.auction.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.FileEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.auction.pojo.User;
import com.web.auction.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/doLogin")
	public String doLogin(String username,String userpassword,String inputCode,Model model,HttpSession httpSession) {
	
//	 获取验证码图片的内容
	 String yzm=(String) httpSession.getAttribute("numrand");
	 if(!yzm.equals(inputCode)) {
		 model.addAttribute("cwMsg","验证码错误");
		 System.out.println("验证码校验");
		 return "login";
	 }
		User dlUser=userService.doLogin(username, userpassword);
		if(dlUser!=null) {
			httpSession.setAttribute("user", dlUser);
			System.out.println("用户名或密码正确");
//			重定向，地址栏发生改变，且不能携带数据
			return "redirect:/queryAll";
		}else {
			model.addAttribute("cwMsg","用户名或密码错误");
			System.out.println("用户名或密码错误");
			return "login";
		}
		
		
	}
	
	
//	注销、退出登录控制器
	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session) {
		session.removeAttribute("user");
//		session.invalidate("user");
		return "login";
		
	}
	
	
	
//	@BindingResult存放错误消息的容器
//	@BindingResult必须紧跟在Pojo的实体类后面
	@RequestMapping("/register")
	public String register(Model model,@ModelAttribute("Useritem") @Validated User user,BindingResult bindingResult) {
		
		if(bindingResult.hasFieldErrors()) {
			System.out.println("输入内容不符合要求");
			
		List<FieldError> errors=bindingResult.getFieldErrors();
		
		for(FieldError fielderror: errors) {
			model.addAttribute(fielderror.getField(),fielderror.getDefaultMessage());
		}
		return "register";
		}
		
		userService.register(user);
		System.out.println("输入内容符合要求");
		model.addAttribute("successMsg","用户"+user.getUsername()+"注册成功");
		return "login";
		
	}
	   

}
