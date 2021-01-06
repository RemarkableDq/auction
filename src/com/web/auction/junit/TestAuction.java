package com.web.auction.junit;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.auction.mapper.AuctionCustomMapper;
import com.web.auction.pojo.Auction;
import com.web.auction.pojo.Auctionrecord;

@RunWith(SpringJUnit4ClassRunner.class)
//添加spring的环境
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class TestAuction {
     
	 @Autowired
	 private AuctionCustomMapper auctionCustomMapper;
	 
	 @Test
	 public void queryAuction() {
		 Auction auction =auctionCustomMapper.findAuctionAndAuctionRecord(50);
		 System.out.println(auction);
		 List<Auctionrecord> recordList=auction.getAuctionrecordList();
		 for (Auctionrecord auctionrecord : recordList) {
			System.out.println("\n"+auctionrecord.getAuctionprice()+auctionrecord.getAuctiontime()+auctionrecord.getUser().getUsername());
		}
	 }
	
}
