package com.alberto;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;
import eu.verdelhan.ta4j.indicators.trackers.RSIIndicator;
import eu.verdelhan.ta4j.indicators.trackers.SMAIndicator;
import eu.verdelhan.ta4j.trading.rules.BooleanRule;
import eu.verdelhan.ta4j.trading.rules.CrossedDownIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.CrossedUpIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.OverIndicatorRule;
import eu.verdelhan.ta4j.trading.rules.StopGainRule;
import eu.verdelhan.ta4j.trading.rules.StopLossRule;
import eu.verdelhan.ta4j.trading.rules.UnderIndicatorRule;

public class RSIStopLossStrategy extends AbstractStrategy {

	private int rsiPoints;
	private double rsiValue;
	private double stopLoss;
	
	
	public RSIStopLossStrategy(TradingDay trading) {
		super(trading);
		setStrategy();
	}
	
	public RSIStopLossStrategy(TradingDay trading, int rsiPoints,double rsiValues, double stopLoss) {
		super(trading);
		
		this.rsiPoints = rsiPoints;
		this.rsiValue = rsiValues;
		this.stopLoss = stopLoss;
		
		super.name = "RSI, Valores " + rsiPoints + ", Corte " + rsiValues + ". StopLoss " + stopLoss + "%";
		setStrategy();
	}

	@Override
	void setStrategy() {
		// TODO Auto-generated method stub
		ClosePriceIndicator close = new ClosePriceIndicator(this.trading.serie);
    	RSIIndicator rsiIndicator = new RSIIndicator(close, rsiPoints);
    	
    	CrossedUpIndicatorRule cross = new CrossedUpIndicatorRule(rsiIndicator, Decimal.valueOf(rsiValue));
    	StopLossRule stopLossRule = new StopLossRule(close, Decimal.valueOf(stopLoss));
    	super.strategy = new Strategy(new CrossedUpIndicatorRule(rsiIndicator, Decimal.valueOf(rsiValue)), stopLossRule);
		/*
		   ClosePriceIndicator closePrice = new ClosePriceIndicator(this.trading.serie);
		   SMAIndicator sma = new SMAIndicator(closePrice, 12);

	        // Signals
	        // Buy when SMA goes over close price
	        // Sell when close price goes over SMA
	        this.strategy = new Strategy(
	                new OverIndicatorRule(sma, closePrice),
	                new UnderIndicatorRule(sma, closePrice)
	        );
	        */
	       
	}

}
