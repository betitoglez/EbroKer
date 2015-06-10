package com.alberto;

import java.util.ArrayList;
import java.util.List;

import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.TimeSeries;

public class TradingDay {
	
	TimeSeries serie;
	List<AbstractStrategy> strategies;
	Tick lastTick;
	 
	public TradingDay (String serieName){
		List<Tick> ticks = new ArrayList<Tick>();
		TimeSeries serie = new TimeSeries(serieName,ticks);
		this.serie = serie;
		init();
	}
	
	public TradingDay (TimeSeries serie){
		this.serie = serie;
		init();
	}
	
	
	protected void init ()
	{
		strategies = new ArrayList<AbstractStrategy>();
		
//		strategies.add(new RSIStopLossStrategy(this,30,75,0.01));
//		strategies.add(new AlternativeEstrategy(this,0.001));
//		strategies.add(new AlternativeEstrategy(this,0.002));
//		strategies.add(new AlternativeEstrategy(this,0.005));
//		strategies.add(new AlternativeEstrategy(this,0.01));
//		strategies.add(new AlternativeEstrategy(this,0.02));
//		strategies.add(new AlternativeEstrategy(this,0.05));		
//		strategies.add(new DefaultEstrategy(this, 0.001, 12, 26, 9));
		strategies.add(new DefaultEstrategy(this, 25, 12, 26, 9,false));
//		strategies.add(new DefaultEstrategy(this, 0.002, 12, 26, 9,false));
//		strategies.add(new DefaultEstrategy(this, 0.003, 12, 26, 9));
//		strategies.add(new DefaultEstrategy(this, 0.005, 12, 26, 9));
//		strategies.add(new DefaultEstrategy(this, 0.01, 12, 26, 9));
//		strategies.add(new DefaultEstrategy(this, 0.03, 12, 26, 9));
//		strategies.add(new DefaultEstrategy(this, 0.05, 12, 26, 9));
	}
	
	
	public void addTick (Tick tick){
		this.serie.addTick(tick);
		lastTick = tick;
	}
	
	
	public void trade () {
		for (AbstractStrategy strategy : this.strategies){
			boolean result = strategy.operate();
			if (result){
				//System.out.println(strategy.getClass().getSimpleName() + " Opera");
			}
		}
	}
	
	public String getProfits(){
		String results = new String();
		for (AbstractStrategy strategy : this.strategies){
			//System.out.println("Beneficio de "+strategy.getClass().getSimpleName()+": " + strategy.getProfit());
			 System.out.println(strategy.getName() + ";" + strategy.profit + ";" + strategy.tradingRecord.getTradeCount() + "\r\n");
			 results += strategy.getName() + ";" + Math.ceil(strategy.profit) + ";"  + ";" + Math.ceil(strategy.profitloss) + ";"  + strategy.tradingRecord.getTradeCount() + "\r\n";
		}
		return results;
	}
	
	public void closePositions()
	{
		for (AbstractStrategy strategy : this.strategies){
			strategy.closePosition();
		}
	}

}
