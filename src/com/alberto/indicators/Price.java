package com.alberto.indicators;

import org.joda.time.DateTime;

public class Price {
	
	private double price;
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public DateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(DateTime datetime) {
		this.datetime = datetime;
	}

	DateTime datetime;

	public Price(double price) {
		// TODO Auto-generated constructor stub
		this.price = price;
	}
	
	public Price (double price , DateTime datetime){
		this(price);
		this.datetime = datetime;
	}

}
