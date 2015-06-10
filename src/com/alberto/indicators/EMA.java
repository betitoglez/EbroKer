package com.alberto.indicators;

import java.util.ArrayList;
import java.util.List;

public class EMA extends AbstractIndicator {

	private int period;
	
	
	
	
	public EMA(PriceCollection prices) {
		// TODO Auto-generated constructor stub
		this.prices = prices;
		this.period = 10;
		_cache = new PriceCollection();
	}
	
	public EMA (PriceCollection prices,int period) {
		this.prices = prices;
		this.period = period;
		_cache = new PriceCollection();
	}
	
	public double get(){
		_doCalculation();
		return _cache.get(_cache.size()-1).getPrice();
	}
	
	public double get(int index){
		_doCalculation();
		return _cache.get(index).getPrice();
	}
	
	public PriceCollection getAll (){
		_doCalculation();
		return _cache;
	}
	
	protected void   _doCalculation () {
		if (prices.size() > _cache.size()){
			int _lastCacheItem = _cache.size();
			for (int i = _lastCacheItem; i < prices.size(); i++){
				if (i < period){
					_cache.add(i,0);
				}else if (i == period){
					double _sum = 0;
				    for (int i1 = 0; i1 < period ; i1++){
				    	_sum += prices.get(i1).getPrice();
				    }
				  
				    _cache.add(_sum / period);
				}else{
					int _auxPeriod = period + 1;
					double s = 2/(double)_auxPeriod;
					_cache.add( ((prices.get(i).getPrice()*2.0)/(_auxPeriod)) +  (_cache.get(i-1).getPrice() * (1.0 - ((s)))));
				}
				
				
			}
		}
	}
	
	
	

}
