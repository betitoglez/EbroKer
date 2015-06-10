package com.alberto.rules;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.alberto.chart.Chart;

import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.TradingRecord;
import eu.verdelhan.ta4j.analysis.criteria.AverageProfitableTradesCriterion;
import eu.verdelhan.ta4j.indicators.helpers.AverageDirectionalMovementDownIndicator;
import eu.verdelhan.ta4j.indicators.helpers.AverageDirectionalMovementUpIndicator;
import eu.verdelhan.ta4j.indicators.helpers.MeanDeviationIndicator;
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator;
import eu.verdelhan.ta4j.indicators.trackers.AverageDirectionalMovementIndicator;
import eu.verdelhan.ta4j.indicators.trackers.EMAIndicator;
import eu.verdelhan.ta4j.indicators.trackers.MACDIndicator;
import eu.verdelhan.ta4j.indicators.trackers.RSIIndicator;
import eu.verdelhan.ta4j.indicators.trackers.SMAIndicator;
import eu.verdelhan.ta4j.trading.rules.AbstractRule;
import eu.verdelhan.ta4j.trading.rules.CrossedUpIndicatorRule;

public class AlbertoEntryDefault extends AbstractRule {

	ClosePriceIndicator close;
	int macd1 ; int macd2 ; int macdema;
	MACDIndicator macd;
	EMAIndicator emaMacd;
	
	SMAIndicator sma;
	EMAIndicator ema5;
	EMAIndicator ema10;
	EMAIndicator ema50;
  
	
	AverageDirectionalMovementDownIndicator averageDown;
	AverageDirectionalMovementUpIndicator averageUp;
	AverageDirectionalMovementIndicator average;
	
	
	CrossedUpIndicatorRule crossUpMacd;
	CrossedUpIndicatorRule crossEma;

	
	boolean extraSecure = false;
	String  extraSecureMessage = "";

	public AlbertoEntryDefault(ClosePriceIndicator closePrice,int macd1 , int macd2 , int macdema, boolean extraSecure) {
		// TODO Auto-generated constructor stub
		close = closePrice;
		this.macd1 = macd1 ; this.macd2=macd2 ; this.macdema =macdema;
		this.extraSecure = extraSecure;
		
		macd = new MACDIndicator(close, macd1, macd2);
		emaMacd = new EMAIndicator(macd, macdema);

		sma = new SMAIndicator(close, 100);
		
		ema5 = new EMAIndicator(close, 5);
		ema10 = new EMAIndicator(close, 10);
		ema50 = new EMAIndicator(close, 50);
		
		averageDown = new AverageDirectionalMovementDownIndicator(close.getTimeSeries(), 10);
		averageUp = new AverageDirectionalMovementUpIndicator(close.getTimeSeries(), 10);
		average = new AverageDirectionalMovementIndicator(close.getTimeSeries(), 10);

		crossUpMacd = new CrossedUpIndicatorRule(emaMacd, macd);
		crossEma = new CrossedUpIndicatorRule(ema10, ema5);
		

	}
	
	private double getMean (int index){
		int start = index -30;
		if (start < 0){
			start = 0;
		}
		double sum = 0;
		int group = 0;
		for (int i=start; i < index;i++){
			group++;
			sum += close.getValue(i).toDouble();
		}
		return sum / group;
	}
	
	
	private boolean _extraSecure (int index, TradingRecord tradingRecord)
	{
		/*
		TimeSeries _serie = close.getTimeSeries();
		if (_serie.getTick(index-26).getClosePrice().isGreaterThanOrEqual(_serie.getTick(index).getClosePrice())){
			return false;
		}
		DescriptiveStatistics _stats = new DescriptiveStatistics();
		
		for (int i = index-26;i<= index;i++){
			_stats.addValue(_serie.getTick(i).getClosePrice().toDouble());
		}
		*/
		
		//extraSecureMessage = "Close: "+ _serie.getTick(index).getClosePrice().toDouble() + " High:" + _stats.getMax() + " Desv. Est. " + _stats.getStandardDeviation();
		//System.out.println(_serie.getTick(index).getClosePrice().toDouble() > _stats.getMean());
    
		//return _serie.getTick(index).getClosePrice().toDouble() >= _stats.getMax() && (_serie.getTick(index).getClosePrice().toDouble() >= getMean(12))
		//		&& (_serie.getTick(index).getClosePrice().toDouble() >= getMean(index));
		//return _serie.getTick(index).getClosePrice().toDouble() > _stats.getMean();
		return true;
	}

	@Override
	public boolean isSatisfied(int index, TradingRecord tradingRecord) {
		// TODO Auto-generated method stub
		
		if (index < 100){
		//	return false;
		}
		double macdValue  = macd.getValue(close.getTimeSeries().getEnd()).toDouble();
		double emacdValue = emaMacd.getValue(close.getTimeSeries().getEnd()).toDouble();
		//System.out.println("Close:"+ close.getValue(index).toDouble() + " ema5 " + ema5.getValue(index) + " ema10 " + ema10.getValue(index));
		return close.getValue(index).toDouble()>getMean(index)&&(macdValue >0 && emacdValue>0) && (macdValue > emacdValue)&&close.getValue(index).toDouble()>ema5.getValue(index).toDouble()&&
				close.getValue(index).toDouble() > ema10.getValue(index).toDouble() && ema5.getValue(index).toDouble()>ema10.getValue(index).toDouble();
		
		/*
		
		if (extraSecure){
			if (!this._extraSecure(index, tradingRecord)){
				return false;
			}
		}
		
		
		double macdValue  = macd.getValue(close.getTimeSeries().getEnd()).toDouble();
		double emacdValue = emaMacd.getValue(close.getTimeSeries().getEnd()).toDouble();
		if (macdValue > emacdValue && (ema5.getValue(index).toDouble() > ema10.getValue(index).toDouble() )) {
			
			//System.out.println("MACD:"+ macdValue + " Ema " + emacdValue + extraSecureMessage + " ema10 " + ema10.getValue(index) + " ema25 " + ema25.getValue(index)
			//		+ " ema50 " + ema50.getValue(index));
			System.out.println("Average: " + average.getValue(index) + " Average Down: " + averageDown.getValue(index) + " Average Up: " + averageUp.getValue(index));
		}
		
		//		System.out.println(emaMacd.getValue(close.getTimeSeries().getEnd()).toDouble() + " - Indice: " + index);
		//		return false;
		return macdValue > emacdValue && (ema5.getValue(index).toDouble() > ema10.getValue(index).toDouble());
		*/
		
	
	}

}
