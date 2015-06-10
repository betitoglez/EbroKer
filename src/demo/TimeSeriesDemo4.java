// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.*;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.*;
import org.jfree.ui.about.ProjectInfo;

public class TimeSeriesDemo4 extends ApplicationFrame
{

	public TimeSeriesDemo4(String s)
	{
		super(s);
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = createChart(xydataset);
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		chartpanel.setMouseZoomable(true);
		setContentPane(chartpanel);
	}

	private static XYDataset createDataset()
	{
		TimeSeries timeseries = new TimeSeries("Random Data");
		Day day = new Day();
		timeseries.add(new Hour(0, day), 500.19999999999999D);
		timeseries.add(new Hour(2, day), 694.10000000000002D);
		timeseries.add(new Hour(3, day), 734.39999999999998D);
		timeseries.add(new Hour(4, day), 453.19999999999999D);
		timeseries.add(new Hour(7, day), 500.19999999999999D);
		timeseries.add(new Hour(8, day), null);
		timeseries.add(new Hour(12, day), 734.39999999999998D);
		timeseries.add(new Hour(16, day), 453.19999999999999D);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeseries);
		return timeseriescollection;
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		String s = "??????????";
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(s, "Time", "Value", xydataset, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 20D));
		ValueMarker valuemarker = new ValueMarker(700D);
		valuemarker.setPaint(Color.blue);
		valuemarker.setAlpha(0.8F);
		xyplot.addRangeMarker(valuemarker);
		xyplot.setBackgroundPaint(null);
		xyplot.setBackgroundImage(JFreeChart.INFO.getLogo());
		xyplot.getDomainAxis().setLowerMargin(0.0D);
		return jfreechart;
	}

	public static JPanel createDemoPanel()
	{
		JFreeChart jfreechart = createChart(createDataset());
		return new ChartPanel(jfreechart);
	}

	public static void main(String args[])
	{
		TimeSeriesDemo4 timeseriesdemo4 = new TimeSeriesDemo4("Time Series Demo 4");
		timeseriesdemo4.pack();
		RefineryUtilities.centerFrameOnScreen(timeseriesdemo4);
		timeseriesdemo4.setVisible(true);
	}
}
