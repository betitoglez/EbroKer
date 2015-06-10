package com.alberto.indicators;

import java.io.*;
import java.util.ArrayList;


public class ADX{
	
	// Initialize Variables
	double EMAWindow;
	double CurrHigh;
	double PrevHigh;
	double CurrLow;
	double PrevLow;
	double PrevEMAP;
	double CurrEMAP;
	double PrevEMAN;
	double CurrEMAN;
	double PrevClose;
	double CurrClose;
	double CurrADX;
	double PrevADX;
	double CurrPDM;
	double CurrNDM;
	double NormADX;
	double ATR;
	double PrevATR;
	
	ArrayList<Double> PDMSeries;   // Positive Direction Movement 
	ArrayList<Double> NDMSeries;	// Second Direction Movement
	ArrayList<Double> DIRatioSeries; //Directional Indicator
	ArrayList<Double> ATRSeries;
 	
	// Constructor
	public ADX(double n){
		EMAWindow = n;	
		CurrHigh = 0;
		PrevHigh = 0;
		CurrLow = 0;
		PrevLow = 0;
		
		CurrEMAP = 0;
		PrevEMAP = 0;
		CurrEMAN = 0;
		PrevEMAN = 0;
		
		PrevClose = 0;
		CurrClose = 0;
		
		CurrADX = 0;
		PrevADX = 0;
		
		PDMSeries=new ArrayList<Double>();
		NDMSeries=new ArrayList<Double>();
		DIRatioSeries=new ArrayList<Double>();
		ATRSeries = new ArrayList<Double>();
	}
	
	public ADX(){
		EMAWindow = 14;
		CurrHigh = 0;
		PrevHigh = 0;
		CurrLow = 0;
		PrevLow = 0;
		
		CurrEMAP = 0;
		PrevEMAP = 0;
		CurrEMAN = 0;
		PrevEMAN = 0;
		
		PrevClose = 0;
		CurrClose = 0;
		
		CurrADX = 0;
		PrevADX = 0;
		
		PDMSeries=new ArrayList<Double>();
		NDMSeries=new ArrayList<Double>();
		DIRatioSeries=new ArrayList<Double>();
		ATRSeries = new ArrayList<Double>();
	}
	
	public void update(double high,double low,double close){
		PrevHigh = CurrHigh;
		PrevLow = CurrLow;
		PrevClose = CurrClose;
		CurrHigh = high;
		CurrLow =  low;
		CurrClose = close;
//		System.out.println("Started " + h +" "+ l + " " + c);
		if (PrevHigh != 0 && PrevLow != 0){
			updateDM();
		}
//		System.out.println("currADX is :" + CurrADX);
//		System.out.println("Prev " +PrevHigh + " " + PrevLow + " " + PrevClose);
//		System.out.println("Curr " +CurrHigh + " " + CurrLow + " " + CurrClose);
		NormADX = CurrADX*100;
		
	}
	
	
	
	private void updateDM(){
		double DownMove;
		double UpMove;
		double zero = 0;
		
		DownMove = PrevLow - CurrLow;
		UpMove = CurrHigh - PrevHigh;
//		System.out.println("Upmove:"+UpMove+" "+"DownMove:"+DownMove);
		if (PDMSeries.size()+1 <= EMAWindow){
//			System.out.println(PDMSeries.size()+" <= "+ EMAWindow);
			if (UpMove > DownMove && UpMove>0){
				PDMSeries.add(0,UpMove);
			}
			else {
				PDMSeries.add(0,zero);
			}
			if (DownMove > UpMove && DownMove>0){
				NDMSeries.add(0,DownMove);
			}
			else {
				NDMSeries.add(0,zero);
			}
			CurrEMAP = Avg(PDMSeries);
			CurrEMAN = Avg(NDMSeries);			
//			System.out.println("PDM:"+PDMSeries.get(0) +" "+"NDM:"+NDMSeries.get(0));
//			System.out.println("CurrEMAP:"+CurrEMAP +" "+"CurrEMAN:"+CurrEMAN);
			ATR = getATR();
			CurrADX = calcADX();
		}
		else {
//			System.out.println(PDMSeries.size()+" > "+ EMAWindow);
				PrevEMAP = CurrEMAP;
				PrevEMAN = CurrEMAN;
				if (UpMove > DownMove && UpMove>0){
					CurrPDM = UpMove;
				}
				else {
					CurrPDM = zero;
				}
				if (DownMove > UpMove && DownMove>0){
					CurrNDM = DownMove;
				}
				else {
					CurrNDM = zero;
				}
				CurrEMAP = getEMA(PrevEMAP,CurrPDM,EMAWindow);
				CurrEMAN = getEMA(PrevEMAN,CurrNDM,EMAWindow);
//				System.out.println("PrevEMAP:"+PrevEMAP +" "+"PrevEMAN:"+PrevEMAN);
//				System.out.println("CurrEMAP:"+CurrEMAP +" "+"CurrEMAN:"+CurrEMAN);
				ATR = getATR();
				CurrADX = calcADX();		
		}			
	}
	public double Avg(ArrayList<Double> Series){
		double AvgD = 0;
		for (double countD:Series){
			AvgD += countD;
		}
		AvgD = AvgD/Series.size();
		return AvgD;
	}
	
