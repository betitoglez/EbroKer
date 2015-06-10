package com.alberto;

import com.alberto.rules.AlbertoHighPointRule;
import com.alberto.rules.AlbertoStopLoss;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;
import eu.verdelhan.ta4j.indicators.trackers.RSIIndicator;
import eu.verdelhan.ta4j.trading.rules.CrossedUpIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.StopLossRule;

class AlternativeEstrategy extends AbstractStrategy {
	
	AlbertoStopLoss stopLoss;
	double stopLossPercentage;
	
	public AlternativeEstrategy(TradingDay trading,double stopLossPercentage) {
		super(trading);
		// TODO Auto-generated constructor stub
		this.name = "AlternativeStrategy_StopLoss_"+stopLossPercentage;
		this.stopLossPercentage = stopLossPercentage;
		setStrategy();
		
	}

	@Override
	void setStrategy() {
		// TODO Auto-generated method stub
		ClosePriceIndicator close = new ClosePriceIndicator(this.trading.serie);
		stopLoss = new AlbertoStopLoss(close,stopLossPercentage);
    	super.strategy = new Strategy(new AlbertoHighPointRule(close),stopLoss);

	}
	
	
	@Override
	void postSell() {
		// TODO Auto-generated method stub
		super.postSell();
		stopLoss.postSell();
		
	}

}
