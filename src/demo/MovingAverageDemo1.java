// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class MovingAverageDemo1 extends ApplicationFrame
{

	public MovingAverageDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	public static XYDataset createDataset()
	{
		TimeSeries timeseries = new TimeSeries("L&G European Index Trust");
		timeseries.add(new Month(2, 2001), 181.80000000000001D);
		timeseries.add(new Month(3, 2001), 167.30000000000001D);
		timeseries.add(new Month(4, 2001), 153.80000000000001D);
		timeseries.add(new Month(5, 2001), 167.59999999999999D);
		timeseries.add(new Month(6, 2001), 158.80000000000001D);
		timeseries.add(new Month(7, 2001), 148.30000000000001D);
		timeseries.add(new Month(8, 2001), 153.90000000000001D);
		timeseries.add(new Month(9, 2001), 142.69999999999999D);
		timeseries.add(new Month(10, 2001), 123.2D);
		timeseries.add(new Month(11, 2001), 131.80000000000001D);
		timeseries.add(new Month(12, 2001), 139.59999999999999D);
		timeseries.add(new Month(1, 2002), 142.90000000000001D);
		timeseries.add(new Month(2, 2002), 138.69999999999999D);
		timeseries.add(new Month(3, 2002), 137.30000000000001D);
		timeseries.add(new Month(4, 2002), 143.90000000000001D);
		timeseries.add(new Month(5, 2002), 139.80000000000001D);
		timeseries.add(new Month(6, 2002), 137D);
		timeseries.add(new Month(7, 2002), 132.80000000000001D);
		TimeSeries timeseries1 = MovingAverage.createMovingAverage(timeseries, "Six Month Moving Average", 6, 0);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.addSeries(timeseries1);
		return timeseriescollection;
	}

	public static JFreeChart createChart(XYDataset xydataset)
	{
		String s = "Legal & General Unit Trust Prices";
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(s, "Date", "Price Per Unit", xydataset, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		if (xyitemrenderer instanceof XYLineAndShapeRenderer)
		{
			XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyitemrenderer;
			xylineandshaperenderer.setBaseShapesVisible(false);
			xylineandshaperenderer.setSeriesShapesVisible(0, true);
			xylineandshaperenderer.setUseFillPaint(true);
			xylineandshaperenderer.setBaseFillPaint(Color.white);
		}
		DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setMouseWheelEnabled(true);
		return chartpanel;
	}

	public static void main(String args[])
	{
		MovingAverageDemo1 movingaveragedemo1 = new MovingAverageDemo1("JFreeChart: MovingAverageDemo1.java");
		movingaveragedemo1.pack();
		RefineryUtilities.centerFrameOnScreen(movingaveragedemo1);
		movingaveragedemo1.setVisible(true);
	}
}
