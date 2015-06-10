package com.alberto.indicators;

public class RSI {

	public double prevclose; 
	public double RS;
	double movAvgUp;
	double movAvgDown;
	public int windowsize;
	public int nup;
	public int ndown;
	public RSI(int ws){
		prevclose=-10000;
		movAvgUp=0;
		movAvgDown=0;
		windowsize=ws;
		nup=0;
		ndown=0;
	}
	
	public void update(double close){
		double currDiff=0.0;
		if(prevclose>0){
			currDiff=close-prevclose;
			if(currDiff>0){
				
				nup++;
				if (nup<=windowsize){
					//take normal avg
					movAvgUp=((1.0/nup)*currDiff) + (((nup-1.0)/nup)*movAvgUp);
				}
				
				else{
					//take moving avg
					movAvgUp=((1.0/windowsize)*currDiff) + (((windowsize-1.0)/windowsize)*movAvgUp);
				}		
				
			}
			if (currDiff<0){
				currDiff=-1*currDiff;
				ndown++;				
				if (ndown<=windowsize){
					//take normal avg
					movAvgDown=((1.0/ndown)*currDiff) + (((ndown-1.0)/ndown)*movAvgDown);
				}		
				else{
					//take moving avg
					movAvgDown=((1.0/windowsize)*currDiff) + (((windowsize-1.0)/windowsize)*movAvgDown);
				}
			}	
			if (ndown<=windowsize){
				//take normal avg
			}				
		}	
		prevclose=close;
	}
	
	
	public double  getRSI(){
		
		double rsi=50;
		if (movAvgUp==0 && movAvgDown==0){
			rsi=50;
		}
		else{
			rsi=100-((100*movAvgDown)/((movAvgDown)+(movAvgUp)));
		}
		return rsi;				
	}
	
	

}
