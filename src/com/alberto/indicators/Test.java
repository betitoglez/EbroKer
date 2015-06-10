package com.alberto.indicators;

import java.util.ArrayList;

public class Test {
	
	EMA ema;
	EMA ema5;
	EMA ema10;
	MACD macd;

	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void addPoints (PriceCollection prices){
		prices.add(20);
		prices.add(22);
		prices.add(24);
		prices.add(25);
		prices.add(26);
		prices.add(26);
		prices.add(25);
		prices.add(27);
		prices.add(28);
		prices.add(26);
		prices.add(24);
		prices.add(21);
		prices.add(20);
		System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(22);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(24);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(25);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(26);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(26);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(25);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(27);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(28);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(26);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(24);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(25);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(28);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(32);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(34);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(35);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(33);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(32);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(32);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(31);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(30);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(29);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(27);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(22);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(20);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(22);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(24);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(25);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(26);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(26);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(25);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(27);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(28);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(26);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(24);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
		prices.add(21);System.out.println("Precio:" + prices.getLast() +  " Ema5 " + this.ema5.get() + " Ema10 " + this.ema10.get() + " MACD " + this.macd.get() + " Indi " +this.macd.getIndicator());
	}
	
	
	public static void main (String... args){
		
	    Test test = new Test();	
		PriceCollection prices = new PriceCollection();
		
		 test.ema = new EMA(prices,10);
		 test.ema10 = new EMA(prices,10);
		 test.ema5 = new EMA(prices,5);
		 test.macd = new MACD(prices,5,10,9);
		
		 test.addPoints(prices);
		 
		
		
		
		
		
		
		
	}

} 
