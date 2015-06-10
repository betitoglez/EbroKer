package com.alberto;

import com.alberto.chart.Chart;
import com.alberto.rules.AlbertoEntryDefault;
import com.alberto.rules.AlbertoHighPointRule;
import com.alberto.rules.AlbertoStopLoss;

import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;

public class DefaultEstrategy extends AbstractStrategy {
	
	
	AlbertoStopLoss stopLoss;
	double stopLossPoints;
	int macd1 ; int macd2 ; int macdema;
	boolean extraSecure;
	

	

	public DefaultEstrategy(TradingDay trading,double stopLossPoints, int macd1 , int macd2 , int macdema, boolean extraSecure) {
		super(trading);
		// TODO Auto-generated constructor stub
		if (extraSecure) {
			this.name = "DefaultStrategy_ExtraSecure_StopLoss_"+stopLossPoints;
		}else{
			this.name = "DefaultStrategy_StopLoss_"+stopLossPoints;
		}
		
		this.stopLossPoints = stopLossPoints;
		this.macd1 = macd1 ; this.macd2=macd2 ; this.macdema =macdema;
		this.extraSecure = extraSecure;
		setStrategy();
	}

	@Override
	void setStrategy() {
		// TODO Auto-generated method stub
		ClosePriceIndicator close = new ClosePriceIndicator(this.trading.serie);
		stopLoss = new AlbertoStopLoss(close,stopLossPoints);
    	super.strategy = new Strategy(new AlbertoEntryDefault(close,macd1,macd2,macdema,extraSecure),stopLoss);

	}
	
	
	@Override
	void postSell() {
		// TODO Auto-generated method stub
		super.postSell();
		stopLoss.postSell();
		
	}

}
