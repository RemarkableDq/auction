package com.web.auction.utils;

public class AuctionPriceException extends Exception{
	
	private String message;
	
	
	
	public AuctionPriceException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
