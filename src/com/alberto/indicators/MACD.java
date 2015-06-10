package com.alberto.indicators;

import java.util.ArrayList;
import java.util.List;

public class MACD extends AbstractIndicator {

	private EMA min;
	private EMA max;
	private EMA indicator;

	private int maxint;
	private int emaIndicator;

	private double calcMin = 0;
	private double calcMax = 0;
	private double calcDiff = 0;
	private double calcIndicator = 0;

	private PriceCollection _emaIndicator;

	private PriceCollection prices;
	
	public EMA getEmaIndicator ()
	{
		_doCalculation();
		return this.indicator;	
	}
	
	public double getInclination (int intervals){
		 _doCalculation();
		 return min.getInclination(intervals) - max.getInclination(intervals);
		 
	}

	public MACD(PriceCollection prices) {
		// TODO Auto-generated constructor stub
		min = new EMA(prices, 12);
		max = new EMA(prices, 26);
		this.prices = prices;
		_emaIndicator = new PriceCollection();
		indicator = new EMA(_emaIndicator, 9);
		this.maxint = 26;
		this.emaIndicator = 9;
	}

	public MACD(PriceCollection prices, int min, int max, int indicator) {

		this.min = new EMA(prices, min);
		this.max = new EMA(prices, max);
		this.prices = prices;
		_emaIndicator = new PriceCollection();
		this.indicator = new EMA(_emaIndicator, indicator);
		this.emaIndicator = indicator;
		this.maxint = max;
	}

	public double get() {
		_doCalculation();
		return this.calcDiff;
	}

	public double getMin() {
		_doCalculation();
		return calcMin;
	}

	public double getMax() {
		_doCalculation();
		return calcMax;
	}

	public double getIndicator() {
		_doCalculation();
		return calcIndicator;
	}

	protected void _doCalculation() {
		if (prices.size() <= this.maxint) {
			this.calcMin = 0;
			this.calcMax = 0;
			this.calcIndicator = 0;
			this.calcDiff = 0;
		} else {
			this.calcMin = min.get();
			this.calcMax = max.get();
			this.calcDiff = this.calcMax - this.calcMin;
			
			

			if (min.getAll().size() > _emaIndicator.size()) {

				int _lastIndex = _emaIndicator.size();
				List<Price> minAll = min.getAll().subList(maxint - 1,
						min.getAll().size() - 1);
				List<Price> maxAll = max.getAll().subList(maxint - 1,
						max.getAll().size() - 1);
				;

				for (int i = _lastIndex; i < maxAll.size(); i++) {

					if (maxAll.get(i).getPrice() == 0){
						double _price = minAll.get(i).getPrice();
						_price = 0;
						_emaIndicator
						.add(i,_price);
					}else{
						_emaIndicator
						.add(i, minAll.get(i).getPrice()
								- maxAll.get(i).getPrice());
					}
					

				}

			}

			this.calcIndicator = indicator.get();
			
			

		}

	}

	@Override
	public double get(int index) {
		// TODO Auto-generated method stub
		_doCalculation();
		return calcDiff;
	}

}
