package com.alberto.indicators;

import java.util.ArrayList;

public class PriceCollection extends ArrayList<Price> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6076500636798727546L;

	public void add (double price){
		this.add(new Price(price));
	}
	
	public void add (int index , double price){
		this.add(index, new Price(price));
	}
	
	public double getLast ()
	{
		return this.get(this.size()-1).getPrice();
	}

}
