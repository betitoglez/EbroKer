package com.alberto.indicators;

import java.awt.Color;
import java.util.Date;
import java.util.Locale;

import org.jfree.chart.plot.IntervalMarker;
import org.joda.time.DateTime;

import com.alberto.chart.Chart;
import com.alberto.chart.OHLChart;
import com.alberto.push.Handler;
import com.is_teledata.mdg.MDGConfig;
import com.is_teledata.mdg.MDGConfigException;
import com.is_teledata.mdg.MDGObject;
import com.is_teledata.mdg.MDGSession;
import com.is_teledata.mdg.push.PushConfig;
import com.is_teledata.mdg.push.PushSession;
import com.is_teledata.mdg.push.Subscription;

import eu.verdelhan.ta4j.Tick;

public class Broker extends BrokerInterface {
	
	PushSession session;
	Subscription subscription;

	String idApp = "12749";
	String idPass = "kPmK9sscbX4";
	String user = "TechUser2BBVA";
	String userPass = "TechBBVA2User";
	//String ID_NOTATION[] = {"324911"};
	//String ID_NOTATION = "193736";
	String ID_NOTATION = "324911";
	
	String pullApp      =  "id10946";
	String pullPass     =  "jhdfcpoa";
//	String pullApp      =  "id12749";
//	String pullPass     =  "kPmK9sscbX4";
	
	MDGSession mdgSession;
	
	
	public Chart chart;
	public OHLChart ohlcChart;
	public PriceCollection prices;
	
	EMA ema5;
	EMA ema10;
	EMA emaLarge;
	
	EMA ema21;
	EMA ema38;
	public RSI rsi;
	
	MACD macd;
	
	BASIC basic;
	
	public ADX adx;
	ADX shortAdx;
	
	
	int status = 100;
	int macdStatus = 100;
	int current = 0;
	
	int zone = 0;
	
	boolean open = false;
	int mode = 0;
	IntervalMarker marker;
	
	
	double enterPrice;
	double exitPrice;
	double profitLoss = 0;
	
	double transactionPrice = 0;
	double transactionMin = 0;
	double transactionMax = 0;
	
	double adxHigh=-1;
	double adxLow=-1;
	
	DateTime lastTrade;
	
	public double checkEnterInclination = 0.2;
	public double checkExitOrderFalseInclination = 0.5;
	public double checkExitOrderTrueInclination = 0.5;
	
	public int priceInclination = 10;
	public int emaInclination = 20;
	
	public int stopGain = 50;

	public boolean stopLossTransactionEnabled = false;
	public double stopLossTransactionPrice = 25;
	public double priceStop;

	public double intradayMinimum = 0;
	public double intradayMaximum = 0;
	
	public double totalIntradayStopLoss = 80;

	public boolean lastTradeEnabled = true;
	public int lastTradeMinutes = 40;

	public int adxOrder = 45;
	
