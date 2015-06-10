package com.alberto;

import java.awt.Color;

import com.alberto.chart.Chart;

import eu.verdelhan.ta4j.AnalysisCriterion;
import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Order;
import eu.verdelhan.ta4j.Strategy;
import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.TradingRecord;
import eu.verdelhan.ta4j.analysis.CashFlow;
import eu.verdelhan.ta4j.analysis.criteria.AverageProfitableTradesCriterion;
import eu.verdelhan.ta4j.analysis.criteria.RewardRiskRatioCriterion;
import eu.verdelhan.ta4j.analysis.criteria.TotalProfitCriterion;
import eu.verdelhan.ta4j.analysis.criteria.VersusBuyAndHoldCriterion;
 
public abstract class AbstractStrategy {

	
	protected TradingDay trading;
	protected TradingRecord tradingRecord;
	protected Strategy strategy;
	protected String name;
	private   boolean openTransaction = false;
	double profitloss = 0;
	
	Chart chart;
	
	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	protected double profit = 0;
	
	protected double enterPrice = 0;
	protected double exitPrice = 0;
	
	public TradingDay getTrading() {
		return trading;
	}


	public void setTrading(TradingDay trading) {
		this.trading = trading;
	}


	
	
	public AbstractStrategy (TradingDay trading){
		this.trading = trading;
		this.tradingRecord = new TradingRecord();
		this.name = "SinNombre";
		chart = new Chart();
		
	}
	
	
	abstract void setStrategy();
	
	void closePosition()
	{
		int endIndex = this.trading.serie.getEnd();
		Tick lastTick = this.trading.lastTick;
		boolean exited = tradingRecord.exit(endIndex, lastTick.getClosePrice(), Decimal.ONE);
        
        if (exited) {
		 profit += lastTick.getClosePrice().toDouble();
        }
	}
	
	double getProfit ()
	{
		//CashFlow
		CashFlow cash = new CashFlow(this.trading.serie, this.tradingRecord);
		// Getting the profitable trades ratio
        AnalysisCriterion profitTradesRatio = new AverageProfitableTradesCriterion();
        System.out.println("Profitable trades ratio: " + profitTradesRatio.calculate(this.trading.serie, this.tradingRecord));
        // Getting the reward-risk ratio
        AnalysisCriterion rewardRiskRatio = new RewardRiskRatioCriterion();
        System.out.println("Reward-risk ratio: " + rewardRiskRatio.calculate(this.trading.serie, this.tradingRecord));

        // Total profit of our strategy
        // vs total profit of a buy-and-hold strategy
        AnalysisCriterion vsBuyAndHold = new VersusBuyAndHoldCriterion(new TotalProfitCriterion());
        System.out.println("Our profit vs buy-and-hold profit: " + vsBuyAndHold.calculate(this.trading.serie, this.tradingRecord));
	
        
        AnalysisCriterion totalProfit = new TotalProfitCriterion();
        System.out.println("Total Profit: " + totalProfit.calculate(this.trading.serie, this.tradingRecord));
        System.out.println("Total Profit: " + profit);
        return profit;
	}
	
	boolean operate () {
		 int endIndex = this.trading.serie.getEnd();
		 Tick lastTick = this.trading.lastTick;
		 chart.addPoint(lastTick.getEndTime().minusHours(2), lastTick.getClosePrice().toDouble());
		 boolean entered = false ;
		 boolean exited = false;
         if (!openTransaction && strategy.shouldEnter(endIndex)) {
             // Our strategy should enter
        	
             entered = tradingRecord.enter(endIndex, lastTick.getClosePrice(), Decimal.ONE);
      
             
             if (entered) {

            	 chart.addMarker(lastTick.getEndTime().minusHours(2), Color.GREEN.darker());
            	 System.out.println("Entro en: " + lastTick.getClosePrice() + " Hora:" + lastTick.getEndTime().toString("HH:mm:ss"));
                 profit -= lastTick.getClosePrice().toDouble();
                 Order entry = tradingRecord.getLastEntry();
                 enterPrice = lastTick.getClosePrice().toDouble();
                 /*
                 System.out.println("Entered on " + entry.getIndex()
                         + " (price=" + entry.getPrice().toDouble()
                         + ", amount=" + entry.getAmount().toDouble() + ")");*/
                 openTransaction = true;
                 postBuy();
             }
             
         } 
         if (openTransaction && strategy.shouldExit(endIndex)) {
             // Our strategy should exit
        	 
             //System.out.println("Strategy should EXIT on " + endIndex);
             exited = tradingRecord.exit(endIndex, lastTick.getClosePrice(), Decimal.ONE);
             
             if (exited) {
            	 chart.addMarker(lastTick.getEndTime().minusHours(2), Color.RED);
            	 profit += lastTick.getClosePrice().toDouble();
                 Order exit = tradingRecord.getLastExit();
                 exitPrice  = lastTick.getClosePrice().toDouble();
                 System.out.println("Salgo en: " + lastTick.getClosePrice() + " Hora:" + lastTick.getEndTime().toString("HH:mm:ss") + ", Diferencia= "+(exitPrice-enterPrice));
                 /*
                 System.out.println("Exited on " + exit.getIndex()
                         + " (price=" + exit.getPrice().toDouble()
                         + ", amount=" + exit.getAmount().toDouble() + ")");*/
                 profitloss += exitPrice - enterPrice - 3;
                 System.out.println("Beneficios o pérdidas de hoy (Estimados): " + profitloss);
                 openTransaction = false;
                 postSell();
             }
         }
         //System.out.println(tradingRecord.getTradeCount());
         return entered || exited;
	}
	
	void postSell (){
		
	}
	
	void postBuy () {
		
	}
	
	
}
