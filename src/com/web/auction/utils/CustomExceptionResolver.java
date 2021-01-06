package com.web.auction.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handle,
			Exception ex) {
		// TODO Auto-generated method stub
		
		ModelAndView mv=new ModelAndView();
		if(ex instanceof AuctionPriceException) {
			 mv.addObject("errorMsg", ex.getMessage());
			 mv.setViewName("error");
		}else {
			 mv.addObject("errorMsg", "未知异常");
			 mv.setViewName("error");
		}
		return mv;
	}

}
