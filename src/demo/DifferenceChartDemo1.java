// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DifferenceChartDemo1 extends ApplicationFrame
{

	public DifferenceChartDemo1(String s)
	{
		super(s);
		JPanel jpanel = createDemoPanel();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}

	private static XYDataset createDataset()
	{
		TimeSeries timeseries = new TimeSeries("Random 1");
		TimeSeries timeseries1 = new TimeSeries("Random 2");
		double d = 0.0D;
		double d1 = 0.0D;
		Day day = new Day();
		for (int i = 0; i < 200; i++)
		{
			d = (d + Math.random()) - 0.5D;
			d1 = (d1 + Math.random()) - 0.5D;
			timeseries.add(day, d);
			timeseries1.add(day, d1);
			day = (Day)day.next();
		}

		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		timeseriescollection.addSeries(timeseries1);
		return timeseriescollection;
	}

	private static JFreeChart createChart(XYDataset xydataset)
	{
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Difference Chart Demo 1", "Time", "Value", xydataset, true, true, false);
		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		xyplot.setDomainPannable(true);
		XYDifferenceRenderer xydifferencerenderer = new XYDifferenceRenderer(Color.green, Color.red, false);
		xydifferencerenderer.setRoundXCoordinates(true);
		xyplot.setDomainCrosshairLockedOnData(true);
		xyplot.setRangeCrosshairLockedOnData(true);
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setRangeCrosshairVisible(true);
		xyplot.setRenderer(xydifferencerenderer);
		DateAxis dateaxis = new DateAxis("Time");
		dateaxis.setLowerMargin(0.0D);
		dateaxis.setUpperMargin(0.0D);
		xyplot.setDomainAxis(dateaxis);
		xyplot.setForegroundAlpha(0.5F);
		ChartUtilities.applyCurrentTheme(jfreechart);
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
		DifferenceChartDemo1 differencechartdemo1 = new DifferenceChartDemo1("JFreeChart: DifferenceChartDemo1.java");
		differencechartdemo1.pack();
		RefineryUtilities.centerFrameOnScreen(differencechartdemo1);
		differencechartdemo1.setVisible(true);
	}
}