	public double getEMA(double PrevEMA,double CurrPrice,double EMAWindow){
//		System.out.println("EMA CALLED");
		double CurrEMA ;
		CurrEMA = PrevEMA*(EMAWindow-1.0)/EMAWindow + CurrPrice*1.0/EMAWindow;
		return CurrEMA;
	}
	
	private double getATR(){
		double maxP;
		double minP;
		double ATRcalc;
		double currATR;
		if (CurrHigh > PrevClose){
			maxP = CurrHigh;
		}
		else {
			maxP = PrevClose;
		}
		if (CurrLow < PrevClose){
			minP = CurrLow;
		}
		else {
			minP = PrevClose;
		}
		currATR = maxP - minP;
		
		if (ATRSeries.size()+1 <= EMAWindow){
			ATRSeries.add(0,currATR);
			ATRcalc = Avg(ATRSeries);
//			System.out.println("PrevADX : "+ PrevADX+ " CurrADX : "+ CurrADX );
		}
		else {
			PrevATR = ATR;
			ATRcalc = getEMA(PrevATR,currATR,EMAWindow);
//			System.out.println("PrevADX : "+ PrevADX+ " CurrADX : "+ ADXcalc );
		}
//		System.out.println("ATR: "+ATRcalc);
		return ATRcalc;
	}
	
	public double calcADX(){
		double PDI;
		double NDI;
		double DIDiff;
		double DISum;
		double AbsDIDiff;
		double DIRatio;
		double ADXcalc;
		
		PDI = CurrEMAP/ATR;
		NDI = CurrEMAN/ATR;
//		System.out.println("PDI: "+PDI+ " NDI: "+NDI);
		DIDiff = PDI - NDI;
		DISum = PDI + NDI;
		if (DIDiff<0)
			AbsDIDiff = -1*DIDiff;
		else AbsDIDiff = DIDiff;
		if (DISum != 0)
			DIRatio = AbsDIDiff/DISum;
		else
			DIRatio = .5;
//		System.out.println("AbsDiff : "+ AbsDIDiff+ " Sum : "+ DISum + " AdRatio : "+ DIRatio );		
		if (DIRatioSeries.size()+1 <= EMAWindow){
			DIRatioSeries.add(0,DIRatio);
			ADXcalc = Avg(DIRatioSeries);
//			System.out.println("PrevADX : "+ PrevADX+ " CurrADX : "+ CurrADX );
		}
		else {
			PrevADX = CurrADX;
			ADXcalc = getEMA(PrevADX,DIRatio,EMAWindow);
//			System.out.println("PrevADX : "+ PrevADX+ " CurrADX : "+ ADXcalc );
		}
		return ADXcalc;
	}	
	public double getADX(){
//		System.out.println("NormADX : "+ NormADX);
		return NormADX;
	}
	public static void main(String[] args) {
		double b;
		double d;
		
		try {
			
		       
	        ADX	a =new ADX(10);
	        for (int i = 10; i < 100; i++){
	        	System.out.println(i+1);
	            a.update(Double.valueOf(i+1), i-1, i);
	            b = a.getADX();
	            d = a.getATR();
	            System.out.println(b+ " -> " +d+" "+(i+1)+" "+(i-1)+" "+i);
	        }
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
