package com.alberto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.random.RandomGeneratorFactory;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Statistics {

	DescriptiveStatistics stats;
	public Statistics() {
		// TODO Auto-generated constructor stub
		stats = new DescriptiveStatistics();
		double x[] = new double[20];
		double y[] = new double[20];
		
		

		double ibex[] = {11000,11005,11008,11020,11022,11025,11023,11024,11029,11027,11024,11028,11030,11032,11033,11032,11031,11033};
		for (int i = 0; i < 20;i++){
		    // stats.addValue(i);
		    x[i]=i;
		    y[i]=i;
		}
		stats = new DescriptiveStatistics(ibex);
		
		
		
		System.out.println(stats.getMean());
		//System.out.println(stats.getGeometricMean());
		//System.out.println(stats.getPopulationVariance());
		System.out.println(stats.getStandardDeviation());	
		
		//System.out.println(stats.getQuadraticMean());
		
		RealDistribution t = new TDistribution(3);
		NormalDistribution normal = new NormalDistribution(stats.getMean(),stats.getStandardDeviation());
		
		
		
		UnivariateInterpolator interpolator = new SplineInterpolator();
		UnivariateFunction function = interpolator.interpolate(x,y);
		
		double interpolationX = 19;
		double interpolatedY = function.value(interpolationX);
		//System.out.println("f(" + interpolationX + ") = " + interpolatedY);
		

		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Statistics obj = new Statistics();
		
		
		
	}

}
