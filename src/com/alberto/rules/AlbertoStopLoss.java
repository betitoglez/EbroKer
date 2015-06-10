package com.alberto.rules;

import eu.verdelhan.ta4j.TradingRecord;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;
import eu.verdelhan.ta4j.trading.rules.AbstractRule;

public class AlbertoStopLoss extends AbstractRule {
	
	ClosePriceIndicator close;
	double stopLossPrice;
	double limitPoints;
	

	public AlbertoStopLoss (ClosePriceIndicator closePrice, double limitPoints) {
		// TODO Auto-generated constructor stub
		close = closePrice;
		this.stopLossPrice = 0;
		this.limitPoints = limitPoints;
	}
	
	@Override
	public boolean isSatisfied(int index, TradingRecord tradingRecord) {
		// TODO Auto-generated method stub
		double closePrice = close.getValue(index).toDouble();
		double stopLossAux = closePrice - limitPoints;
		//System.out.println(tradingRecord.getTradeCount());
		if (stopLossPrice <= 0){
			stopLossPrice = stopLossAux;
		}
		
		//System.out.println("Precio Actual: " + closePrice + "; Stop Loss en :" + stopLossPrice);
		
		if (closePrice <= stopLossPrice){
			return true;
		}
		
		if (stopLossAux > stopLossPrice){
			stopLossPrice = stopLossAux;
		}
	
		return false;
	}
	
	public void postSell (){
		stopLossPrice = 0;
	}

}
