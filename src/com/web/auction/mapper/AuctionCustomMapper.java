package com.web.auction.mapper;

import com.web.auction.pojo.Auction;
import com.web.auction.pojo.Auctionrecord;

public interface AuctionCustomMapper {
	
	public Auction findAuctionAndAuctionRecord(int id);
	
	public void addAuctionRecord(Auctionrecord auctionrecord);

}
