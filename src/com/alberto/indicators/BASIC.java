package com.alberto.indicators;

public class BASIC extends AbstractIndicator {

	public BASIC(PriceCollection prices) {
		// TODO Auto-generated constructor stub
		this._cache = prices;
		this.prices = prices;
	}

	@Override
	protected void _doCalculation() {
		// TODO Auto-generated method stub
        //_cache = (PriceCollection) prices.clone();
	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return prices.getLast();
	}

	@Override
	public double get(int index) {
		// TODO Auto-generated method stub
		return prices.get(index).getPrice();
	}

}
