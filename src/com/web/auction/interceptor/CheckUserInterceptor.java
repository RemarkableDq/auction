package com.web.auction.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import org.springframework.web.servlet.ModelAndView;

//拦截器要实现这个接口，HandlerInterceptor
public class CheckUserInterceptor implements HandlerInterceptor {
    
//	在return之后
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

//	进入方法体，在return之前
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
//	在访问handler之前
//	return false表示拦截
//	return true表示放行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		
		String path=request.getRequestURI();
		
//		||path.indexOf("publishAuctions")!=-1
		if (path.indexOf("doLogin")!=-1||path.indexOf("register")!=-1) {
			return true;
		}
		
		HttpSession session=request.getSession();
		System.out.println(session.getAttribute("user"));
		
		if (session.getAttribute("user")!=null) {
			System.out.println("放行");
			return true;
		}else {
			System.out.println("拦截，跳回登录页面");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return false;
		}
	
	}



}
