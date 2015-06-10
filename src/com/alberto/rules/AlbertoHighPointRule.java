package com.alberto.rules;

import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.TradingRecord;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;
import eu.verdelhan.ta4j.trading.rules.AbstractRule;

public class AlbertoHighPointRule extends AbstractRule {

	ClosePriceIndicator close;
	

	public AlbertoHighPointRule (ClosePriceIndicator closePrice) {
		// TODO Auto-generated constructor stub
		close = closePrice;
	
	}
	
	@Override
	public boolean isSatisfied(int index, TradingRecord tradingRecord) {
	
		if (close.getTimeSeries().getTickCount() >= 60){
		
			double maxi = 0;
			int startIndex = close.getTimeSeries().getTickCount() - 59;
			for (int i = startIndex; i < startIndex+59; i++){
				double auxPrice = close.getTimeSeries().getTick(i).getClosePrice().toDouble();
				if (auxPrice > maxi){
					maxi = auxPrice;
				}
			}
			
			
			//System.out.println("Maximo precio: " + maxi + "; Precio actual: " + close.getTimeSeries().getLastTick().getClosePrice().toDouble());
			
			if (close.getTimeSeries().getLastTick().getClosePrice().toDouble() >= maxi){
			
				return true;
			}
			
		}
	
		return false;
	}

}
