// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class TimeSeriesDemo12 extends ApplicationFrame
{

	public TimeSeriesDemo12(String s)
	{
		super(s);
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = createChart(xydataset);
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		chartpanel.setMouseZoomable(true);
		setContentPane(chartpanel);
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Sample Chart", "Date", "Value", xydataset, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setRangeCrosshairVisible(false);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		if (xyitemrenderer instanceof XYLineAndShapeRenderer)
		{
			XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyitemrenderer;
			xylineandshaperenderer.setBaseShapesVisible(true);
			xylineandshaperenderer.setBaseShapesFilled(true);
			xyitemrenderer.setSeriesStroke(0, new BasicStroke(2.0F));
			xyitemrenderer.setSeriesStroke(1, new BasicStroke(2.0F));
		}
		DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("hh:mma"));
		return jfreechart;
	}

	private static XYDataset createDataset()
	{
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		TimeSeries timeseries = new TimeSeries("Series 1");
		timeseries.add(new Minute(0, 0, 7, 12, 2003), 1.2D);
		timeseries.add(new Minute(30, 12, 7, 12, 2003), 3D);
		timeseries.add(new Minute(15, 14, 7, 12, 2003), 8D);
		TimeSeries timeseries1 = new TimeSeries("Series 2");
		timeseries1.add(new Minute(0, 3, 7, 12, 2003), 0.0D);
		timeseries1.add(new Minute(30, 9, 7, 12, 2003), 0.0D);
		timeseries1.add(new Minute(15, 10, 7, 12, 2003), 0.0D);
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.addSeries(timeseries1);
		return timeseriescollection;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		TimeSeriesDemo12 timeseriesdemo12 = new TimeSeriesDemo12("Time Series Demo 12");
		timeseriesdemo12.pack();
		RefineryUtilities.centerFrameOnScreen(timeseriesdemo12);
		timeseriesdemo12.setVisible(true);
	}
}
