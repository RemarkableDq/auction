package com.web.auction.services;

import java.util.List;

import com.web.auction.pojo.Auction;
import com.web.auction.pojo.Auctionrecord;

public interface AuctionService {
        
	 public List<Auction> queryAuctions();
	 
//	 增加商品的抽象方法
	 public void addAuctions(Auction auction);
	 
	 public Auction selectByPrimaryKey(int id);
	 
//	 修改商品的抽象方法
	 public void updateAuctions(Auction auction);
	 
//	 删除商品的方法
	 public void deleteAuctionById(int id);
	 
//	 查找具体商品和商品拍卖记录
	 public Auction findAuctionAndAuctionRecord(int id);
	 
	 public void addAuctionRecord(Auctionrecord record) throws Exception;
}