	public int zoneCheck = 0;

	
	public Broker() {
		// TODO Auto-generated constructor stub
		
		
		idApp    = "10946";
		user     = "technical";
		userPass = "technical";

		
		try {
			chart = new Chart();
			ohlcChart = new OHLChart();
			prices = new PriceCollection();
			
			ema5 = new EMA(prices, 5);
			ema10 = new EMA(prices,15);
			emaLarge = new EMA(prices,60);
			macd = new MACD(prices,12,30,18);
			basic = new BASIC(prices);
			adx = new ADX(8);  
//			shortAdx = new ADX(40);
//			rsi = new RSI(14);
//			
//			ema21 = new EMA(prices, 21);
//			ema38 = new EMA(prices,38);
			
			mdgSession = MDGSession.getInstance(MDGConfig.getMDGConfig(), idApp, pullApp, pullPass); 
			
			historyList();
			
			session = PushSession.getUserPushInstanceByLogin(
					PushConfig.getPushConfig(), idApp, user,
					userPass);
			
			subscription = new Subscription("prices/quote?CODE_QUALITY_PRICE=BST&VERSION=2&ID_NOTATION="+ID_NOTATION,new Handler(this));
			
			session.subscribe(subscription);
			
			
			
				
			
		} catch (MDGConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void status (DateTime datetime){
		zone = 0;
		
		if (status == 100){
    		status = ema5.get() > ema10.get()?-1:1;
    	}else{
    		if (ema5.get() > ema10.get() ){
    			if (status == 1)
    			      status = -1;
    			zone--;
    		}else if (ema5.get() < ema10.get() ){
    		  if (status == -1)
    			status = 1;
    		    zone++;
    		}
    	}
    	
    	if (macdStatus == 100){
    		macdStatus = macd.get() > macd.getIndicator()?1:-1;
    	}else{
    		if (macd.get() > macd.getIndicator()){
    		  if (macdStatus == -1)
    			macdStatus = 1;
    		   zone++;
    		}else if (macd.get() < macd.getIndicator()){
    		if (macdStatus == 1)
    			macdStatus = -1;
    		  zone--;
    		}
    	}
    	//System.out.println(datetime.plusHours(2).toString("HH:mm") + " -> " +zone);
    //	chart.addIndicatorPoint(datetime, macd.getIndicator());
    //	chart.addIndicatorBisPoint(datetime, macd.get());
    	

    	ohlcChart.addMacd(datetime, macd.get(), macd.getIndicator());
    	ohlcChart.addAdx(datetime, adx.getADX());
//    	ohlcChart.addAdx(datetime, rsi.getRSI());
    	//chart.addIndicatorPoint(datetime, ema21.get());
    	//chart.addIndicatorBisPoint(datetime, adx.getADX());
    	
    	if (ema5.get()>0){
    		ohlcChart.addPointIndicator(1, datetime, ema5.get());
    	}
    	
    	if (ema10.get()>0){
    		ohlcChart.addPointIndicator(2, datetime, ema10.get());
    	}
    	
    	if (emaLarge.get()>0){
    		ohlcChart.addPointIndicator(3, datetime, emaLarge.get());
    	}

    	if (emaLarge.get()>0){
    		chart.addEmaPoint(datetime, emaLarge.get());
    	}
    	
	}
	
	
	public void check(DateTime datetime) {

		if (!open) {
			
			checkEnter(datetime);
			
		} else {
			checkExit(datetime);
		}
	}
	
	

	private void checkEnter(DateTime datetime) {
		if (profitLoss < totalIntradayStopLoss * -1
				|| (datetime.getHourOfDay() == 10 && datetime.getMinuteOfHour() < 45)
				|| (datetime.getHourOfDay() < 10)
				|| datetime.getHourOfDay() == 17
				&& datetime.getMinuteOfHour() >= 20
				|| (lastTrade != null && lastTrade
						.plusMinutes(lastTradeMinutes).isAfter(datetime))) {
			return;
		}
		if (zone >= zoneCheck) {
			if (adx.getADX() < adxOrder
					|| basic.getInclination(priceInclination) > checkEnterInclination
					|| ema10.getInclination(emaInclination) >= checkEnterInclination
					|| ema5.getInclination(emaInclination) >= checkEnterInclination
					|| emaLarge.getInclination(emaInclination) >= checkEnterInclination
					|| macd.getInclination(emaInclination) >= checkEnterInclination) {
				return;
			}
			priceStop = prices.getLast() + stopLossTransactionPrice;
			mode = -1;
			marker = chart.addInterval(datetime.getMillis(),
					datetime.plusMinutes(1).getMillis(),
					Color.RED, "Corto");

		} else if (zone <= zoneCheck * -1) {
			if (adx.getADX() < adxOrder
					|| basic.getInclination(priceInclination) < checkEnterInclination
					|| ema10.getInclination(emaInclination) <= checkEnterInclination
					|| ema5.getInclination(emaInclination) <= checkEnterInclination
					|| emaLarge.getInclination(emaInclination) <= checkEnterInclination
					|| macd.getInclination(emaInclination) <= checkEnterInclination) {
				return;
			}
			priceStop = prices.getLast() - stopLossTransactionPrice;
			mode = 1;
			marker = chart.addInterval(datetime.getMillis(),
					datetime.plusMinutes(1).getMillis(),
					Color.GREEN, "Largo");

		}

		if (Math.abs(zone) >= zoneCheck) {
			open = true;

			enterPrice = prices.getLast();
			lastTrade = datetime;
			transactionPrice = 0;
			transactionMax = 0;
			transactionMin = 0;
		}
	}

	public void checkExit(DateTime datetime) {
		if (mode == 1) {
			transactionPrice = prices.getLast() - enterPrice;
		} else {
			transactionPrice = enterPrice - prices.getLast();
		}

		if (transactionPrice > transactionMax)
			transactionMax = transactionPrice;

		if (transactionPrice < transactionMin)
			transactionMin = transactionPrice;

		boolean endOrder = true;
		if (profitLoss > -80
				&& ((zone >= 0 && mode == -1) || (zone <= 0 && mode == 1))
				&& (((datetime.getHourOfDay() < 17) || (datetime.getHourOfDay() == 17 && datetime
						.getMinuteOfHour() < 25)))
				&& ((mode == -1 && emaLarge.getInclination(emaInclination) < checkExitOrderFalseInclination) || (mode == 1 && emaLarge
						.getInclination(emaInclination) > checkExitOrderFalseInclination
						* -1))) {
			endOrder = false;
		}

		if (mode == 1) {
			if ((macd.getInclination(emaInclination) < checkExitOrderTrueInclination || ema10
					.getInclination(emaInclination) < checkExitOrderTrueInclination)
					&& transactionPrice >= stopGain) {
				endOrder = true;
			}

		} else {
			if ((macd.getInclination(emaInclination) > checkExitOrderTrueInclination || ema10
					.getInclination(emaInclination) > checkExitOrderTrueInclination)
					&& transactionPrice >= stopGain) {
				endOrder = true;
			}

		}

		if (endOrder) {
			exitPrice = prices.getLast();

			if (mode == 1) {
				profitLoss += exitPrice - enterPrice;
			} else {
				profitLoss += enterPrice - exitPrice;
			}
			profitLoss -= 3;
			open = false;
			mode = 0;
			System.out.println(profitLoss);

		}else{
			marker.setEndValue(datetime.plusMinutes(1).getMillis());
		}
	}
	
	
	public void historyList (){
	    
		MDGObject history = MDGObject.getInstance(mdgSession, "/prices/history_list?CODE_QUALITY_PRICE=BST&ID_NOTATION="+ID_NOTATION+"&CODE_RESOLUTION=1s&VERSION=2&BLOCKSIZE=20000&OFFSET_END_RANGE=-4&OFFSET_START_RANGE=-4"); 
		int amount = history.getIntValue("AMOUNT").intValue();
		double _open=0, _high=0 , _low=0 , _close=0;
		double minute=0;
        for (int i = 1; i < amount;i++){
        	
        	//if (i%12!=0){
        	//	continue;
        	//}
        	DateTime datetime = new DateTime( history.getDateTimeValue(i, "DATETIME_LAST").longValue()).plusHours(2);  
        	
        	if (minute > 0 && datetime.getMinuteOfDay() == minute){
				continue;
			}

			minute = datetime.getMinuteOfDay();
        	
        	        	
        
        	//adx.update(history.getFloatValue(i,"HIGH"),history.getFloatValue(i,"LOW"), history.getFloatValue(i,"LAST"));
        	adx.update(history.getFloatValue(i,"LAST"),history.getFloatValue(i,"LAST"), history.getFloatValue(i,"LAST"));
        	//rsi.add(history.getFloatValue(i,"LAST"));
        	prices.add(history.getFloatValue(i,"LAST"));
        	chart.addPoint(datetime, history.getFloatValue(i,"LAST"));
        	
        	
        	if ( (i-1)%5 == 0){
        		if (_open>0){
        			ohlcChart.addCandle(datetime, _open, _high, _low, _close);
        		}
        		_open = history.getFloatValue(i,"FIRST");
        		_high = history.getFloatValue(i,"HIGH");
        		_low = history.getFloatValue(i,"LOW");
        	}else{
        		_close = history.getFloatValue(i,"LAST");
        		if (history.getFloatValue(i,"HIGH") > _high)
        			 _high = history.getFloatValue(i,"HIGH");
        		if (history.getFloatValue(i,"LOW")<_low)
        			_low = history.getFloatValue(i,"LOW");
        	}
        	
        	status (datetime);
	    	check(datetime);
        }
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Broker eBroker = new Broker();
		
	}

}
