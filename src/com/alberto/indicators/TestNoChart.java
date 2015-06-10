package com.alberto.indicators;

import java.awt.Color;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.jfree.chart.plot.IntervalMarker;
import org.joda.time.DateTime;

import com.alberto.Main;
import com.alberto.TradingDay;
import com.alberto.chart.Chart;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Tick;

public class TestNoChart {

	//Chart chart;
	PriceCollection prices;
	
	EMA ema5;
	EMA ema10;
	EMA emaLarge;
	
	MACD macd;
	
	BASIC basic;
	
	ADX adx;
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
	
	public TestNoChart() {
		// TODO Auto-generated constructor stub
		//chart = new Chart();
		prices = new PriceCollection();
		
		ema5 = new EMA(prices, 15);
		ema10 = new EMA(prices,40);
		emaLarge = new EMA(prices,90);
		macd = new MACD(prices,12,26,9);
		basic = new BASIC(prices);
		adx = new ADX(14);  
		shortAdx = new ADX(40);
	}
	
	
	private void status (DateTime datetime){
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
    	//chart.addIndicatorPoint(datetime, macd.getIndicator());
    	//chart.addIndicatorBisPoint(datetime, macd.get());
    	
//    	if (ema5.get() > 0 && ema10.get()>10){
//    	chart.addIndicatorPoint(datetime, ema5.get());
//    	chart.addIndicatorBisPoint(datetime, ema10.get());
//    	}
    	if (emaLarge.get()>0){
    		//chart.addEmaPoint(datetime, emaLarge.get());
    	}
    	
	}
	
	
	private void check (DateTime datetime)
	{
		 if (!open){
			 
			 if (profitLoss < -80 || (datetime.plusHours(2).getHourOfDay()== 10 && datetime.plusHours(2).getMinuteOfHour()<45 )|| (datetime.plusHours(2).getHourOfDay()< 10)|| 
					 datetime.plusHours(2).getHourOfDay()== 17 && datetime.plusHours(2).getMinuteOfHour()>=20 || (lastTrade != null &&
					 lastTrade.plusMinutes(30).isAfter(datetime))){
				 return;
			 }
			 if (zone == 2){
				 if (adx.getADX() < 27 || basic.getInclination(5) > 0 || ema10.getInclination(15) >= 0  || ema5.getInclination(15) >= 0  ||  emaLarge.getInclination(15) >= 0 || macd.getInclination(15) >= 0){
					 return;
				 }
				 
				 
				 
				 mode = -1;
				// marker = chart.addInterval(datetime.getMillis(), datetime.plusMinutes(1).getMillis(), Color.RED, "Corto");				 
				 System.out.println("Entro en corto a " + enterPrice );
			 }else if (zone == -2){
				 if (adx.getADX() < 27 || basic.getInclination(5) < 0 || ema10.getInclination(15) <= 0  || ema5.getInclination(15) <= 0 ||  emaLarge.getInclination(15) <= 0 || macd.getInclination(15) <= 0){
					 return;
				 }
				 
				 
				 
				 mode = 1;
				// marker = chart.addInterval(datetime.getMillis(), datetime.plusMinutes(1).getMillis(), Color.GREEN, "Largo"); 
				 System.out.println("Entro en largo a " + enterPrice );
			 }
			 
			 if (Math.abs(zone)==2){
				 open = true;
				 //chart.addDottedMarker(datetime, Color.GRAY);
				 //chart.addDottedMarker(datetime, Color.GRAY);
				 enterPrice = prices.getLast();
				 lastTrade = datetime;
				 transactionPrice = 0;
				 transactionMax = 0;
				 transactionMin = 0;
			 }
			 
		 }else{
			 
			 if (mode == 1){
				 transactionPrice = prices.getLast() - enterPrice;
			 }else{
				 transactionPrice = enterPrice - prices.getLast();
			 }
			 
			 if (transactionPrice > transactionMax)
				 transactionMax = transactionPrice;
			 
			 if (transactionPrice < transactionMin)
				 transactionMin = transactionPrice;
			 
			 
			 
			 boolean endOrder = true;
			 if ( profitLoss > -80 && ((zone >=0 && mode == -1 ) || (zone<=0 && mode==1)) && ( ((datetime.plusHours(2).getHourOfDay()< 17) || 
					 (datetime.plusHours(2).getHourOfDay()== 17 && datetime.plusHours(2).getMinuteOfHour()<25))) && (
					 (mode == -1 &&   emaLarge.getInclination(15) < 0.1)||
					 (mode == 1 &&   emaLarge.getInclination(15) > -0.1))){			 
				 endOrder = false;
			 }
			 
			
			 
			 if (mode == 1){
				 if ( (macd.getInclination(10) < 0 ||ema10.getInclination(10) < 0 ) && transactionPrice >= 25){
					 endOrder = true;
				 }
			 }else{
				 if ( (macd.getInclination(10) > 0 ||ema10.getInclination(10) > 0 ) && transactionPrice >= 25){
					 endOrder = true;
				 }
			 }
			 
			 if (endOrder){
				 exitPrice = prices.getLast();
				 
				 if (mode == 1){
					 profitLoss += exitPrice - enterPrice;
				 }else{
					 profitLoss += enterPrice - exitPrice;
				 }
				 profitLoss -= 3;
				 
				 System.out.println("Salgo con " + profitLoss);
				 
				 open = false;
				 mode = 0;
				// chart.addMarker(datetime, Color.GRAY);
				
				 
				 
			 }else{
				// marker.setEndValue(datetime.plusMinutes(1).getMillis());
			 }
		 }
	}
	
