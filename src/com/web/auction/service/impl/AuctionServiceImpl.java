package com.web.auction.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.auction.mapper.AuctionCustomMapper;
import com.web.auction.mapper.AuctionMapper;
import com.web.auction.mapper.AuctionrecordMapper;
import com.web.auction.pojo.Auction;
import com.web.auction.pojo.AuctionExample;
import com.web.auction.pojo.Auctionrecord;
import com.web.auction.pojo.AuctionrecordExample;
import com.web.auction.services.AuctionService;
import com.web.auction.utils.AuctionPriceException;

@Service
public class AuctionServiceImpl implements AuctionService{
    
	@Autowired
	private AuctionMapper auctionMapper;
	
	@Autowired
	private AuctionrecordMapper auctionrecordMapper;
	
	@Autowired
	private AuctionCustomMapper auctionCustomMapper;
	
	@Override
	public List<Auction> queryAuctions() {
		// TODO Auto-generated method stub
//		查询所有商品，结果是一个商品的集合
		List<Auction> list=auctionMapper.selectByExample(null);
		return list;
	}


	@Override
	public void addAuctions(Auction auction) {
		// TODO Auto-generated method stub
		auctionMapper.insert(auction);
	}


	@Override
	public Auction selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		Auction auction=auctionMapper.selectByPrimaryKey(id);
		return auction;
	}


	@Override
	public void updateAuctions(Auction auction) {
		// TODO Auto-generated method stub
		auctionMapper.updateByPrimaryKey(auction);
	}


	@Override
	public void deleteAuctionById(int id) {
		// TODO Auto-generated method stub
		AuctionrecordExample example=new AuctionrecordExample();
		AuctionrecordExample.Criteria criteria=example.createCriteria();
//		添加商品id相同的条件
		criteria.andAuctionidEqualTo(id);
//		先删除外键所在表的记录
		auctionrecordMapper.deleteByExample(example);
		auctionMapper.deleteByPrimaryKey(id);
	}


	@Override
	public Auction findAuctionAndAuctionRecord(int id) {
		// TODO Auto-generated method stub
		return auctionCustomMapper.findAuctionAndAuctionRecord(id);
	}


	@Override
	public void addAuctionRecord(Auctionrecord record) throws Exception {
		// TODO Auto-generated method stub
		Auction auction=auctionCustomMapper.findAuctionAndAuctionRecord(record.getAuctionid());
		
		if(auction.getAuctionendtime().after(new java.util.Date())==false) {
			 throw new AuctionPriceException("拍卖时间已经过期了");
		}else {
//			  说明不是首次竞拍
			if(auction.getAuctionrecordList().size()>0) {
				Auctionrecord maxRecord=auction.getAuctionrecordList().get(0);
				if(record.getAuctionprice()<maxRecord.getAuctionprice()) {
					 throw new AuctionPriceException("出价不能低于当前竞拍最高价");
				}
				
			}else {
				if(record.getAuctionprice()<auction.getAuctionstartprice()) {
					throw new AuctionPriceException("出价不能低于起拍价");
				}
			}
		}
		auctionrecordMapper.insert(record);
	}

}
