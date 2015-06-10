package com.alberto.indicators;

public abstract class AbstractIndicator {

	protected PriceCollection prices;
	protected PriceCollection _cache;
	
	abstract protected void _doCalculation ();
	
	abstract public double get();
	abstract public double get(int index);
	
	
	public double getInclination (int intervals){
		 _doCalculation();
		 if (intervals >= prices.size())
			 return 0;
		 double sum = 0;
		 double alternativeSum = 0;
		 int count = 0;
		 for (int j = _cache.size()-intervals; j < _cache.size()-1;j++ ){
			 count++;
			 sum += this.get(j+1) - this.get(j);
			 alternativeSum += this.get() - this.get(j);
		 }
		 
		// System.out.println(sum/count);
		// return (alternativeSum/count);
		 
		 return sum / count;
	}
}