	private void run(String doc, String filename) {
		try {

			CSVParser oParser = CSVParser.parse(doc,
					CSVFormat.DEFAULT.withDelimiter(';'));
			int i = 0;
			for (CSVRecord csvRecord : oParser) {
				i++;
				try {
					
					Decimal price = Decimal.valueOf(csvRecord.get(0)
							.replaceAll("\\.", "").replaceAll(",", "."));
					
					if (i%12!=0){
						adxLow = (adxLow < 0 || price.toDouble() < adxLow) ? price.toDouble() : adxLow;
						adxHigh = (adxHigh < 0 || price.toDouble() > adxHigh) ? price.toDouble() : adxHigh;
						continue;
					}
					
					//adx.update(adxHigh, adxLow, price.toDouble());
					adx.update(price.toDouble(), price.toDouble(), price.toDouble());
					shortAdx.update(price.toDouble(), price.toDouble(), price.toDouble());
					adxLow = -1;
					adxHigh = -1;
					
					FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss").parse(
							csvRecord.get(1));

					DateTime datetime = DateTime.parse(csvRecord.get(1)).minusHours(2);
					

				    prices.add(price.toDouble());
				   // chart.addPoint(datetime, price.toDouble());
				    
				    current++;
				    //if (i > 150){
				    	status (datetime);
				    	check(datetime);
				    //}
				    

				} catch (ParseException e) {
					// TODO Auto-generated catch block

				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
			
			System.out.println("Totales: " + profitLoss);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Falla");
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args){
		
		
		String doc = "";
		File folder = new File("resources/inputs");

		String[] files = folder.list(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				// TODO Auto-generated method stub
				if (FilenameUtils.isExtension(arg1, "csv")) {
					return true;
				}
				return false;
			}
		});

		double totalProfitLoss = 0;
		for (String file : files) {
			System.out.println(file);

			try {
				doc = FileUtils.readFileToString(new File("resources/inputs/"
						+ file));
				TestNoChart testFile = new TestNoChart();
				testFile.run(doc,file);
				totalProfitLoss += testFile.profitLoss;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Total: " + totalProfitLoss);
		
	}

}
