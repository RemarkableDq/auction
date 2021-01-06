package com.web.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.net.httpserver.HttpsConfigurator;
import com.web.auction.pojo.Auction;
import com.web.auction.pojo.Auctionrecord;
import com.web.auction.pojo.User;
import com.web.auction.services.AuctionService;

@Controller
public class AuctionController {
	
	  @Autowired
      private AuctionService auctionService;  
	  
//	  定义一个常量，注意常量名字母要全大写
	  private static final int PAGE_SIZE=10;
	
	  @RequestMapping("/queryAll")
	  public ModelAndView queryAuction(@RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum) {
	
//		  pageNum为当前页，PAGE_SIZE为每页显示的记录数，PageHelper.startPage开启分页功能
	  PageHelper.startPage(pageNum,PAGE_SIZE);  
	  List<Auction>	auctionList=auctionService.queryAuctions(); 
//	  创建bean类，分页实体类
	  PageInfo page=new PageInfo<>(auctionList);
	  
//	  创建model对象，用来携带数据和返回视图对象
	  ModelAndView mav=new ModelAndView();
	  mav.addObject("auctionList",auctionList);
	  mav.addObject("page", page);
	    mav.setViewName("index");
		return mav;
	  }
	  
//	  MultipartFile接收二进制文件
	  @RequestMapping("/publishAuctions")
	  public String  publishAuctions(Auction auction,MultipartFile pic,HttpSession session) {
	
		  try {
			String path=session.getServletContext().getRealPath("upload");
			System.out.println(path);
			File targetFile=new File(path,pic.getOriginalFilename());
			pic.transferTo(targetFile);
			auction.setAuctionpic(pic.getOriginalFilename());
			auction.setAuctionpictype(pic.getContentType());
			auctionService.addAuctions(auction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
		  return "redirect:/queryAll";
		  
	  }
	  
	  @RequestMapping("/toUpdate/{id}")
	  public ModelAndView  toUpdate(@PathVariable int id) {
	
	        ModelAndView mv=new ModelAndView();
			System.out.println(id);
			Auction auction=auctionService.selectByPrimaryKey(id);
			mv.addObject("auction", auction);
			mv.setViewName("updateAuction");
		    return mv;
		  
	  }
	  
	  //	  MultipartFile接收二进制文件
	  @RequestMapping("/submitUpdateAuction")
	  public String  submitUpdateAuction(Auction auction,MultipartFile pic,HttpSession session) {
	
		  try {
			  
			  if(pic.getSize()>0) {
				    String path=session.getServletContext().getRealPath("upload");			
					System.out.println(path);
					
					File oldFile=new File(path,auction.getAuctionpic());
					if(oldFile.exists()) {
						oldFile.delete();
					}
					
					File targetFile=new File(path,pic.getOriginalFilename());
					pic.transferTo(targetFile);
					auction.setAuctionpic(pic.getOriginalFilename());
					auction.setAuctionpictype(pic.getContentType());
					auctionService.updateAuctions(auction);
			  }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
		  return "redirect:/queryAll";
		  
	  }
	  
//	  删除商品信息
	  @RequestMapping("/deleteAuctionById/{id}")
	  public String deleteAuctionById(@PathVariable int id) {
		auctionService.deleteAuctionById(id);  
		return "redirect:/queryAll";
		  
	  }
	  
//	  去商品详情页(去拍卖商品)
	  @RequestMapping("/toDetail/{id}")
	  public ModelAndView toDetail(@PathVariable int id) {
		System.out.println(id);
		Auction auction=auctionService.findAuctionAndAuctionRecord(id);
		System.out.println(auction);
		ModelAndView mv=new ModelAndView();
		mv.addObject("auctionDetail", auction);
		mv.setViewName("auctionDetail");
		return mv;
		  
	  }
	  
	  
	  @RequestMapping("/saveAuctionRecord")
	  public String saveAuctionRecord(Auctionrecord record,HttpSession session) throws Exception{
		record.setAuctiontime(new Date());  
	    User user=(User) session.getAttribute("user");
	    record.setUserid(user.getUserid());
		auctionService.addAuctionRecord(record);  
		return "redirect:/toDetail/"+record.getAuctionid();
	  }
	  
	  
}
